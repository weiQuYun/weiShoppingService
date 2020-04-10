package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShAttribute;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "属性表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/shAttribute")
public class ShAttributeController {

    @PostMapping()
    @ApiOperation("保存")
    public boolean save(@RequestBody ShAttribute shAttribute){
        return shAttribute.insert();
    }
    @PutMapping()
    @ApiOperation("修改")
    public boolean update(@RequestBody ShAttribute shAttribute){
        return shAttribute.updateById();
    }
}
