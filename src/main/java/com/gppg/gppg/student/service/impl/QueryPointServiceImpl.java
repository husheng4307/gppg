package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.entity.FrontUserPointsDomain;
import com.gppg.gppg.common.entity.response.ResponseType;
import com.gppg.gppg.student.entity.dto.StudentPointDto;
import com.gppg.gppg.student.entity.exception.CommonException;
import com.gppg.gppg.student.entity.vo.PersonalInfoVo;
import com.gppg.gppg.student.mapper.QueryPoint;
import com.gppg.gppg.student.service.IQueryPointService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public StudentPointDto studentQueryPoint(int id) throws RuntimeException{
        // 封装对象
        try {
            FrontUserPointsDomain studentPointDomain = null;
            StudentPointDto studentPointDto = new StudentPointDto();
            studentPointDomain = this.baseMapper.queryStudentPoint(id);
            System.out.println(studentPointDomain);
            if (studentPointDomain == null) {
                return new StudentPointDto(0,0,0);
            } else {
                studentPointDto.setNowPoint(studentPointDomain.getPoint() - studentPointDomain.getExchangedPoint());
                studentPointDto.setSumPoint(studentPointDomain.getPoint());
                studentPointDto.setUsedPoint(studentPointDomain.getExchangedPoint());
                return studentPointDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ResponseType.FAILED,"数据查询失败");
        }
    }

    /**
     * 学生查询自身信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<PersonalInfoVo> queryPersonalInfo(int userId) {
        List<PersonalInfoVo> list = this.baseMapper.queryPersonalInfo(userId);
        return list;
    }
}
