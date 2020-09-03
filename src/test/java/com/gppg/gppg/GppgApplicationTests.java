package com.gppg.gppg;

import com.gppg.gppg.common.entity.BackUserDomain;
import com.gppg.gppg.common.service.BackUserService;
import com.gppg.gppg.common.util.MD5;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GppgApplicationTests {

    @Autowired
    private BackUserService backUserService;

    @Test
    public void addBackUser(){
        BackUserDomain domain = new BackUserDomain();

        String salt = MD5.randomString();
        //加盐
        String passWithSalt = MD5.md5("123456",salt);

        domain.setAccount("root");
        domain.setPassword(passWithSalt);
        domain.setSalt(salt);
        domain.setName("aotuman");
        domain.setSchoolId(1);
        domain.setCreateUserId(1);

        backUserService.save(domain);
    }

}
