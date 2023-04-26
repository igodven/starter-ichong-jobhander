package com.ichong.service;

import com.ichong.pojo.entity.JobGroup;

import java.util.List;


/**
 * @author 陈高文
 * @description 针对表【xxl_job_group】的数据库操作Service
 * @createDate 2023-04-24 15:59:00
 */
public interface JobGroupService {

    List<JobGroup> getJobGroup();

    boolean autoRegisterGroup();

    boolean preciselyCheck();
}
