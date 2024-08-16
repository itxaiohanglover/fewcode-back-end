package com.fewcode.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fewcode.system.bean.Dept;
import com.fewcode.system.vo.model.DeptModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户中心-部门Mapper接口
 *
 * @author 文艺倾年
 */
public interface DeptMapper extends BaseMapper<Dept>{
    /**
     * 查询用户中心-部门列表
     *
     * @param dept 用户中心-部门
     * @return 用户中心-部门集合
     */
    List<DeptModel> getTreeList(@Param("dept") DeptModel dept);
}
