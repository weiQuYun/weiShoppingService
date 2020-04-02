package com.wqy;

import com.wqy.config.Constants;
import com.wqy.modules.shopping.service.IShUserService;
import com.wqy.modules.system.entity.User;
import com.wqy.modules.system.mapper.UserMapper;
import com.wqy.utils.PasswordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StartStartApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    IShUserService iShUserService;
    @Autowired
    private BaseTest baseTest;

    @Test
    public void contextLoads() {
    }

    /**
     * 对密码加密
     *
     * @param :
     * @return: void
     */
    @Test
    public void test() throws Throwable {
    }

}
