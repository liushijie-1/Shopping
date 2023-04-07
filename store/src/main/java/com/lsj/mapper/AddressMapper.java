package com.lsj.mapper;

import com.lsj.domain.Address;
import com.lsj.domain.District;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/*收货地址持久层接口*/
public interface AddressMapper {

    /*
     * @Param 插入用户收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     * @return java.lang.Integer
     * @Date 20:31 2023/3/21
     **/
    Integer insert(Address address);

    /*
     * @Param 根据用户ID统计收货地址数量
     * @param uid
     * @return
     * @return java.lang.Integer
     * @Date 20:32 2023/3/21
     **/

    Integer countByUid(Integer uid);

    /*
     * 根据用户UID查询用户的收货地址数据
     * @param uid 用户UID
     * @return 用户地址信息
     * @Date 15:16 2023/3/22
     **/
    List<Address> findByUid(Integer uid);

    /*
     * 通过aid检测用户想设置的默认地址是否存在
     * @param aid 收货地址的id
     * @return 收货地址数据，如果没有返回null
     * @Date 16:06 2023/3/22
     **/
    Address findByAid(Integer aid);

    /*
     * 根据用户的UID值来修改用户的收货地址设置为非默认
     * @param uid 用户的ID值
     * @return 受影响的行数
     * @Date 16:10 2023/3/22
     **/
    Integer updateNonDefault(Integer uid);

    /*
     *  根据收货地址的ID设置状态为默认收货地址
     * @param aid 收货地址ID
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     * @Date 16:16 2023/3/22
     **/
    Integer updateDefaultByAid(@Param("aid") Integer aid,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);

    /*
     *根据收货地址ID删除收货地址数据
     * @param aid 收货地址ID
     * @return 受影响的行数
     * @Date 17:23 2023/3/22
     **/
    Integer deleteByAid(Integer aid);

    /*
     * 根据用户UID查询当前用户最后一次被修改的收货地址数据
     * @param uid
     * @return 收货地址数据
     * @Date 17:25 2023/3/22
     **/
    Address findLastModified(Integer uid);

}
