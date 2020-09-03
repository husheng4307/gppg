package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.student.entity.AllSchoolDomain;
import com.gppg.gppg.student.mapper.RegisterMapper;
import com.gppg.gppg.student.service.IRegisterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yang
 * date: 2020/9/3 15:36
 * des:
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, AllSchoolDomain> implements IRegisterService {

    @Override
    public List<AllSchoolDomain> allSchool() {
        try {
            List<AllSchoolDomain> list = this.baseMapper.allSchool();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
