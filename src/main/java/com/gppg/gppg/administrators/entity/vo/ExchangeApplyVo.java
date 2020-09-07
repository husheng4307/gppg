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
    private String applicantName;
    private int frontUserId;
    private String phoneNumber;
    String goodsName;
    int exchangeRecordsId;
    int goodsNum;
    int applyState;
    Date applyTime;
}
