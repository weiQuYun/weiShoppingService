package com.wqy.wx.back.plus3.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus3.entity.ShType;
import com.wqy.wx.back.plus3.service.IShTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-09
 */
@Api(tags = "分类表接口管理 已完成")
@RestController
@RequestMapping(Constant.MAPPING + "/type")
public class ShTypeController {
    @Autowired
    private IShTypeService iShTypeService;

    @GetMapping("/list")
    @ApiOperation(value = "商品分类")
    public List<ShType> getShType() {
        return iShTypeService.selectAll();
    }

    @GetMapping("/index")
    @ApiOperation(value = "主页商品分类 仅供admin调用")
    public ShType getIndexShGoodsType() {
        return iShTypeService.selectIndex();
    }

    @PutMapping("")
    @ApiOperation(value = "分类修改")
    public String updateShType(@RequestBody ShType shType) {
        if (iShTypeService.updateShType(shType)) {
            return "修改成功";
        }
        return "修改失败";
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除分类")
    public String deleteShtype(@PathVariable String id) {
        iShTypeService.deleteById(id);
        return "删除成功";
    }

    @PostMapping("")
    @ApiOperation(value = "新增分类")
    public String insertShType(@RequestBody ShType shType) {
        iShTypeService.insertShType(shType);
        return "添加成功";
    }

    @PutMapping("/batch")
    @ApiOperation(value = "批量修改")
    public String updateShType(@RequestBody List<ShType> list) {
        if (iShTypeService.updateShType(list)) return "修改成功";
        return "修改失败";
    }

    @PostMapping("/batch")
    @ApiOperation(value = "批量新增")
    public String insertShType(@RequestBody List<ShType> list) {
        if (iShTypeService.insertShType(list)) return "添加成功";
        return "添加失败";
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除")
    public String deleteShType(@RequestBody List<String> list) {
        if (iShTypeService.deleteShType(list)) return "删除成功";
        return "删除失败ID错误";
    }


}
