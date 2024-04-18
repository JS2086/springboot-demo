package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.MenuMapper;
import com.example.pojo.Menu;
import com.example.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenu() {
        return menuMapper.getMenu();
    }
}
