package com.wqy.wx.back.plus2.controller;

import com.wqy.wx.back.common.Constant;
import com.wqy.wx.back.plus2.entity.TRole;
import com.wqy.wx.back.plus2.service.ITRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author licm
 * @since 2020-04-03
 */
@Api(tags = "角色权限接口管理")
@RestController
@RequestMapping(Constant.MAPPING + "/role")
public class TRoleController {
    @Autowired
    private ITRoleService itRoleService;

    /**
     * 测试通过
     **/
    @GetMapping("/list")
    @ApiOperation("获取全部")
    public List<TRole> getTRoleAll() {
        System.out.println("1321231321");
        return itRoleService.searchAll();
    }

//暂定不更改用户组 故未实现以下method
//    @DeleteMapping("/{id}")
//    @ApiOperation("删除")
//    public void deleteTRole(@PathVariable String id) {
//        itRoleService.deleteTRole(id);
//    }
//
//    @PostMapping("")
//    @ApiOperation("添加")
//    public void addTRole(@RequestBody TRole tRole) {
//        itRoleService.insertTRole(tRole);
//    }
//
//    @PutMapping("")
//    @ApiOperation("修改")
//    public void updateTRole(@RequestBody TRole tRole) {
//        itRoleService.updateTRole(tRole);
//    }
//
//    @GetMapping("/page/{page}/{size}")
//    @ApiOperation("分页查询")
//    public void searchTRolePage(@PathVariable int page, @PathVariable int size) {
//        itRoleService.searchAll(page, size);
//    }
//    @PutMapping("/batch")
//    @ApiOperation("批量修改")
//    public void updateTRoleBatch(@RequestBody List<TRole> list){
//        itRoleService.updateTRole(list);
//    }
//    @PostMapping("/batch")
//    @ApiOperation("批量添加")
//    public void addTRoleBatch(@RequestBody List<TRole> list){
//        itRoleService.insertTRole(list);
//    }
//    @DeleteMapping("/delete/{id}")
//    @ApiOperation("批量删除")
//    public void deleteTRoleBatch(@PathVariable List<String> id){
//        itRoleService.deleteTRole(id);
//    }
}
