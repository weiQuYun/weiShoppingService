package com.wqy.wx.back.configer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: Req
 * @Description: TODO
 * @Author licm
 * @Date 2019/12/25 12:32
 * @Version V1.0
 * @Explain : 头部封装信息
 **/
@Data
@ApiIgnore
public class Req implements Serializable {
    @ApiModelProperty(value = "请求令牌", hidden = false)
    private String token;
    @ApiModelProperty(value = "请求ip", hidden = false)
    private String ip;
    @ApiModelProperty(value = "请求用户id", hidden = false)
    private String userId;
    @ApiModelProperty(value = "请求用户名称", hidden = false)
    private String userName;
    @ApiModelProperty(value = "请求用户类型", hidden = false)
    private String userType;
    @ApiModelProperty(value = "请求用户父id", hidden = false)
    private String parentId;
    @ApiModelProperty(value = "请求用户分享码", hidden = false)
    private String sharNumber;
//    @ApiModelProperty(value = "请求用户归属店铺", hidden = false)
//    private String shopId;
}
