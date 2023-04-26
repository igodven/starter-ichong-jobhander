package com.ichong.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ichong.config.CustomXxlJobConfig;
import com.ichong.pojo.entity.JobGroup;
import com.ichong.service.JobGroupService;
import com.ichong.service.JobLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 陈高文
 * @description 针对表【xxl_job_group】的数据库操作Service实现
 * @createDate 2023-04-24 15:59:00
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JobGroupServiceImpl implements JobGroupService {

    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.application.name}")
    private String title;


    private final CustomXxlJobConfig customXxlJobConfig;

    private final JobLoginService jobLoginService;

    private final RedissonClient redissonClient;

    /**
     * 查询执行器管理信息
     *
     * @return java.util.List<com.ichong.pojo.entity.JobGroup>
     * @author igodven
     * @params []
     * @date 2023/4/25 14:33
     */
    @Override
    public List<JobGroup> getJobGroup() {
        String url = customXxlJobConfig.getUrl() + "/jobgroup/pageList";
        RBucket<String> loginCookieRBucket = redissonClient.getBucket(
                "XXL_JOB_LOGIN_IDENTITY");
        String loginCookie = loginCookieRBucket.get();
        if (ObjectUtil.isEmpty(loginCookie)) {
            loginCookie = jobLoginService.login();
        }
        HttpResponse response = null;
        try {
            response = HttpUtil.createPost(url)
                    .form("appname", appName)
//                    .form("title", title)
                    .cookie(loginCookie)
                    .execute();
        } catch (Exception e) {
            log.error("调用获取自动任务执行器管理信息接口失败:{}", e.getMessage());
            throw new RuntimeException("调用获取自动任务执行器管理信息接口失败");
        }
        assert response != null;
        String body = response.body();
//        List<JobGroup> jobGroupList = JSONUtil.parse(body).getByPath("data",
//                JSONArray.class).toList(JobGroup.class);
        List<JobGroup> jobGroupList = JSONUtil.parseObj(body).getJSONArray("data").toList(JobGroup.class);


        return jobGroupList;
    }

    /**
     * 新增执行器管理信息
     *
     * @return boolean
     * @author igodven
     * @params []
     * @date 2023/4/25 14:33
     */
    @Override
    public boolean autoRegisterGroup() {
        String url = customXxlJobConfig.getUrl() + "/jobgroup/save";
        RBucket<String> loginCookieRBucket = redissonClient.getBucket(
                "XXL_JOB_LOGIN_IDENTITY");
        String loginCookie = loginCookieRBucket.get();
        if (ObjectUtil.isEmpty(loginCookie)) {
            loginCookie = jobLoginService.login();
        }
        HttpResponse response = null;
        try {
            response = HttpUtil.createPost(url)
                    .form("appname", appName)
                    .form("title", title)
                    .cookie(loginCookie)
                    .execute();
        } catch (Exception e) {
            log.error("调用保存自动任务执行器信息接口失败:{}", e.getMessage());
            throw new RuntimeException("调用保存自动任务执行器信息接口失败");
        }
        Object code = JSONUtil.parse(response.body()).getByPath("code");
        return code.equals(200);
    }

    @Override
    public boolean preciselyCheck() {
        List<JobGroup> jobGroup = getJobGroup();
        Optional<JobGroup> has = jobGroup.stream()
                .filter(xxlJobGroup -> xxlJobGroup.getAppname().equals(appName))
                .findAny();
        return has.isPresent();
    }
}




