package com.lsj.service.impl;

import com.lsj.domain.Address;
import com.lsj.domain.District;
import com.lsj.mapper.AddressMapper;
import com.lsj.service.AddressService;
import com.lsj.service.IDistrictService;
import com.lsj.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
/*新增收货地址的实现类*/
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    //在添加用户的收货地址的业务层依赖于IDistrictService的业务层接口
    @Autowired
    private IDistrictService districtService;

    @Value("${User.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //先判断收货地址是否达到上限
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("收货地址超出上限");
        }

        //对address对象中的数据进行补全：省市区
        String provinceName = districtService.getByCode(address.getProvinceCode());
        String cityName = districtService.getByCode(address.getCityCode());
        String areaName = districtService.getByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //补全uid、isDelete
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;// 1表示默认地址，0表示不是默认
        address.setIsDefault(isDefault);
        //补全4项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //插入收货地址的方法啊
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsterException("插入用户的收货地址产生未知异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
            //address.setAid(null);
            //address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取的收货数据归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        //把所有数据设置为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新数据产生未知收货异常1");
        }
        //将用户选中的某条地址设置为默认收货地址
        Integer integer = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (integer != 1) {
            throw new UpdateException("更新数据产生未知收货异常2");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        Integer roes = addressMapper.deleteByAid(aid);
        if (roes!=1){
            throw new DeleteException("删除数据失败");
        }

        Integer count = addressMapper.countByUid(uid);
        if (count==0){
            return;
        }

        if (result.getIsDefault()==0){
            return;
        }
        //将这条数据中的isDefault字符的值设置为1
        Address address = addressMapper.findLastModified(uid);
        Integer rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据产生未知收货异常");
        }
    }

    @Override
    public Address getByAid(Integer aid,Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        addressMapper.findByAid(aid);
        return address;
    }
}
