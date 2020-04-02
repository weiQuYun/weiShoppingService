package com.wqy.modules.shopping.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licm
 * @since 2020-04-01
 */
@Api(description = "分销表")
@RestController
@RequestMapping("/api/shop/shAttribute")
public class ShAttributeController {
    @GetMapping("/list")
    @ApiOperation("列表查询")
    public Boolean getlist(){
        return null;
    }
}
