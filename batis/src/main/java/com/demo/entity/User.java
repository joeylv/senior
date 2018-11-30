package com.demo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 性别，女为0，男为1
     */
    private String sex;

    /**
     * 备注
     */
    private Date birthday;

    /**
     * 备注
     */
    private String address;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return username - 姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置姓名
     *
     * @param username 姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取性别，女为0，男为1
     *
     * @return sex - 性别，女为0，男为1
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别，女为0，男为1
     *
     * @param sex 性别，女为0，男为1
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取备注
     *
     * @return birthday - 备注
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置备注
     *
     * @param birthday 备注
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取备注
     *
     * @return address - 备注
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置备注
     *
     * @param address 备注
     */
    public void setAddress(String address) {
        this.address = address;
    }
}