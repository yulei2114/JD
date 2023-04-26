package com.jd.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class Customer {

    @Value("3")
    private  int id;
    private String account;
    private String nickName;
    private String password;
    private Date registerDate;
    private String email;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setQqEmail(String qqEmail) {
        this.email = qqEmail;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", registerDate=" + registerDate +
                ", email='" + email + '\'' +
                '}';
    }
}
