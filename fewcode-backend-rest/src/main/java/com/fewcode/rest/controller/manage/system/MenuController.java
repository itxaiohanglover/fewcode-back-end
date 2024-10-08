package com.fewcode.rest.controller.manage.system;

import com.fewcode.common.util.JwtUtil;
import com.fewcode.system.service.IMenuService;
import com.fewcode.system.vo.model.MenuModel;
import com.fewcode.system.vo.model.RouterModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户中心-菜单管理
 *
 * @author 文艺倾年
 */
@Api(tags = "系统管理-菜单管理接口")
@RestController
@RequestMapping("/manage/system/menu")
@AllArgsConstructor
public class MenuController {
    
    private final IMenuService menuService;

    @ApiOperation(value = "获取路由信息")
    @GetMapping("/getRouters")
    public List<RouterModel> getRouters() {
        return menuService.selectMenuTreeByUserId(JwtUtil.getUserId());
    }

    @ApiOperation(value = "查询用户中心-菜单权限树结构", notes = "用户中心-菜单权限-查询树结构", nickname = "文艺倾年")
    @PostMapping("/getTreeList")
    public List<MenuModel> getTreeList(@RequestBody MenuModel model) {
        return menuService.getTreeList(model);
    }

    @ApiOperation(value = "根据id查询用户中心-菜单权限信息", notes = "用户中心-菜单权限-根据id查询数据信息", nickname = "文艺倾年")
    @GetMapping(value = "/{id}")
    public MenuModel getMenuById(@PathVariable("id") Long id) {
        return menuService.getMenuById(id);
    }

    @ApiOperation(value = "新增用户中心-菜单权限数据", notes = "用户中心-菜单权限-新增数据", nickname = "文艺倾年")
    @PostMapping("/createMenu")
    public Long createMenu(@RequestBody MenuModel model) {
        return menuService.createMenu(model);
    }

    @ApiOperation(value = "/修改用户中心-菜单权限数据", notes = "用户中心-菜单权限-修改数据", nickname = "文艺倾年")
    @PostMapping("/updateMenu")
    public void updateMenu(@RequestBody MenuModel model) {
        menuService.updateMenu(model);
    }

    @ApiOperation(value = "删除用户中心-菜单权限", notes = "用户中心-菜单权限-根据id删除数据信息", nickname = "文艺倾年")
    @DeleteMapping("/{id}")
    public Integer deleteMenu(@PathVariable("id") Long id) {
        return menuService.deleteMenu(id);
    }
}
