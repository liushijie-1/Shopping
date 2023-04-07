package com.lsj.vo;

import java.util.Objects;

/*用户信息VO类*/
public class UserVO {
    private Integer uid;
    private Integer aid;
    private String username;
    private String phone;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String zip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVO userVO = (UserVO) o;
        return Objects.equals(uid, userVO.uid) && Objects.equals(aid, userVO.aid) && Objects.equals(username, userVO.username) && Objects.equals(phone, userVO.phone) && Objects.equals(provinceName, userVO.provinceName) && Objects.equals(cityName, userVO.cityName) && Objects.equals(areaName, userVO.areaName) && Objects.equals(zip, userVO.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, aid, username, phone, provinceName, cityName, areaName, zip);
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "uid=" + uid +
                ", aid=" + aid +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
