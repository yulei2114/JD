package com.jd.service.serviceImpl;

import com.jd.dao.CustomerMapper;
import com.jd.entity.Customer;
import com.jd.service.LogAndRegister;

public class LogAndRegisterImpl implements LogAndRegister {
    //一个属性用于存放Dao对象
    private CustomerMapper customerMapper;

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer registCustomer(Customer customer) {
        return null;

    }

    @Override
    public Customer loginCustomer(Customer customer) {
        return null;
    }
}
