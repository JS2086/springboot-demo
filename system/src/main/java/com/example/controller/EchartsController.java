package com.example.controller;

import com.example.pojo.ResponseBean;
import com.example.service.IEchartsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private IEchartsService iEchartsService;

    @ApiOperation(value = "获取柱状图数据")
    @GetMapping("/bar")
    public ResponseBean<Map> getBarData(){
        return iEchartsService.getBarData();
    }
}
