package com.ichong.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 陈高文
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XxlRegister {

    //调用配置；FIX_RATE时，值30;   CRON时：* 1-2 * * * ?
    String scheduleConf() default "* 1-2 * * * ?";

    //自动任务执行类型  FIX_RATE：速率执行，以秒为单位 CRON: 定时执行
    String scheduleType() default "CRON";

    //任务描述
    String jobDesc() default "任务描述";

    //作者
    String author() default "ichong";

    //任务状态 0:停止 ; 1:运行
    int triggerStatus() default 0;

    /**
     * 运行模式:
     * BEAN:  Bean模式任务，支持基于类的开发方式，每个任务对应一个Java类。
     * GLUE(Java): 任务以源码方式维护在调度中心，支持通过Web IDE在线更新，实时编译和生效，因此不需要指定JobHandler
     * GLUE(Shell): 该模式的任务实际上是一段 “shell” 脚本
     * GLUE(Python): 该模式的任务实际上是一段 “python” 脚本；
     * GLUE(NodeJS): 该模式的任务实际上是一段 “nodeJS” 脚本；
     * GLUE(PHP): 同上
     */
    String glueType() default "BEAN";

    /**
     * SERIAL_EXECUTION:单机串行
     * DISCARD_LATER:丢弃后续调度
     * COVER_EARLY:覆盖之前调度
     */
    String executorBlockStrategy() default "SERIAL_EXECUTION";

    //失败重试次数
    int executorFailRetryCount() default 0;

    //任务超时时间
    int executorTimeout() default 30;

    /**
     * FIRST：(I18nUtil.getString("jobconf_route_first"), new  ExecutorRouteFirst()),
     * LAST：(I18nUtil.getString("jobconf_route_last"), new ExecutorRouteLast()),
     * ROUND：(I18nUtil.getString("jobconf_route_round"), new ExecutorRouteRound()),
     * RANDOM：(I18nUtil.getString("jobconf_route_random"), new ExecutorRouteRandom()),
     * CONSISTENT_HASH：(I18nUtil.getString("jobconf_route_consistenthash"), new ExecutorRouteConsistentHash()),
     * LEAST_FREQUENTLY_USED：(I18nUtil.getString("jobconf_route_lfu"), new ExecutorRouteLFU()),
     * LEAST_RECENTLY_USED：(I18nUtil.getString("jobconf_route_lru"), new ExecutorRouteLRU()),
     * FAILOVER：(I18nUtil.getString("jobconf_route_failover"), new ExecutorRouteFailover()),
     * BUSYOVER：(I18nUtil.getString("jobconf_route_busyover"), new ExecutorRouteBusyover()),
     * SHARDING_BROADCAST：(I18nUtil.getString("jobconf_route_shard"), null);
     */
    String executorRouteStrategy() default "FIRST";


    //调度过期策略；DO_NOTHING：忽略  FIRE_ONCE_NOW：立即执行一次
    String misfireStrategy() default "DO_NOTHING";

    //告警邮件地址，多个邮件地址用逗号分割
    String alarmEmail() default "";

    //任务执行参数
    String executorParam() default "";
}