package com.ichong.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ichong.config.CustomXxlJobConfig;
import com.ichong.pojo.entity.JobInfo;
import com.ichong.service.JobInfoService;

import com.ichong.service.JobLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 陈高文
 * @description 针对表【xxl_job_info】的数据库操作Service实现
 * @createDate 2023-04-24 15:59:00
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JobInfoServiceImpl implements JobInfoService {

    private final CustomXxlJobConfig customXxlJobConfig;

    private final JobLoginService jobLoginService;

    private final RedissonClient redissonClient;


    @Override
    public List<JobInfo> getJobInfo(Integer jobGroupId, String executorHandler) {
        String url = customXxlJobConfig.getUrl() + "/jobinfo/pageList";
        RBucket<String> loginCookieRBucket = redissonClient.getBucket(
                "XXL_JOB_LOGIN_IDENTITY");
        String loginCookie = loginCookieRBucket.get();
        if (ObjectUtil.isEmpty(loginCookie)) {
            loginCookie = jobLoginService.login();
        }
        HttpResponse response;
        try {
            response = HttpRequest.post(url)
                    .form("jobGroup", jobGroupId)
                    .form("executorHandler", executorHandler)
                    .form("triggerStatus", -1)
                    .form("start", 0)
                    .form("length", 50)
                    .cookie(loginCookie)
                    .execute();
        } catch (Exception e) {
            log.error("根据执行器管理id获取任务信息失败:{}", e.getMessage());
            throw new RuntimeException("根据执行器管理id获取任务信息失败");
        }
        String body = response.body();
        JSONArray array = JSONUtil.parse(body).getByPath("data", JSONArray.class);
        List<JobInfo> list = array.stream()
                .map(o -> JSONUtil.toBean((JSONObject) o, JobInfo.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public Integer addJobInfo(JobInfo xxlJobInfo) {
        String url = customXxlJobConfig.getUrl() + "/jobinfo/add";
        Map<String, Object> paramMap = BeanUtil.beanToMap(xxlJobInfo);
        RBucket<String> loginCookieRBucket = redissonClient.getBucket(
                "XXL_JOB_LOGIN_IDENTITY");
        String loginCookie = loginCookieRBucket.get();
        if (ObjectUtil.isEmpty(loginCookie)) {
            loginCookie = jobLoginService.login();
        }
        HttpResponse response;
        try {
            response = HttpRequest.post(url)
                    .form(paramMap)
                    .cookie(loginCookie)
                    .execute();
        } catch (Exception e) {
            log.error("调用自动任务新增任务管理信息接口报错:{}", e.getMessage());
            throw new RuntimeException("调用自动任务新增任务管理信息接口报错");
        }

        JSON json = JSONUtil.parse(response.body());
        int code = (int) json.getByPath("code");
        if (HttpStatus.HTTP_OK == code) {
            return Convert.toInt(json.getByPath("content"));
        }
        throw new RuntimeException("新增任务管理信息报错");
    }

}




