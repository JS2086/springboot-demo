package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.ResponseBean;
import com.example.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IUserService extends IService<User> {

    /**
     * 实现登录并返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    ResponseBean<Map<String,Object>> login(String username, String password, HttpServletRequest request);

    /**
     * 获取当前登录用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);
}
