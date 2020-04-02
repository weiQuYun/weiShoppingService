package com.wqy;

import com.alibaba.druid.support.json.JSONUtils;
import com.wqy.modules.system.dto.input.UserQueryPara;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
public class UserTest extends BaseTest {
    @Test
    public void listMessageSendRule() {
        UserQueryPara userQueryPara = new UserQueryPara();
        userQueryPara.setLimit(10);
        userQueryPara.setPage(1);
        Response response = given().body(userQueryPara).
                when().
                post("/login/shGoods/search");
        response.prettyPrint();
        response.then().body("code", equalTo(200)).body("data", notNullValue());
    }
}
