package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShExchangeReq;
import com.wqy.wx.back.plus3.service.IShExchangeReqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author licm
 * @since 2020-04-11
 */
@Api(tags = "积分兑换申请表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/exchange/req")
public class ShExchangeReqController {

    @Autowired
    private IShExchangeReqService iShExchangeReqService;
    @PostMapping("")
    @ApiOperation("添加提现请求")
    public Boolean request(ShExchangeReq shExchangeReq){
        return iShExchangeReqService.request(shExchangeReq);
    }
    @GetMapping("/list")
    @ApiOperation("条件查询提现请求")
    public List<ShExchangeReq> getList(ShExchangeReq shExchangeReq){
        return iShExchangeReqService.getList(shExchangeReq);
    }
    @PostMapping("/downloadFile")
    @ApiOperation("下载文件")
     public Boolean downloadFile(HttpServletResponse response){
        return iShExchangeReqService.downloadFile(response);
     }
}
