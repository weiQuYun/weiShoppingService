package com.zhengqing.modules.system.service;

import com.zhengqing.modules.system.pojo.ShUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/e")
public class ShUserWeb {
    @Autowired
    private ShUserService shUserService;

    @GetMapping("/e")
    public ShUser getShUser(){
        return shUserService.getShUserService().get(0);
    }



}
