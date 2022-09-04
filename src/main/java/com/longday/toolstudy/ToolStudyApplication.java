package com.longday.toolstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 君
 */
@SpringBootApplication
@MapperScan("com.longday.toolstudy.mapper")
public class ToolStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolStudyApplication.class, args);
    }

}
