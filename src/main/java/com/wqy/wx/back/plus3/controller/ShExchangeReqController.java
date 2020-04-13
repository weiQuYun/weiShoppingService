package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShExchangeReq;
import com.wqy.wx.back.plus3.service.IShExchangeReqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/downloadFile", produces = "application/json;charset=UTF-8")
    @ApiOperation("下载文件-按日期区，一天只生成一次，历史请选择日期，默认当天")
    @ApiImplicitParam(value = "日期-年月日",name = "date",dataType = "String")
     public void downloadFile(@RequestParam(value = "date",required = false) String date, HttpServletResponse response){
         iShExchangeReqService.downloadFile(date,response);
     }
}
