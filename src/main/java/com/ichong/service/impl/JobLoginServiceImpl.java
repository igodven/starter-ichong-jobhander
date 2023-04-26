package com.ichong.service.impl;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.ichong.config.CustomXxlJobConfig;
import com.ichong.service.JobLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: JobLoginServiceImpl
 * Description:
 *
 * @author 陈高文
 * @date 2023/4/24 15:37
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class JobLoginServiceImpl implements JobLoginService {

    private final RedissonClient redissonClient;
    private final CustomXxlJobConfig customXxlJobConfig;

    @Override
    public String login() {
        String url = customXxlJobConfig.getUrl() + "/login";
        HttpResponse response = null;
        try {
            response = HttpUtil.createPost(url)
                    .form("userName", customXxlJobConfig.getUsername())
                    .form("password", customXxlJobConfig.getPassword())
                    .execute();
        } catch (Exception e) {
            log.error("调用xxl-job登录接口报错:{}", e.getMessage());
            throw new RuntimeException("调用xxl-job登录接口报错");
        }
        List<HttpCookie> cookies = response.getCookies();
        Optional<HttpCookie> cookieOpt = cookies.stream()
                .filter(cookie -> cookie.getName().equals("XXL_JOB_LOGIN_IDENTITY")).findFirst();
        if (!cookieOpt.isPresent()) {
            throw new RuntimeException("获取xxl-job的cookie失败");
        }
        String value = "XXL_JOB_LOGIN_IDENTITY=" + cookieOpt.get().getValue();
        RBucket<String> loginCookie = redissonClient.getBucket(
                "XXL_JOB_LOGIN_IDENTITY");
        loginCookie.set(value, 30, TimeUnit.MINUTES);
        return value;
    }
}
