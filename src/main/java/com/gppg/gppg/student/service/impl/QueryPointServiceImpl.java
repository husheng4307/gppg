package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.student.entity.dto.StudentPointDto;
import com.gppg.gppg.student.mapper.QueryPoint;
import com.gppg.gppg.student.service.IQueryPointService;
import org.springframework.stereotype.Service;

/**
 * @author: Yang
 * date: 2020/9/3 10:09
 * des:
 */
@Service
public class QueryPointServiceImpl extends ServiceImpl<QueryPoint, FrontUserPointsDomain> implements IQueryPointService {
    /**
     * 学生查询自身积分
     *
     * @param id
     * @return
     */
    @Override
    public StudentPointDto studentQueryPoint(int id) {
        // 封装对象
        try {
            FrontUserPointsDomain studentPointDomain = null;
            StudentPointDto studentPointDto = new StudentPointDto();
            studentPointDomain = this.baseMapper.queryStudentPoint(id);
            System.out.println(studentPointDomain);
            studentPointDto.setNowPoint(studentPointDomain.getPoint() - studentPointDomain.getExchangedPoint());
            studentPointDto.setSumPoint(studentPointDomain.getPoint());
            studentPointDto.setUsedPoint(studentPointDomain.getExchangedPoint());
            return studentPointDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
