package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.dto.MonthDto;
import com.wqy.wx.back.plus3.entity.MothMoney;
import com.wqy.wx.back.plus3.mapper.MothMoneyMapper;
import com.wqy.wx.back.plus3.service.IMonthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: MonthController
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/13 14:53
 * @Version V1.0
 */
@Api(tags = "月度分红")
@RestController
@RequestMapping(Constant.MAPPING + "/month")
public class MonthController {

    @Autowired
    private IMonthService iMonthService;
    /**
     * 查询消费记录
     */
    @GetMapping("/list")
    @ApiOperation("查询消费记录")
    public List<MonthDto> getList(MothMoney mothMoney){
        return iMonthService.getList(mothMoney);
    }
    @GetMapping(value = "/downloadFile", produces = "application/json;charset=UTF-8")
    @ApiOperation("下载文件-按日期区，一天只生成一次，历史请选择日期，默认当月")
    @ApiImplicitParam(value = "日期-年月202002",name = "date",dataType = "String")
    public void downloadFile(@RequestParam(value = "date",required = false) String date, HttpServletResponse response){
        iMonthService.downloadFile(date,response);
    }
}
