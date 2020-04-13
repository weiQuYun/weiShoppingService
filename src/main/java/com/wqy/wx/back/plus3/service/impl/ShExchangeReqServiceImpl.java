package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.CheckUtils;
import com.wqy.wx.back.common.util.DateUtil;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.dozer.IGenerator;
import com.wqy.wx.back.common.util.excel.ExportExcel;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.dto.ExchangeReqDto;
import com.wqy.wx.back.plus3.entity.ShExchangeReq;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShMoney;
import com.wqy.wx.back.plus3.mapper.ShExchangeReqMapper;
import com.wqy.wx.back.plus3.mapper.ShMoneyMapper;
import com.wqy.wx.back.plus3.service.IShExchangeReqService;
import com.wqy.wx.back.plus3.service.IShMemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 积分兑换申请表 服务实现类
 * </p>
 *
 * @author licm
 * @since 2020-04-11
 */
@Slf4j
@Primary
@Service
public class ShExchangeReqServiceImpl extends ServiceImpl<ShExchangeReqMapper, ShExchangeReq> implements IShExchangeReqService {

    @Autowired
    private IGenerator generator;
    @Autowired
    private IShMemberService memberService;
    @Autowired
    private ShMoneyMapper moneyMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = BizException.class)
    @Override
    public Boolean request(ShExchangeReq shExchangeReq) {
        CheckUtils.isObjectBlank(shExchangeReq,"兑现请求");
        CheckUtils.isStrBlank(shExchangeReq.getMemberId(),"兑现用户");
        CheckUtils.isStrBlank(shExchangeReq.getPhone(),"手机号", Constant.REGEX_MOBILE);
        CheckUtils.isObjectBlank(shExchangeReq.getExchangeIntegral(),"兑现用户");
        ShMember shMember = memberService.selectById(shExchangeReq.getMemberId());
        ShMoney money = moneyMapper.selectById(shExchangeReq.getMemberId());
        if(null==shMember||null==money){
            throw new BizException("用户账户异常");
        }
        if(money.getAmount().compareTo(new BigDecimal("300"))<=0){
            throw new BizException("账户金额不满足提现标准");
        }
        if(shExchangeReq.getExchangeIntegral().compareTo(money.getAmount())>0){
            throw new BizException("账户金额不足");
        }
        shExchangeReq.setStatus(0);
        money.setAmount(money.getAmount().subtract(shExchangeReq.getExchangeIntegral()));
        money.updateById();
        return shExchangeReq.insert();
    }

    @Override
    public List<ShExchangeReq> getList(ShExchangeReq shExchangeReq) {
        QueryWrapper<ShExchangeReq> query = new QueryWrapper<>();
        query = ParamUtils.reflect(shExchangeReq,query);
        return this.list(query);
    }

    @Override
    public void downloadFile(String date,HttpServletResponse response) {
        String fileName = null;
        File file = null;
        if (StringUtils.isNotBlank(date)){
            fileName = "兑换信息导出_" +date+".xlsx";
            file = new File(Constant.FILE_PATH,fileName);
            if(!file.exists()){
                throw new BizException("文件不存在");
            }
        }else{
            fileName ="兑换信息导出_" +DateUtil.dateToYMDString(new Date())+".xlsx";
             file = new File(Constant.FILE_PATH,fileName);
             log.info("文件不存在则生成");
            if(!file.exists()){
                fileName = createFile();
            }
        }
        try(
                InputStream inputStream = new FileInputStream(new File(Constant.FILE_PATH,fileName));
                OutputStream outputStream = response.getOutputStream()
        ){
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment; fileName="+  fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
            throw new BizException("文件下载失败");
        }
    }

    private String   createFile(){
        QueryWrapper<ShExchangeReq> query = new QueryWrapper<>();
        query.eq("status",0);
        List<ShExchangeReq> staffs = this.list(query);
        if (CollectionUtils.isEmpty(staffs)){
            throw new BizException("没有要兑换的数据");
        }
        Map<String,String> titleMap = new LinkedHashMap<>();
        titleMap.put("phone", "手机号");
        titleMap.put("exchangeIntegral", "提现积分");
        titleMap.put("createTime", "申请时间");
        String sheetName = "兑换信息导出";
        System.out.println("start导出");
        long start = System.currentTimeMillis();
        ExportExcel exportExcel = new ExportExcel();
        long end = System.currentTimeMillis();
        System.out.println("end导出");
        System.out.println("耗时："+(end-start)+"ms");
        staffs.forEach(item->{
            item.setStatus(Constant.ONE);
        });
        if(this.updateBatchById(staffs)){
            List<ExchangeReqDto> list = new ArrayList<>();
            staffs.forEach(item->{
                ExchangeReqDto dto = generator.convert(item,ExchangeReqDto.class);
                dto.setCreateTime(DateUtil.dateToString(item.getCreateTime()));
                list.add(dto);
            });
            return  exportExcel.excelExport("兑换信息导出",list, titleMap, sheetName);
        }
        return null;
    }
}
