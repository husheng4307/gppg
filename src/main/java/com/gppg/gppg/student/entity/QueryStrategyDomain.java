package com.gppg.gppg.student.entity;

import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/3 10:46
 * des:
 */
public class QueryStrategyDomain {
    int id;
    int schoolId;
    String strategyName;
    String strategyDescription;
    int pointAccquired;
    Date createTime;
    int createUserId;
    int isDeleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyDescription() {
        return strategyDescription;
    }

    public void setStrategyDescription(String strategyDescription) {
        this.strategyDescription = strategyDescription;
    }

    public int getPointAccquired() {
        return pointAccquired;
    }

    public void setPointAccquired(int pointAccquired) {
        this.pointAccquired = pointAccquired;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
