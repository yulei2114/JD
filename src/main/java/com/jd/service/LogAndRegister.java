package com.jd.service;

import com.jd.entity.Customer;

import java.security.GeneralSecurityException;

public interface LogAndRegister {

    //插入数据
    public boolean insertCustomer(Customer customer);
    //用户登录
    public Customer checkByCustomer(Customer customer);
    //用户注册
    public boolean checkByAccount(String account);
    //用户验证邮箱
    public Customer checkEmail(Customer customer) throws GeneralSecurityException;
    //更新用户信息
    public Customer updateCustomer(Customer customer);
}
