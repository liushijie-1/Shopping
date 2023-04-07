package com.lsj.mapper;

import com.lsj.domain.District;

import java.util.List;

public interface DistrictMapper {

    /*
     * 根据父代号来查询区域信息（省市区）
     * @param parent 父代号
     * @return 某个父区域下的所有区域列表
     * @return java.util.List<com.lsj.domain.District>
     * @Date 10:37 2023/3/22
     **/
    List<District> findByParent(String parent);

    /*
     * 根据code来获取当前省市的名称
     * @param code
     * @return
     * @Date 14:23 2023/3/22
     **/
    String findByCode(String code);
}
