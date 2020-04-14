package com.wqy.wx.back.scheduler;

import com.wqy.wx.back.plus3.service.IShRankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component
public class ScheduledService {
    @Autowired
    private IShRankService shRankService;

    @Scheduled(cron = "0 0 0 15 * ?")//每月15号触发
    public void scheduled() {
        log.info("=====>>>>>开始计算排行榜  {}", System.currentTimeMillis());
        shRankService.generateRank();
        log.info("=====>>>>>结束计算排行榜  {}", System.currentTimeMillis());
    }
}
