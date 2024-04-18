package com.example.mapper;

import com.example.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取菜单列表
     * @return
     */
    List<Menu> getMenu();
}
