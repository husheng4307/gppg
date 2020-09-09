package com.gppg.gppg.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.PointRecordsDomain;

/**
 * @author: Yang
 * date: 2020/9/3 20:59
 * des:
 */
public interface IUsePointService extends IService<PointRecordsDomain> {

    /**
     * 学生使用积分申请兑换
     * @param frontUser
     * @param id
     * @param sum
     */
    void studentUsePoint(FrontUserDomain frontUser, int id, int sum);
}
