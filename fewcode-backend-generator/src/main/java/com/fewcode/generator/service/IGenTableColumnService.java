package com.fewcode.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fewcode.generator.bean.GenTable;
import com.fewcode.generator.bean.GenTableColumn;

import java.util.List;

/**
 * <p>
 * 代码生成-代码生成业务表字段 服务类
 * </p>
 *
 * @author 文艺倾年
 * @since 2021-12-30
 */
public interface IGenTableColumnService extends IService<GenTableColumn> {
    /**
     * 查询业务字段列表
     *
     * @param tableId 业务字段编号
     * @return 业务字段集合
     */
    List<GenTableColumn> selectListByTableId(Long tableId);
}
