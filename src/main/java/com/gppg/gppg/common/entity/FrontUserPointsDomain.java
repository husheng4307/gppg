package com.gppg.gppg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: Yang
 * date: 2020/9/3 16:48
 * des: 用户积分表
 */
@TableName("front_user_points")
public class FrontUserPointsDomain {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    /**
     * 前端用户id
     */
    @TableId(value = "front_user_id")
    int frontUserId;
    /**
     * 积分
     */
    int point;
    /**
     * 已兑换积分
     */
    @TableId(value = "exchanged_point")
    int exchangedPoint;
    /**
     * 是否删除
     */
    @TableId(value = "is_deleted")
    int isDeleted;

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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getExchangedPoint() {
        return exchangedPoint;
    }

    public void setExchangedPoint(int exchangedPoint) {
        this.exchangedPoint = exchangedPoint;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
