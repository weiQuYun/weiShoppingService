package com.wqy;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static org.hamcrest.Matchers.equalTo;

/**
 *
 * @ClassName: BaseTest
 * @Description: TODO
 * @Author xiongshibo
 * @Date 2019/7/10 17:05
 * @Version V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseTest {

    @LocalServerPort
    private int port;

    @Before
    public void setup() throws Throwable {
        String tenantId = "1";
        RestAssured.baseURI = "http://127.0.0.1";
        RestAssured.port = port;
        RestAssured.config = RestAssured.config().decoderConfig(decoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .httpMultipartMode(HttpMultipartMode.BROWSER_COMPATIBLE));
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("X-Token", getToken(tenantId));
        builder.setContentType("application/json;charset=utf-8");
        RestAssured.requestSpecification = builder.build();

    }

    private String getToken(String tenantId) {
        String token;
        Map<String, String> user = new HashMap<>(5);
        user.put("username", "admin");
        user.put("password", "123456");
        Response response = given().header("tenantId", tenantId).contentType("application/json;charset=utf-8").body(user).
                when().
                post("http://127.0.0.1:9101/login");
        token = response.then().body("code", equalTo(200)).extract().path("data.token").toString();
        System.out.println("X_Token:" + token);
        return token;
    }

}
