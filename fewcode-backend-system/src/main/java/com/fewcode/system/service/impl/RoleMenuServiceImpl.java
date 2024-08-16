package com.fewcode.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fewcode.system.bean.RoleMenu;
import com.fewcode.system.mapper.RoleMenuMapper;
import com.fewcode.system.service.IRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户中心-角色和菜单关联表 服务实现类
 * </p>
 *
 * @author 文艺倾年
 * @since 2021-12-07
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
