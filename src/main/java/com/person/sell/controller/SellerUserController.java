package com.person.sell.controller;


import com.person.sell.config.ProjectUrlConfig;
import com.person.sell.constant.CookieConstant;
import com.person.sell.constant.RedisConstant;
import com.person.sell.dataobject.SellerInfo;
import com.person.sell.enums.ResultEnum;
import com.person.sell.form.SellerLoginForm;
import com.person.sell.service.SellerService;
import com.person.sell.service.impl.SellerServiceImpl;
import com.person.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("seller/login");
    }


    /**
     * 登录
     * @param sellerLoginForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(@Valid SellerLoginForm sellerLoginForm,
                              BindingResult bindingResult,
                              HttpServletResponse response,
                              Map<String,Object> map){

        //1. 获取表单传递账户密码，与数据库数据匹配，错误则通知，正确继续
        SellerInfo sellerInfo = sellerService.findSellerInfoByUsername(sellerLoginForm.getUsername());
        if(!sellerInfo.getUsername().equals(sellerLoginForm.getUsername()) || !sellerInfo.getPassword().equals(sellerLoginForm.getPassword())){
            map.put("msg", ResultEnum.LOGIN_FAIL.getMsg());
            map.put("url","/sell/seller/order/list");
            map.put("msg1",bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error",map);
        }
        //2. 设置token至redis
        String token = UUID.randomUUID().toString(); //随机生成字符串
        Integer expire = RedisConstant.EXPIRE;
        //设置 redis，最后参数是将 expire 格式设置为秒
        redisTemplate.opsForValue().set(
                String.format(RedisConstant.TOKEN_PREFIX,token),
                sellerLoginForm.getUsername(),expire, TimeUnit.SECONDS);
        //3. 设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);
        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,
                       Map<String,Object> map){

        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie != null){
            //2. 清除 redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3. 清除 cookie ,就是将过期时间设置为0
            //cookie.setMaxAge(0);
            //response.addCookie(cookie);
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGIN_SUCCESS.getMsg());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);

    }

}
