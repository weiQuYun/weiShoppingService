package com.wqy.wx.back.plus3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wqy.wx.back.dto.MonthDto;
import com.wqy.wx.back.plus3.entity.MothMoney;
import com.wqy.wx.back.plus3.entity.ShAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: IMonthService
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/13 15:05
 * @Version V1.0
 */
public interface IMonthService extends IService<MothMoney> {
     List<MonthDto> getList(MothMoney mothMoney);

     void downloadFile( String date, HttpServletResponse response);

     void createFile();
}
