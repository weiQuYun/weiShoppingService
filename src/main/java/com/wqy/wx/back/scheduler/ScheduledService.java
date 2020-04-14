package com.wqy.wx.back.scheduler;

import com.wqy.wx.back.plus3.service.IMonthService;
import com.wqy.wx.back.plus3.service.IShRankService;
import com.wqy.wx.back.plus3.service.IShUserSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 成都微趣云网络科技有限公司
 *
 * @ClassName: ScheduledService
 * @Description: TODO
 * @Author licm
 * @Date 2020/4/10 15:41
 * @Version V1.0
 */
@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class ScheduledService {
    @Autowired
    private IShRankService shRankService;
    @Autowired
    private IMonthService iMonthService;
    @Autowired
    private IShUserSignService iShUserSignService;

    @Scheduled(cron = "0 0 0 15 * ?")//每月15号触发
    public void scheduled() {
        log.info("=====>>>>>开始计算排行榜  {}", System.currentTimeMillis());
        shRankService.generateRank();
        log.info("=====>>>>>结束计算排行榜  {}", System.currentTimeMillis());
    }
    @Scheduled(cron = "0 0 0 15 * ?")//每月15号触发
    public void scheduled2() {
        log.info("=====>>>>>开始生成消费记录  {}", System.currentTimeMillis());
        iMonthService.createFile();
        log.info("=====>>>>>结束生成消费记录  {}", System.currentTimeMillis());
    }
    @Scheduled(cron = "0 0 1 * * ?")//每天0点触发
    public void scheduled3() {
        log.info("=====>>>>>开始计算签到扣分  {}", System.currentTimeMillis());
        iShUserSignService.deduction();
        log.info("=====>>>>>结束计算签到扣分   {}", System.currentTimeMillis());
    }
}
