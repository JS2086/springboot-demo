package com.example.service;

import com.example.pojo.ResponseBean;

import java.util.Map;

public interface IEchartsService {
    ResponseBean<Map> getBarData();
}
