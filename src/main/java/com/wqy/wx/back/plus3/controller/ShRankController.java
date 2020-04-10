package com.wqy.wx.back.plus3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import com.wqy.wx.back.common.Constant;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licm
 * @since 2020-04-10
 */
@Api(tags = "团队分值排行榜接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/shRank")
public class ShRankController {
}
