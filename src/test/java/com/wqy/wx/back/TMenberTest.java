//package com.wqy.wx.back;//package com.wqy.wx.back;
//
//import com.wqy.wx.back.plus2.entity.TMenber;
//import io.restassured.response.Response;
//import org.junit.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.core.IsNull.notNullValue;
//
//public class TMenberTest extends BaseTest {
//    @Test
//    public void listMessageSendRule() {
//        TMenber tMenber = new TMenber();
//        Response response = given().
//                when().
//                get("/api/wqy/menber/list");
//        response.prettyPrint();
//        response.then().body("code", equalTo(200)).body("data", notNullValue());
//    }
//}
