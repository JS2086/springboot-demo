package com.example.controller;

import com.example.pojo.ResponseBean;
import com.example.pojo.User;
import com.example.pojo.UserLoginParam;
import com.example.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

/**
 * 登录
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "实现登录并返回token")
    @PostMapping("/login")
    public ResponseBean<Map<String,Object>> login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        return userService.login(userLoginParam.getUsername(),userLoginParam.getPassword(),request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/user/info")
    public User getUserInfo(Principal principal){
        if (principal == null){
            return null;
        }
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        user.setPassword(null);
        return user;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseBean<String> logout(){
        return ResponseBean.success("注销成功！");
    }
}
