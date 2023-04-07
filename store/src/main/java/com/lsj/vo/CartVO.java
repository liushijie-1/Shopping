package com.lsj.vo;

import java.io.Serializable;
import java.util.Objects;

/*购物车数据的VO类(Value Object)*/
public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realPrice;


    public CartVO() {
    }

    public CartVO(Integer cid, Integer uid, Integer pid, Long price, Integer num, String title, String image, Long realPrice) {
        this.cid = cid;
        this.uid = uid;
        this.pid = pid;
        this.price = price;
        this.num = num;
        this.title = title;
        this.image = image;
        this.realPrice = realPrice;
    }

    /*
     * 获取
     * @return cid
     */
    public Integer getCid() {
        return cid;
    }

    /*
     * 设置
     * @param cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * 获取
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /*
     * 设置
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /*
     * 设置
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取
     * @return price
     */
    public Long getPrice() {
        return price;
    }

    /*
     * 设置
     * @param price
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /*
     * 获取
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /*
     * 设置
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取
     * @return realPrice
     */
    public Long getRealPrice() {
        return realPrice;
    }

    /**
     * 设置
     * @param realPrice
     */
    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public String toString() {
        return "CartVO{cid = " + cid + ", uid = " + uid + ", pid = " + pid + ", price = " + price + ", num = " + num + ", title = " + title + ", image = " + image + ", realPrice = " + realPrice + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartVO cartVO = (CartVO) o;
        return Objects.equals(cid, cartVO.cid) && Objects.equals(uid, cartVO.uid) && Objects.equals(pid, cartVO.pid) && Objects.equals(price, cartVO.price) && Objects.equals(num, cartVO.num) && Objects.equals(title, cartVO.title) && Objects.equals(image, cartVO.image) && Objects.equals(realPrice, cartVO.realPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, uid, pid, price, num, title, image, realPrice);
    }
}
