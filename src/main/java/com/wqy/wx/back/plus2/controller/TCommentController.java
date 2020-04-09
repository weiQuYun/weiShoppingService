package com.wqy.wx.back.plus2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.common.util.page.PageDTO;
import com.wqy.wx.back.plus2.entity.TComment;
import com.wqy.wx.back.plus2.service.ITCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "评论投诉表i接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/comment")
public class TCommentController {
    @Autowired
    private ITCommentService itCommentService;

    @GetMapping("/list")
    @ApiOperation("条件获取数据")
    public List<TComment> getList(TComment tComment) {
        return itCommentService.getList(tComment);
    }

    @GetMapping("/page")
    @ApiOperation("条件获取分页数据")
    public Page<TComment> getPage(TComment tComment, PageDTO pageDTO) {
        return itCommentService.getPage(tComment, pageDTO);
    }

    @PostMapping("")
    @ApiOperation("保存数据")
    public boolean save(@RequestBody TComment tComment) {
        return itCommentService.save(tComment);
    }

    @PostMapping("/batch")
    @ApiOperation("批量保存")
    public boolean saveBatch(@RequestBody List<TComment> tComments) {
        return itCommentService.saveBatch(tComments);
    }

    @PutMapping("")
    @ApiOperation("修改")
    public boolean update(@RequestBody TComment tComments) {
        return itCommentService.updateById(tComments);
    }

    @PutMapping("/batch")
    @ApiOperation("批量修改")
    public boolean updateBatch(@RequestBody List<TComment> tComments) {
        return itCommentService.updateBatchById(tComments);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public boolean delete(@RequestBody TComment tComments) {
        return itCommentService.removeById(tComments);
    }

    @DeleteMapping("/batch")
    @ApiOperation("批量删除")
    public boolean deleteBatch(@RequestBody List<TComment> tComments) {
        return itCommentService.removeByIds(tComments);
    }
}
