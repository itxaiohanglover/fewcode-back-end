package com.fewcode.system.vo.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fewcode.system.bean.DictType;
import com.fewcode.system.vo.model.DictTypeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 系统管理-字典类型分页请求对象 sys_dict_type
 *
 * @author 文艺倾年
 */
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ApiModel(value = "系统管理-字典类型分页请求对象", description = "system-系统管理-字典类型分页请求对象")
public class DictTypePageQuery {

    @ApiModelProperty(value = "分页信息")
    private Page<DictType> page;

    @ApiModelProperty(value = "请求信息")
    private DictTypeModel query;

}
