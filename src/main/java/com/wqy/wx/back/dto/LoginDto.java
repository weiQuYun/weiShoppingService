package com.wqy.wx.back.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: LoginDto
 * @Description: TODO
 * @Author licm
 * @Date 2020/3/5 13:57
 * @Version V1.0
 * @Explain :
 **/
@ApiModel
@Data
public class LoginDto implements Serializable {
    @ApiModelProperty("用户名")
    @Mapping("username")
    private String userName;
    @ApiModelProperty("密码")
    private String password;

}
