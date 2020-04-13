package com.wqy.wx.back.plus3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.DateUtil;
import com.wqy.wx.back.common.util.ParamUtils;
import com.wqy.wx.back.common.util.excel.ExclDto;
import com.wqy.wx.back.common.util.excel.ExportExcel;
import com.wqy.wx.back.configer.exception.BizException;
import com.wqy.wx.back.dto.ExchangeReqDto;
import com.wqy.wx.back.dto.MonthDto;
import com.wqy.wx.back.plus3.entity.MothMoney;
import com.wqy.wx.back.plus3.entity.ShAttribute;
import com.wqy.wx.back.plus3.entity.ShExchangeReq;
import com.wqy.wx.back.plus3.entity.ShMember;
import com.wqy.wx.back.plus3.mapper.MothMoneyMapper;
import com.wqy.wx.back.plus3.mapper.ShAttributeMapper;
import com.wqy.wx.back.plus3.mapper.ShMemberMapper;
import com.wqy.wx.back.plus3.service.IMonthService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: MonthServiceImpl
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/13 15:06
 * @Version V1.0
 */
@Slf4j
@Service
@Primary
public class MonthServiceImpl extends ServiceImpl<MothMoneyMapper, MothMoney> implements IMonthService {
    @Autowired
    private MothMoneyMapper mothMoneyMapper;
    @Autowired
    private ShMemberMapper memberMapper;
    /**
     * 查询消费记录
     */
    @Override
    public List<MonthDto> getList(MothMoney mothMoney){
        QueryWrapper<MothMoney> query = new QueryWrapper<>();
        query = ParamUtils.reflect(mothMoney,query);
        List<MothMoney> mothMonies = mothMoneyMapper.selectList(query);
        List<MonthDto> list = new ArrayList<>();
        mothMonies.forEach(item->{
            ShMember member = memberMapper.selectByid(item.getMemberId());
            MonthDto dto = new MonthDto();
            dto.setCreateTime(item.getCreateTime());
            dto.setMemberId(item.getMemberId());
            dto.setUsername(member.getUsername());
            dto.setMoney(item.getMoney());
            dto.setPhone(member.getPhone());
            dto.setStatus(item.getStatus().toString());
            list.add(dto);
        });
        return list;
    }
    @Override
    public void downloadFile(String date, HttpServletResponse response){
        String fileName = null;
        File file = null;
        if (StringUtils.isNotBlank(date)){
            fileName = "消费信息导出_" +date+".xlsx";
            file = new File(Constant.FILE_PATH,fileName);
            if(!file.exists()){
                throw new BizException("文件不存在");
            }
        }else{
            fileName ="消费信息导出_" +DateUtil.dateToYMString(new Date())+".xlsx";
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

    private String  createFile(){
        List<ExclDto> list = new ArrayList<>();
        MothMoney mothMoney = new MothMoney();
        mothMoney.setStatus(0);
        List<MonthDto> monthDtos = this.getList(mothMoney);
        if (CollectionUtils.isEmpty(monthDtos)){
            throw new BizException("没有要导出的数据");
        }
        Map<String,String> titleMap1 = new LinkedHashMap<>();
        titleMap1.put("username", "用户名");
        titleMap1.put("phone", "手机号");
        titleMap1.put("money", "消费金额");
        titleMap1.put("addTime", "消费时间");
        String sheetName1 = "消费信息";
        list.add(new ExclDto(monthDtos,titleMap1,sheetName1));
        QueryWrapper<ShMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("integral_change_rate",Constant.TEN);
        List<ShMember> list2 = memberMapper.selectList(queryWrapper);
        Map<String,String> titleMap2 = new LinkedHashMap<>();
        titleMap2.put("username", "用户名");
        titleMap2.put("phone", "手机号");
        String sheetName2 = "满10次消费者信息";
        list.add(new ExclDto(list2,titleMap2,sheetName2));
        QueryWrapper<MothMoney> query = new QueryWrapper<>();
        query.eq("status",0);
        List<MothMoney> mothMonies = mothMoneyMapper.selectList(query);
        mothMonies.forEach(item->{
            item.setStatus(Constant.ONE);
        });
        ExportExcel exportExcel = new ExportExcel();
        if(this.updateBatchById(mothMonies)){
            monthDtos.forEach(item->{
                item.setAddTime(DateUtil.dateToString(item.getCreateTime()));
            });
            return  exportExcel.excelExport2("消费信息导出",list);
        }
        return null;
    }
}
