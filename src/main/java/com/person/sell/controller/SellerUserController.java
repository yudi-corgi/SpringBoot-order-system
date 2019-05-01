package com.person.sell.controller;


import com.person.sell.dataobject.SellerInfo;
import com.person.sell.enums.ResultEnum;
import com.person.sell.form.SellerLoginForm;
import com.person.sell.service.SellerService;
import com.person.sell.service.impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

/**
 * 卖家登录相关操作
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("seller/login");
    }


    /**
     * 登录
     * @param sellerLoginForm
     * @param bindingResult
     * @param returnUrl
     * @param map
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(@Valid SellerLoginForm sellerLoginForm, BindingResult bindingResult,
                      @RequestParam(value = "returnUrl",required = false) String returnUrl,
                      Map<String,Object> map){

        //1. 获取表单传递账户密码，与数据库数据匹配，错误则通知，正确继续
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(sellerLoginForm.getUsername());
        if(!sellerInfo.getUsername().equals(sellerLoginForm.getUsername()) || !sellerInfo.getPassword().equals(sellerLoginForm.getPassword())){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        //2. 设置token至redis
        redisTemplate.opsForValue().set("asd","123");
        //3. 设置token至cookie
        return null;
    }

    @GetMapping("/logout")
    public void logout(){

    }

}
