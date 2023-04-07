package com.lsj.service;

import com.lsj.domain.Address;

import java.util.List;

/*收货地址业务层接口*/
public interface AddressService {

    /*
     * 新增收货地址
     * @param uid
     * @param username 收货人姓名
     * @param address
     * @return
     * @Date 22:31 2023/3/21
     **/
    void addNewAddress(Integer uid,String username,Address address);

    /*
     * 通过UID获取地址列表
     * @param uid
     * @return
     * @return java.util.List<com.lsj.domain.Address>
     * @Date 15:24 2023/3/22
     **/
    List<Address> getByUid(Integer uid);

    /*
     * 修改某个用户的某条收货地址数据为默认收货地址
     * @param aid 收货地址ID
     * @param uid 用户ID
     * @param username 表示执行修改的人
     * @Date 16:34 2023/3/22
     **/
    void setDefault(Integer aid,Integer uid,String username);

    /*
     * 删除用户选择的收货地址对象
     * @param aid 收货地址ID
     * @param uid 用户ID
     * @param username 用户名
     * @Date 17:39 2023/3/22
     **/
    void delete(Integer aid,Integer uid,String username);

    Address getByAid(Integer aid,Integer uid);

}
