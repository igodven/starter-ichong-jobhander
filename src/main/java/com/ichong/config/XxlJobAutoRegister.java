package com.ichong.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.ichong.annotate.XxlRegister;
import com.ichong.pojo.entity.JobGroup;
import com.ichong.pojo.entity.JobInfo;
import com.ichong.service.JobGroupService;
import com.ichong.service.JobInfoService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class XxlJobAutoRegister implements ApplicationListener<ApplicationReadyEvent>,
        ApplicationContextAware {
    private ApplicationContext applicationContext;

    private final JobGroupService jobGroupService;

    private final JobInfoService jobInfoService;

    private final RedissonClient redissonClient;

    @Value("${spring.application.name}")
    private String appName;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        RLock lock = redissonClient.getLock(appName);
        try {
            if (lock.tryLock()) {
                addJobGroup();//注册执行器
                addJobInfo();//注册任务
            }
        } finally {
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }


    private void addJobGroup() {
        boolean flag = jobGroupService.preciselyCheck();
        if (!flag) {
            jobGroupService.autoRegisterGroup();
        }
    }

    private void addJobInfo() {
        List<JobGroup> jobGroups = jobGroupService.getJobGroup();
        JobGroup jobGroup = jobGroups.get(0);

        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Object.class, false, true);
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);

            Map<Method, XxlJob> annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                    new MethodIntrospector.MetadataLookup<XxlJob>() {
                        @Override
                        public XxlJob inspect(Method method) {
                            return AnnotatedElementUtils.findMergedAnnotation(method, XxlJob.class);
                        }
                    });
            for (Map.Entry<Method, XxlJob> methodXxlJobEntry : annotatedMethods.entrySet()) {
                Method executeMethod = methodXxlJobEntry.getKey();
                XxlJob xxlJob = methodXxlJobEntry.getValue();

                //自动注册
                if (executeMethod.isAnnotationPresent(XxlRegister.class)) {
                    XxlRegister xxlRegister = executeMethod.getAnnotation(XxlRegister.class);
                    List<JobInfo> jobInfo =
                            jobInfoService.getJobInfo(jobGroup.getId(),
                                    xxlJob.value());
                    if (!jobInfo.isEmpty()) {
                        //因为是模糊查询，需要再判断一次
                        Optional<JobInfo> first = jobInfo.stream()
                                .filter(xxlJobInfo -> xxlJobInfo.getExecutorHandler().equals(xxlJob.value()))
                                .findFirst();
                        if (first.isPresent()) {
                            continue;
                        }
                    }

                    JobInfo xxlJobInfo = createXxlJobInfo(jobGroup, xxlJob, xxlRegister);
                    jobInfoService.addJobInfo(xxlJobInfo);
                }
            }
        }
    }

    private JobInfo createXxlJobInfo(JobGroup jobGroup, XxlJob xxlJob, XxlRegister xxlRegister) {

        JobInfo jobInfo = new JobInfo();
        //基础配置
        jobInfo.setJobGroup(jobGroup.getId());
        jobInfo.setAuthor(xxlRegister.author());
        jobInfo.setJobDesc(xxlRegister.jobDesc());
        jobInfo.setAlarmEmail(xxlRegister.alarmEmail());

        //调度配置
        jobInfo.setScheduleType(xxlRegister.scheduleType());
        jobInfo.setScheduleConf(xxlRegister.scheduleConf());

        //是否开启任务
        jobInfo.setTriggerStatus(xxlRegister.triggerStatus());

        //任务配置
        jobInfo.setExecutorHandler(xxlJob.value());
        jobInfo.setGlueType(xxlRegister.glueType());
        jobInfo.setExecutorParam(xxlRegister.executorParam());


        //高级配置
        jobInfo.setExecutorRouteStrategy(xxlRegister.executorRouteStrategy());
        jobInfo.setExecutorBlockStrategy(xxlRegister.executorBlockStrategy());
        jobInfo.setExecutorFailRetryCount(xxlRegister.executorFailRetryCount());
        jobInfo.setExecutorTimeout(xxlRegister.executorTimeout());

        jobInfo.setGlueRemark("GLUE代码初始化");

        //调度过期策略
        jobInfo.setMisfireStrategy(xxlRegister.misfireStrategy());

        return jobInfo;

    }


}