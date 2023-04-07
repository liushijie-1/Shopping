package com.lsj.domain;

import java.util.Objects;

public class AdminUser extends BaseEntity {
    private String uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Integer gender;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AdminUser adminUser = (AdminUser) o;
        return Objects.equals(uid, adminUser.uid) && Objects.equals(username, adminUser.username) && Objects.equals(password, adminUser.password) && Objects.equals(phone, adminUser.phone) && Objects.equals(email, adminUser.email) && Objects.equals(gender, adminUser.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uid, username, password, phone, email, gender);
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
