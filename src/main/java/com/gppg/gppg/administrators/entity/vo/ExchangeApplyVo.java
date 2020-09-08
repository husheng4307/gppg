package com.gppg.gppg.administrators.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: Yang
 * date: 2020/9/7 8:37
 * des: 管理员查看本校学生兑换申请
 */
@Data
@Accessors(chain = true)
public class ExchangeApplyVo {
    /**
     * 申请人名称
     */
    private String applicantName;
    /**
     * 前端用户id
     */
    private int frontUserId;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 商品名称
     */
    String goodsName;
    /**
     * 兑换记录ID
     */
    int exchangeRecordsId;
    /**
     * 商品ID
     */
    int goodsNum;
    /**
     * 申请状态
     */
    int applyState;
    /**
     * 申请时间
     */
    Date applyTime;
}
