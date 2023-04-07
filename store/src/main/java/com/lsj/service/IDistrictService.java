package com.lsj.service;

import com.lsj.domain.District;

import java.util.List;

public interface IDistrictService {

    /*
     * 根据父代号来查询区域信息（省市区）
     * @param parent
     * @return 多个区域信息
     * @return java.util.List<com.lsj.domain.District>
     * @Date 10:50 2023/3/22
     **/
    List<District> getByParent(String parent);

    /*
     * 根据code获取当前省市信息
     * @param code
     * @return
     * @Date 14:28 2023/3/22
     **/
    String getByCode(String code);

}
