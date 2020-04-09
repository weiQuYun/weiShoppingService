package com.wqy.wx.back.plus2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.THotel;
import com.wqy.wx.back.plus2.service.ITHotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "店铺表接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/hotel")
public class THotelController {
    @Autowired
    private ITHotelService itHotelService;

    @GetMapping("/list")
    @ApiOperation("条件获取数据")
    public List<THotel> getList(THotel tHotel) {
        return itHotelService.getList(tHotel);
    }

    @GetMapping("/page")
    @ApiOperation("条件获取分页数据")
    public Page<THotel> getPage(THotel tHotel, PageDTO pageDTO) {
        return itHotelService.getPage(tHotel, pageDTO);
    }

    @PostMapping("")
    @ApiOperation("保存数据")
    public boolean save(@RequestBody THotel tHotel) {
        return itHotelService.save(tHotel);
    }

    @PostMapping("/batch")
    @ApiOperation("批量保存")
    public boolean saveBatch(@RequestBody List<THotel> tHotels) {
        return itHotelService.saveBatch(tHotels);
    }

    @PutMapping("")
    @ApiOperation("修改")
    public boolean update(@RequestBody THotel tHotels) {
        return itHotelService.updateById(tHotels);
    }

    @PutMapping("/batch")
    @ApiOperation("批量修改")
    public boolean updateBatch(@RequestBody List<THotel> tHotels) {
        return itHotelService.updateBatchById(tHotels);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public boolean delete(@RequestBody THotel tHotels) {
        return itHotelService.removeById(tHotels);
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除")
    public boolean deleteBatch(@RequestBody List<THotel> tHotels) {
        return itHotelService.removeByIds(tHotels);
    }
}
