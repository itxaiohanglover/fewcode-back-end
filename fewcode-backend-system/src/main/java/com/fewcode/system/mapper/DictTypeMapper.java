package com.fewcode.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fewcode.system.bean.DictType;
import com.fewcode.system.vo.model.DictTypeModel;
import org.apache.ibatis.annotations.Param;

/**
 * 系统管理-字典类型Mapper接口
 *
 * @author 文艺倾年
 */
public interface DictTypeMapper extends BaseMapper<DictType>{
    /**
     * 查询系统管理-字典类型列表
     *
     * @param req 系统管理-请求参数
     * @return 系统管理-字典类型集合
     */
    IPage<DictTypeModel> getPageList(@Param("page") Page<DictType> page, @Param("req") DictTypeModel req);

}
