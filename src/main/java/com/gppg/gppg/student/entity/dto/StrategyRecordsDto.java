package com.gppg.gppg.student.entity.dto;

import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/3 15:14
 * des:
 */
public class StrategyRecordsDto {
    int countApplication;
    int isApproved;
    Date timeApplication;
    Date timeApproved;
    String strategyName;
    Date createTime;

    public int getCountApplication() {
        return countApplication;
    }

    public void setCountApplication(int countApplication) {
        this.countApplication = countApplication;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public Date getTimeApplication() {
        return timeApplication;
    }

    public void setTimeApplication(Date timeApplication) {
        this.timeApplication = timeApplication;
    }

    public Date getTimeApproved() {
        return timeApproved;
    }

    public void setTimeApproved(Date timeApproved) {
        this.timeApproved = timeApproved;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
