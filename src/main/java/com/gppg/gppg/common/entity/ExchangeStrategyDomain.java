package com.gppg.gppg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/3 10:46
 * des:
 */
@TableName("point_exchange_strategy")
public class ExchangeStrategyDomain {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    /**
     * 学校id
     */
    @TableField(value = "school_id")
    int schoolId;
    /**
     * 策略名称
     */
    @TableField(value = "strategy_name")
    String strategyName;
    /**
     * 策略描述
     */
    @TableField(value = "strategy_description")
    String strategyDescription;
    /**
     * 兑换所需积分
     */
    @TableField(value = "point_accquired")
    int pointAccquired;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    Date createTime;
    /**
     * 创建者id
     */
    @TableField(value = "create_user_id")
    int createUserId;
    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
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
