package com.gppg.gppg.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gppg.gppg.common.entity.AcademyDomain;
import com.gppg.gppg.common.entity.AllSchoolDomain;
import com.gppg.gppg.student.entity.dto.SchoolAndAcademyDto;
import com.gppg.gppg.student.mapper.RegisterMapper;
import com.gppg.gppg.student.service.IRegisterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Yang
 * date: 2020/9/3 15:36
 * des:
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, AllSchoolDomain> implements IRegisterService {

    @Override
    public List<AllSchoolDomain> allSchoolAndAcademy() {
        try {
            List<AllSchoolDomain> school = this.baseMapper.allSchool();
            List<AcademyDomain> academy = this.baseMapper.allAcademy();

            for (int i = 0; i < school.size(); i++) {
                List<AcademyDomain> list = new ArrayList<>();
                for (int j = 0; j < academy.size(); j++) {
                    if (school.get(i).getId() == academy.get(j).getSchoolId()) {
                        list.add(academy.get(j));
                    }
                }
                school.get(i).setAcademy(list);
            }

            return school;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
