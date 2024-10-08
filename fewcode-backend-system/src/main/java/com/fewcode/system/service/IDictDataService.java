package com.fewcode.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fewcode.system.bean.DictData;
import com.fewcode.system.vo.model.DictDataModel;

import java.util.List;

/**
 * <p>
 * 系统管理-字典数据表 服务类
 * </p>
 *
 * @author 文艺倾年
 * @since 2021-12-07
 */
public interface IDictDataService extends IService<DictData> {

    /**
     * 根据字典类型获取字典信息
     *
     * @param code dict_code
     * @return 字典信息
     */
    List<DictDataModel> getDictByCode(String code);
}
