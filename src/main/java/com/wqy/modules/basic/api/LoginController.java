package com.wqy.modules.basic.api;

import com.wqy.modules.common.Constant;
import com.wqy.modules.common.api.BaseController;
import com.wqy.modules.common.dto.output.ApiResult;
import com.wqy.modules.shopping.entity.ShUser;
import com.wqy.modules.shopping.service.IShUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p> 首页 </p>
 *
 * @author : wqy
 * @description :
 * @date : 2019/8/19 13:49
 */
@RestController
@Api(description = "登陆-接口")
@RequestMapping(Constant.BACK)
public class LoginController extends BaseController {
    @Autowired
    private IShUserService shUserService;



    @PostMapping(value = "/login")
    public ApiResult backLogin(@RequestBody ShUser user) {

        ShUser shUser = shUserService.login(user.getUsername(),user.getPassword());
       if(shUser!=null){
           return ApiResult.ok(400,"登录失败",null);
       }

        return ApiResult.ok(200,"登录成功", user);
    }

    @GetMapping(value = "/wx/login")
    @ApiOperation(value = "微信登陆")
    public ApiResult wxLogin() {
        return ApiResult.ok("登录系统成功", null);
    }
}
