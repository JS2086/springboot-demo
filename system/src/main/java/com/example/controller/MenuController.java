package com.example.controller;


import com.example.pojo.Menu;
import com.example.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "获取菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenu(){
        return menuService.getMenu();
    }

}
