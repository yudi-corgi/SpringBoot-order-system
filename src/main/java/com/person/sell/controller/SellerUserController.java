package com.person.sell.controller;


import com.person.sell.dataobject.SellerInfo;
import com.person.sell.form.SellerLoginForm;
import com.person.sell.service.SellerService;
import com.person.sell.service.impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("seller/login");
    }


    /**
     * 登录
     * @param
     */
    @GetMapping("/login")
    public void login(@Valid SellerLoginForm sellerLoginForm,
                      @RequestParam("returnUrl") String returnUrl){

        //1. 获取表单传递账户密码，与数据库数据匹配，错误则通知，正确继续
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(sellerLoginForm.getUsername());
        if(sellerInfo != null && sellerInfo.getPassword().equals(sellerLoginForm.getPassword())){
            //2. 设置token至redis

            //3. 设置token至cookie
        }
    }

    @GetMapping("/logout")
    public void logout(){

    }

}
