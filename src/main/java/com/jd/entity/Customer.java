package com.jd.entity;

import java.util.Date;

public class Customer {

    private  int id;
    private String account;
    private String password;
    private Date registerDate;
    //前端接受账号和密码，封装成Customer对象，给定一个两个参数的构造器
    public Customer(){

    }
    public Customer(String account,String password){
        this.account=account;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}
