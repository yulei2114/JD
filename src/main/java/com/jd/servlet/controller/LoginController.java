package com.jd.servlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @PostMapping("/login")
    public ModelAndView modelAndView(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("test");
        //调用Dao对象，获取数据库信息
        modelAndView.addObject("customer","hello");
        return modelAndView;
    }
}
