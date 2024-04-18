package com.example.controller;


import com.example.pojo.Province;
import com.example.service.IProvinceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProvinceController {

    @Autowired
    private IProvinceService provinceService;

    @ApiOperation(value = "获取所有省份")
    @GetMapping("/province")
    public List<Province> getAllProvince(){
        return provinceService.list();
    }
}
