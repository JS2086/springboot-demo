package com.example.service;

import com.example.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IMenuService extends IService<Menu> {

    /**
     * 获取菜单列表
     * @return
     */
    List<Menu> getMenu();
}
