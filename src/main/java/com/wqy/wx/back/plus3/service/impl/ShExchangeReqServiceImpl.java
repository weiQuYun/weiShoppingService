package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.CheckUtils;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.excel.ExportExcel;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.plus3.entity.ShExchangeReq;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.entity.ShMoney;
import com.wqy.wx.back.plus3.mapper.ShExchangeReqMapper;
import com.wqy.wx.back.plus3.mapper.ShMoneyMapper;
import com.wqy.wx.back.plus3.service.IShExchangeReqService;
import com.wqy.wx.back.plus3.service.IShMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public Boolean downloadFile(HttpServletResponse response) {
        createFile();
        //被下载的文件在服务器中的路径,
        String downloadFilePath = "/root/fileSavePath/";
        //被下载文件的名称
        String fileName = "demo.xml";
        File file = new File(downloadFilePath);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    private void   createFile(){
        QueryWrapper<ShExchangeReq> query = new QueryWrapper<>();
        query.eq("status",0);
        List<ShExchangeReq> staffs = this.list(query);
        Map<String,String> titleMap = new LinkedHashMap<>();
        titleMap.put("phone", "手机号");
        titleMap.put("exchangeIntegral", "提现积分");
        titleMap.put("createTime", "申请时间");
        String sheetName = "兑换信息导出";
        System.out.println("start导出");
        long start = System.currentTimeMillis();
        ExportExcel.excelExport(staffs, titleMap, sheetName);
        long end = System.currentTimeMillis();
        System.out.println("end导出");
        System.out.println("耗时："+(end-start)+"ms");
        this.updateBatchById(staffs);
    }
}
