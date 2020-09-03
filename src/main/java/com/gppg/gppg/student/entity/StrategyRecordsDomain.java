package com.gppg.gppg.student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.TableGenerator;
import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/3 11:38
 * des:
 */
@TableName("point_exchange_records")
public class StrategyRecordsDomain {
    /**
     * 自增主键，积分兑换记录ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    /**
     * 前端用户id
     */
    @TableId(value = "front_user_id")
    int frontUserId;
    /**
     * 积分兑换策略id
     */
    @TableId(value = "point_exchange_strategy")
    int pointExchangeStrategy;
    /**
     * 兑换数量
     */
    @TableId(value = "count_application")
    int countApplication;
    /**
     * 是否批准：0审核中、1审核通过、2审核未通过
     */
    @TableId(value = "is_approved")
    int isApproved;
    /**
     * 是否删除
     */
    @TableId(value = "is_deleted")
    int isDeleted;
    /**
     * 批准人id--后端用户id
     */
    @TableId(value = "back_user_id")
    int backUserId;
    /**
     * 申请时间
     */
    @TableId(value = "time_application")
    Date timeApplication;
    /**
     * 审核时间
     */
    @TableId(value = "time_approved")
    Date timeApproved;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrontUserId() {
        return frontUserId;
    }

    public void setFrontUserId(int frontUserId) {
        this.frontUserId = frontUserId;
    }

    public int getPointExchangeStrategy() {
        return pointExchangeStrategy;
    }

    public void setPointExchangeStrategy(int pointExchangeStrategy) {
        this.pointExchangeStrategy = pointExchangeStrategy;
    }

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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getBackUserId() {
        return backUserId;
    }

    public void setBackUserId(int backUserId) {
        this.backUserId = backUserId;
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
}
