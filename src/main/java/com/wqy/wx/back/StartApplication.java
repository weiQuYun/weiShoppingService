package com.wqy.wx.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;


@MapperScan("com.wqy.wx.back")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ServletComponentScan("com.wqy.wx.back")
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
        System.out.println("==========启动成功 ！=======");
    }

}
