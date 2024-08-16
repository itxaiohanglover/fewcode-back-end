package com.fewcode.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fewcode.system.bean.Menu;
import com.fewcode.system.vo.model.MenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户中心-菜单权限表 Mapper 接口
 * </p>
 *
 * @author 文艺倾年
 * @since 2021-12-07
 */
public interface MenuMapper extends BaseMapper<Menu> {
    
    /**
     * 查询用户中心-菜单权限列表
     *
     * @param menu 用户中心-菜单权限
     * @return 用户中心-菜单权限集合
     */
    List<MenuModel> getTreeList(@Param("menu") MenuModel menu);

    List<MenuModel> selectMenuTreeByUserId(Long userId);

    List<String> selectMenuPermsByUserId(Long userId);
}
