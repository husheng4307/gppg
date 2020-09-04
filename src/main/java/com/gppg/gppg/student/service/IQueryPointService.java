package com.gppg.gppg.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.student.entity.dto.StudentPointDto;

/**
 * @author: Yang
 * date: 2020/9/3 10:07
 * des:
 */
public interface IQueryPointService extends IService<FrontUserPointsDomain> {
    /**
     * 学生查询自身积分
     * @param id
     * @return
     */
    StudentPointDto studentQueryPoint(int id);
}
