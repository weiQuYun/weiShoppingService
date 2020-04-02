package com.wqy.modules.shopping.controller;

import com.wqy.modules.shopping.service.IShUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author licm
 * @since 2020-04-01
 */
@Api(tags = {""})
@RestController
@RequestMapping("/shUser")
public class ShUserController {

    @Autowired
    private IShUserService iShUserService;

//    @GetMapping("/add")
    //  public
}
