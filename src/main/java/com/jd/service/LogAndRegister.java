package com.jd.service;

import com.jd.entity.Customer;

public interface LogAndRegister {

    /*
        直接使用ajax异步发送请求，判断用户注册账号是否重复
        注册成功则提示用户是否直接登录，同意则返回一个携带不完整信息的Customer对象到页面,对相关页面进行渲染
     */
    public Customer registCustomer(Customer customer);
    /*
        登录，接受用户请求，后台判断账号和密码是否正确，不正确则重新登陆
     */
    public Customer loginCustomer(Customer customer);
}
