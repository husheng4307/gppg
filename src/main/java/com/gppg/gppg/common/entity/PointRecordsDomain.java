package com.gppg.gppg.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/3 16:54
 * des:
 */
@TableName("point_records")
public class PointRecordsDomain {
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
     * 图片存放位置
     */
    @TableId(value = "image_path")
    String imagePath;
    /**
     * 创建时间
     */
    @TableId(value = "create_time")
    Date createTime;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
