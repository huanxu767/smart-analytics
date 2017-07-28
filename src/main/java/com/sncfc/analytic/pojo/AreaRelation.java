package com.sncfc.analytic.pojo;

import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 苏宁公司-消金大区关系
 */
public class AreaRelation {
    private Long id;
    private String begindate;
    private String enddate;
    private String strCode;
    private String strName;
    private String areaCode;
    private String areaName;
    private Date createDate;
    private String createId;
    /**
     * 1 生效 2 删除
     */
    private Integer status;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBegindate() {
        return begindate;
    }

    public void setBegindate(String begindate) {
        this.begindate = begindate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AreaRelation{" + "id=" + id + ", begindate='" + begindate + '\'' + ", enddate='" + enddate + '\'' + ", strCode='" + strCode + '\'' + ", strName='" + strName + '\'' + ", areaCode='" + areaCode + '\'' + ", areaName='" + areaName + '\'' + ", createDate=" + createDate + ", createId='" + createId + '\'' + ", status=" + status + ", updateTime=" + updateTime + '}';
    }

    public void formatDate() {
        if(!StringUtils.isEmpty(this.begindate)){
            this.begindate = this.begindate.replaceAll("-","");
        }
        if(!StringUtils.isEmpty(this.enddate)){
            this.enddate = this.enddate.replaceAll("-","");
        }
    }

}
