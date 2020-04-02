package com.wqy;

import com.wqy.config.MyProperties;
import com.wqy.utils.ApplicationContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@EnableConfigurationProperties({MyProperties.class})
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.wqy.modules.shopping.mapper")
public class StartApplication {

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(StartApplication.class, args);
        ApplicationContextUtil.setApplicationContext(app);
        System.out.println("====启动成功====");
    }

}
