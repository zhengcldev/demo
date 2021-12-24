package com.algorithm.demo.Controller;

import com.algorithm.demo.entity.User;
import com.algorithm.demo.enumeration.StatusEnum;
import com.algorithm.demo.resp.Resp;
import com.algorithm.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @描述:
 * @author: zhengchuanlong
 * @date: 2021/12/22 下午1:36
 */
@RestController
@RequestMapping("/skyline")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping(value = "/login")
    public Resp<Object> login(@RequestBody User user) {
        Boolean isLogin = userService.queryUser(user.getUserId(), user.getPassword());
        Resp<Object> rsp = new Resp<>(StatusEnum.LOGIN_SUCCESS.getStatusCode(), StatusEnum.LOGIN_SUCCESS.getStatusMsg(), isLogin);
        return rsp;
    }
    @GetMapping(value = "/test")
    public String test(){
        return "test";
    }
}
