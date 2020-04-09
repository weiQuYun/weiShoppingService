package com.wqy.modules.shopping.controller;

import com.github.pagehelper.PageInfo;
import com.wqy.modules.common.Constant;
import com.wqy.modules.common.pojo.Page;
import com.wqy.modules.common.pojo.Result;
import com.wqy.modules.common.pojo.StatusCode;
import com.wqy.modules.shopping.entity.ShType;
import com.wqy.modules.shopping.entity.ShUser;
import com.wqy.modules.shopping.service.IShTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author licm
 * @since 2020-04-01
 */
@Api(tags = {"商品类型表"})
@RestController
@RequestMapping(Constant.TYPE)
public class ShTypeController {
    @Autowired
    private IShTypeService shTypeService;
    /*
    *  查询全部
    * */
    @PostMapping("findAll")
    public PageInfo<ShType> findAll(Page page,@RequestBody ShType shType){
        return shTypeService.selectAll(page,shType);
    }

   @PostMapping("addType")
    public Result addType(@RequestBody ShType shType){
       System.out.println(shType);
       shTypeService.addType(shType);
       return new Result(true, StatusCode.OK,"成功");
    }
    /**
     * 修改
     * **/
    @PutMapping(value = "/update/{id}")
    public Result updateShUser(@RequestBody ShType shType, @PathVariable String id){
        shType.setId(id);
       shTypeService.updateType(shType);
        return new Result(true,StatusCode.OK,"成功");
    }
    /**
     * 删除
     * **/
    @DeleteMapping(value = "/delete/{id}")
    public Result deleteShUser(@PathVariable String id){
        shTypeService.deleteType(id);
        return new Result(true,StatusCode.OK,"成功");
    }
}
