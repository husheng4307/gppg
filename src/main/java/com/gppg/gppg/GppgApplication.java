package com.gppg.gppg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({
        "com.gppg.gppg.common.mapper",
        "com.gppg.gppg.student.mapper"
})
public class GppgApplication {

    public static void main(String[] args) {
        SpringApplication.run(GppgApplication.class, args);
    }

}
