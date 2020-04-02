package com.wqy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2

//开启访问接口文档的权限
@ConditionalOnExpression("${swagger.enable}")
public class SwaggerConfiguration {
    @Value("${swagger.basePackage}")
    private String basePackage;
    @Value("${swagger.groupName}")
    private String groupName;
    @Value("${swagger.version}")
    private String version;
    @Bean
    public Docket userRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //模块名称
                .groupName(groupName)
                .apiInfo(apiInfo())
                .select()
                //扫描的控制器路径
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("服务:发布为 " + groupName + " 后台 APIs")
                .description("服务:发布为 " + groupName + " 后台 APIs")
                .termsOfServiceUrl("http://localhost:60010/swagger-ui.html")
                //作者  邮箱等
                .contact(new Contact("后端服务", "http://qingxuan.com", "2940517905@qq.com"))
                .version(version)
                .build();
    }
}
