package com.ichong.service;

import com.ichong.pojo.entity.JobInfo;

import java.util.List;

/**
 * @author 陈高文
 * @description 针对表【xxl_job_info】的数据库操作Service
 * @createDate 2023-04-24 15:59:00
 */
public interface JobInfoService {

    List<JobInfo> getJobInfo(Integer jobGroupId,
                             String executorHandler);

    Integer addJobInfo(JobInfo xxlJobInfo);
}
