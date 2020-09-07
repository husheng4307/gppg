package com.gppg.gppg.student.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.student.entity.dto.StudentPointDto;
import com.gppg.gppg.student.entity.vo.PersonalInfoVo;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 10:07
 * des:
 */
public interface IQueryPointService extends IService<FrontUserPointsDomain> {
    /**
     * 学生查询自身积分
     *
     * @param id
     * @return
     */
    StudentPointDto studentQueryPoint(int id);

    /**
     * 学生查询自身信息
     *
     * @param userId
     * @return
     */
    List<PersonalInfoVo> queryPersonalInfo(int userId);
}
