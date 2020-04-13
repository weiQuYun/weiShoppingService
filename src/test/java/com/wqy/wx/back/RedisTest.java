package com.wqy.wx.back;

import com.wqy.wx.back.configer.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: RedisTest
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/9 17:36
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 删除所有缓存
     */
    @Test
    public void fun1(){
        System.out.println("已清除缓存个数："+redisUtil.removeAll());
    }
}
