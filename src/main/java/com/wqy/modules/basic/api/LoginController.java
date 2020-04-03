package com.wqy.modules.basic.api;

import com.wqy.modules.common.Constant;
import com.wqy.modules.common.api.BaseController;
import com.wqy.modules.common.dto.output.ApiResult;
import com.wqy.modules.shopping.entity.ShUser;
import com.wqy.modules.shopping.service.IShUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 首页 </p>
 *
 * @author : wqy
 * @description :
 * @date : 2019/8/19 13:49
 */
@RestController
@Api(description = "登陆-接口")
@RequestMapping(Constant.API)
public class LoginController extends BaseController {
    @Autowired
    private IShUserService shUserService;
    @GetMapping(value = "/back/login")
    @ApiOperation(value = "后端登陆")
    public ApiResult backLogin(@RequestParam("userName") String userName,@RequestParam("pwd")  String pwd) {
        ShUser shUser = shUserService.login(userName,pwd);
        return ApiResult.ok("登录系统成功", shUser);
    }
    @GetMapping(value = "/wx/login")
    @ApiOperation(value = "微信登陆")
    public ApiResult wxLogin() {
        return ApiResult.ok("登录系统成功", null);
    }
}
