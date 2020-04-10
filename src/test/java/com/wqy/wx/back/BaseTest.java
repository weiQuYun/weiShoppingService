package com.wqy.wx.back;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.config.DecoderConfig.decoderConfig;
import static org.hamcrest.CoreMatchers.equalTo;

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
        RestAssured.baseURI = "http://127.0.0.1";
        RestAssured.port = port;
        RestAssured.config = RestAssured.config().decoderConfig(decoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .httpMultipartMode(HttpMultipartMode.BROWSER_COMPATIBLE));
        RequestSpecBuilder builder = new RequestSpecBuilder();
        getToken(builder);
        builder.setContentType("application/json;charset=utf-8");
        RestAssured.requestSpecification = builder.build();

    }

    private void getToken(RequestSpecBuilder builder) {
        String token;
        Map<String, String> user = new HashMap<>(5);
        user.put("userName", "admin");
        user.put("password", "admin");
        Response response = given().contentType("application/json;charset=utf-8").body(user).
                when().
                post("http://127.0.0.1:"+port+"/login/back");
        response.prettyPrint();
        token = response.then().body("code", equalTo("200")).extract().path("data.token").toString();
        String userId = response.then().body("code", equalTo("200")).extract().path("data.userId").toString();
        System.out.println("token:" + token);
        builder.addHeader("token", token);
        builder.addHeader("userId", userId);
    }

}
