package com.ichong.pojo.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName xxl_job_info
 */

public class JobInfo implements Serializable {
    /**
     * 
     */

    private Integer id;

    /**
     * 执行器主键ID
     */

    private Integer jobGroup;

    /**
     * 
     */

    private String jobDesc;

    /**
     * 
     */

    private Date addTime;

    /**
     * 
     */

    private Date updateTime;

    /**
     * 作者
     */

    private String author;

    /**
     * 报警邮件
     */

    private String alarmEmail;

    /**
     * 调度类型
     */

    private String scheduleType;

    /**
     * 调度配置，值含义取决于调度类型
     */

    private String scheduleConf;

    /**
     * 调度过期策略
     */

    private String misfireStrategy;

    /**
     * 执行器路由策略
     */

    private String executorRouteStrategy;

    /**
     * 执行器任务handler
     */

    private String executorHandler;

    /**
     * 执行器任务参数
     */

    private String executorParam;

    /**
     * 阻塞处理策略
     */

    private String executorBlockStrategy;

    /**
     * 任务执行超时时间，单位秒
     */

    private Integer executorTimeout;

    /**
     * 失败重试次数
     */

    private Integer executorFailRetryCount;

    /**
     * GLUE类型
     */

    private String glueType;

    /**
     * GLUE源代码
     */

    private String glueSource;

    /**
     * GLUE备注
     */

    private String glueRemark;

    /**
     * GLUE更新时间
     */

    private Date glueUpdatetime;

    /**
     * 子任务ID，多个逗号分隔
     */

    private String childJobid;

    /**
     * 调度状态：0-停止，1-运行
     */

    private Integer triggerStatus;

    /**
     * 上次调度时间
     */

    private Long triggerLastTime;

    /**
     * 下次调度时间
     */

    private Long triggerNextTime;


    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 执行器主键ID
     */
    public Integer getJobGroup() {
        return jobGroup;
    }

    /**
     * 执行器主键ID
     */
    public void setJobGroup(Integer jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * 
     */
    public String getJobDesc() {
        return jobDesc;
    }

    /**
     * 
     */
    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    /**
     * 
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 报警邮件
     */
    public String getAlarmEmail() {
        return alarmEmail;
    }

    /**
     * 报警邮件
     */
    public void setAlarmEmail(String alarmEmail) {
        this.alarmEmail = alarmEmail;
    }

    /**
     * 调度类型
     */
    public String getScheduleType() {
        return scheduleType;
    }

    /**
     * 调度类型
     */
    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    /**
     * 调度配置，值含义取决于调度类型
     */
    public String getScheduleConf() {
        return scheduleConf;
    }

    /**
     * 调度配置，值含义取决于调度类型
     */
    public void setScheduleConf(String scheduleConf) {
        this.scheduleConf = scheduleConf;
    }

    /**
     * 调度过期策略
     */
    public String getMisfireStrategy() {
        return misfireStrategy;
    }

    /**
     * 调度过期策略
     */
    public void setMisfireStrategy(String misfireStrategy) {
        this.misfireStrategy = misfireStrategy;
    }

    /**
     * 执行器路由策略
     */
    public String getExecutorRouteStrategy() {
        return executorRouteStrategy;
    }

    /**
     * 执行器路由策略
     */
    public void setExecutorRouteStrategy(String executorRouteStrategy) {
        this.executorRouteStrategy = executorRouteStrategy;
    }

    /**
     * 执行器任务handler
     */
    public String getExecutorHandler() {
        return executorHandler;
    }

    /**
     * 执行器任务handler
     */
    public void setExecutorHandler(String executorHandler) {
        this.executorHandler = executorHandler;
    }

    /**
     * 执行器任务参数
     */
    public String getExecutorParam() {
        return executorParam;
    }

    /**
     * 执行器任务参数
     */
    public void setExecutorParam(String executorParam) {
        this.executorParam = executorParam;
    }

    /**
     * 阻塞处理策略
     */
    public String getExecutorBlockStrategy() {
        return executorBlockStrategy;
    }

    /**
     * 阻塞处理策略
     */
    public void setExecutorBlockStrategy(String executorBlockStrategy) {
        this.executorBlockStrategy = executorBlockStrategy;
    }

    /**
     * 任务执行超时时间，单位秒
     */
    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    /**
     * 任务执行超时时间，单位秒
     */
    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    /**
     * 失败重试次数
     */
    public Integer getExecutorFailRetryCount() {
        return executorFailRetryCount;
    }

    /**
     * 失败重试次数
     */
    public void setExecutorFailRetryCount(Integer executorFailRetryCount) {
        this.executorFailRetryCount = executorFailRetryCount;
    }

    /**
     * GLUE类型
     */
    public String getGlueType() {
        return glueType;
    }

    /**
     * GLUE类型
     */
    public void setGlueType(String glueType) {
        this.glueType = glueType;
    }

    /**
     * GLUE源代码
     */
    public String getGlueSource() {
        return glueSource;
    }

    /**
     * GLUE源代码
     */
    public void setGlueSource(String glueSource) {
        this.glueSource = glueSource;
    }

    /**
     * GLUE备注
     */
    public String getGlueRemark() {
        return glueRemark;
    }

    /**
     * GLUE备注
     */
    public void setGlueRemark(String glueRemark) {
        this.glueRemark = glueRemark;
    }

    /**
     * GLUE更新时间
     */
    public Date getGlueUpdatetime() {
        return glueUpdatetime;
    }

    /**
     * GLUE更新时间
     */
    public void setGlueUpdatetime(Date glueUpdatetime) {
        this.glueUpdatetime = glueUpdatetime;
    }

    /**
     * 子任务ID，多个逗号分隔
     */
    public String getChildJobid() {
        return childJobid;
    }

    /**
     * 子任务ID，多个逗号分隔
     */
    public void setChildJobid(String childJobid) {
        this.childJobid = childJobid;
    }

    /**
     * 调度状态：0-停止，1-运行
     */
    public Integer getTriggerStatus() {
        return triggerStatus;
    }

    /**
     * 调度状态：0-停止，1-运行
     */
    public void setTriggerStatus(Integer triggerStatus) {
        this.triggerStatus = triggerStatus;
    }

    /**
     * 上次调度时间
     */
    public Long getTriggerLastTime() {
        return triggerLastTime;
    }

    /**
     * 上次调度时间
     */
    public void setTriggerLastTime(Long triggerLastTime) {
        this.triggerLastTime = triggerLastTime;
    }

    /**
     * 下次调度时间
     */
    public Long getTriggerNextTime() {
        return triggerNextTime;
    }

    /**
     * 下次调度时间
     */
    public void setTriggerNextTime(Long triggerNextTime) {
        this.triggerNextTime = triggerNextTime;
    }
}