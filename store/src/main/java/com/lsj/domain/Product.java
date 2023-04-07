package com.lsj.domain;

import java.io.Serializable;
import java.util.Objects;

public class Product extends BaseEntity implements Serializable {

    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private String sellPoint;
    private Long price;
    private Integer num;
    private String image;
    private Integer status;
    private Integer priority;

    public Product() {
    }

    public Product(Integer id, Integer categoryId, String itemType, String title, String sellPoint, Long price, Integer num, String image, Integer status, Integer priority) {
        this.id = id;
        this.categoryId = categoryId;
        this.itemType = itemType;
        this.title = title;
        this.sellPoint = sellPoint;
        this.price = price;
        this.num = num;
        this.image = image;
        this.status = status;
        this.priority = priority;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置
     * @param categoryId
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取
     * @return itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * 设置
     * @param itemType
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
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
     * @return sellPoint
     */
    public String getSellPoint() {
        return sellPoint;
    }

    /**
     * 设置
     * @param sellPoint
     */
    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    /**
     * 获取
     * @return price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取
     * @return num
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置
     * @param num
     */
    public void setNum(Integer num) {
        this.num = num;
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
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取
     * @return priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String toString() {
        return "Product{id = " + id + ", categoryId = " + categoryId + ", itemType = " + itemType + ", title = " + title + ", sellPoint = " + sellPoint + ", price = " + price + ", num = " + num + ", image = " + image + ", status = " + status + ", priority = " + priority + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(categoryId, product.categoryId) && Objects.equals(itemType, product.itemType) && Objects.equals(title, product.title) && Objects.equals(sellPoint, product.sellPoint) && Objects.equals(price, product.price) && Objects.equals(num, product.num) && Objects.equals(image, product.image) && Objects.equals(status, product.status) && Objects.equals(priority, product.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, itemType, title, sellPoint, price, num, image, status, priority);
    }
}
