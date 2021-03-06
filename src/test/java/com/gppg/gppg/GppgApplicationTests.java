package com.gppg.gppg;

import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.entity.FrontUserDomain;
import com.gppg.gppg.common.service.BackUserService;
import com.gppg.gppg.common.service.FrontUserService;
import com.gppg.gppg.common.util.MD5;
import com.gppg.gppg.student.entity.vo.PersonalInfoVo;
import com.gppg.gppg.student.service.IQueryPointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GppgApplicationTests {

    @Autowired
    private BackUserService backUserService;
    @Autowired
    private FrontUserService frontUserService;
    @Autowired
    IQueryPointService iQueryPointService;

    @Test
    public void addBackUser(){
        BackUserDomain domain = new BackUserDomain();

        String salt = MD5.randomString();
        //加盐
        String passWithSalt = MD5.md5("123456",salt);

        domain.setAccount("root1");
        domain.setPassword(passWithSalt);
        domain.setSalt(salt);
        domain.setName("aotuman");
        domain.setSchoolId(1);
        domain.setCreateUserId(1);

        backUserService.save(domain);
    }

    @Test
    public void addFrontUser(){
        FrontUserDomain domain = new FrontUserDomain();

        String salt = MD5.randomString();
        //加盐
        String passWithSalt = MD5.md5("123456",salt);

        domain.setName("yang");
        domain.setSchoolId(1);
        domain.setAcademyId(1);
        domain.setphoneNumber("123123");
        domain.setOpenId("openid123");
        domain.setPassword(passWithSalt);
        domain.setSalt(salt);

        frontUserService.save(domain);
    }

    @Test
    public void list(){
        List<FrontUserDomain> list = frontUserService.list();
        for (FrontUserDomain domain : list) {
            System.out.println(domain);
        }
    }

    @Test
    public void test11() {
        List<PersonalInfoVo> list = iQueryPointService.queryPersonalInfo(10);
        System.out.println(list);
    }

}
