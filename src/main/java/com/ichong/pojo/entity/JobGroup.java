package com.ichong.pojo.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName xxl_job_group
 */

public class JobGroup implements Serializable {
    /**
     * 
     */

    private Integer id;

    /**
     * 执行器AppName
     */

    private String appname;

    /**
     * 执行器名称
     */

    private String title;

    /**
     * 执行器地址类型：0=自动注册、1=手动录入
     */

    private Integer addressType;

    /**
     * 执行器地址列表，多地址逗号分隔
     */

    private String addressList;

    /**
     * 
     */

    private Date updateTime;


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
     * 执行器AppName
     */
    public String getAppname() {
        return appname;
    }

    /**
     * 执行器AppName
     */
    public void setAppname(String appname) {
        this.appname = appname;
    }

    /**
     * 执行器名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 执行器名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 执行器地址类型：0=自动注册、1=手动录入
     */
    public Integer getAddressType() {
        return addressType;
    }

    /**
     * 执行器地址类型：0=自动注册、1=手动录入
     */
    public void setAddressType(Integer addressType) {
        this.addressType = addressType;
    }

    /**
     * 执行器地址列表，多地址逗号分隔
     */
    public String getAddressList() {
        return addressList;
    }

    /**
     * 执行器地址列表，多地址逗号分隔
     */
    public void setAddressList(String addressList) {
        this.addressList = addressList;
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
}