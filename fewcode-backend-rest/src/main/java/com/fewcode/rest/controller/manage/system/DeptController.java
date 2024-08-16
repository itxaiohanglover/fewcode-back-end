package com.fewcode.rest.controller.manage.system;

import com.fewcode.system.service.IDeptService;
import com.fewcode.system.vo.model.DeptModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户中心-部门  前端控制器
 *
 * @author 文艺倾年
 */
@Api(tags = "系统管理-部门管理接口")
@RestController
@RequestMapping("/manage/system/dept")
@AllArgsConstructor
public class DeptController {

    private final IDeptService deptService;

    @ApiOperation(value = "查询用户中心-部门树结构", notes="用户中心-部门-查询树结构", nickname = "文艺倾年")
    @PostMapping("/getTreeList")
    public List<DeptModel> getTreeList(@RequestBody DeptModel model) {
        return deptService.getTreeList(model);
    }

    @ApiOperation(value = "根据id查询用户中心-部门信息", notes="用户中心-部门-根据id查询数据信息", nickname = "文艺倾年")
    @GetMapping(value = "/{id}")
    public DeptModel getDeptById(@PathVariable("id") Long id) {
        return deptService.getDeptById(id);
    }

    @ApiOperation(value = "新增用户中心-部门数据", notes="用户中心-部门-新增数据", nickname = "文艺倾年")
    @PostMapping("/createDept")
    public Long createDept(@RequestBody DeptModel model) {
        return deptService.createDept(model);
    }

    @ApiOperation(value = "修改用户中心-部门数据", notes="用户中心-部门-修改数据", nickname = "文艺倾年")
    @PostMapping("/updateDept")
    public void updateDept(@RequestBody DeptModel model) {
        deptService.updateDept(model);
    }

    @ApiOperation(value = "删除用户中心-部门", notes="用户中心-部门-根据id删除数据信息", nickname = "文艺倾年")
    @DeleteMapping("/{id}")
    public Integer deleteDept(@PathVariable("id") Long id) {
        return deptService.deleteDept(id);
    }
}
