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

/**
 * @author: Yang
 * date: 2020/9/3 15:36
 * des:
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, AllSchoolDomain> implements IRegisterService {

    @Override
    public List<SchoolAndAcademyDto> allSchoolAndAcademy() {
        try {
            List<AllSchoolDomain> school = this.baseMapper.allSchool();
            List<AcademyDomain> academy = this.baseMapper.allAcademy();
            ArrayList<SchoolAndAcademyDto> list = new ArrayList<>();
            ArrayList<SchoolAndAcademyDto.Academy> tmp = new ArrayList<>();

            for (int i = 0; i < school.size(); i++) {
                list.get(i).setXxid(school.get(i).getId());
                list.get(i).setXxmc(school.get(i).getSchoolName());
                for (int j = 0; j < academy.size(); j++) {
                    if (school.get(i).getId() == academy.get(j).getSchool_id()) {
                        tmp.get(i).setXyid(academy.get(j).getId());
                        tmp.get(i).setXymc(academy.get(j).getAcademy_name());
                    }
                }
                list.get(i).setXy(tmp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
