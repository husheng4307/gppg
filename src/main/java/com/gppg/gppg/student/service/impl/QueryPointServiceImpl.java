package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.student.entity.StudentPointDomain;
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
public class QueryPointServiceImpl extends ServiceImpl<QueryPoint, StudentPointDomain> implements IQueryPointService {
    /**
     * 学生查询自身积分
     *
     * @param id
     * @return
     */
    @Override
    public StudentPointDto studentQueryPoint(int id) {
        try {
            StudentPointDomain studentPointDomain = null;
            StudentPointDto studentPointDto = null;
            studentPointDomain = this.baseMapper.queryStudentPoint(id);
            studentPointDto.setNowPoint(studentPointDomain.getSumPoint() - studentPointDomain.getUsedPoint());
            studentPointDto.setSumPoint(studentPointDomain.getSumPoint());
            studentPointDto.setUsedPoint(studentPointDomain.getUsedPoint());

            return studentPointDto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
