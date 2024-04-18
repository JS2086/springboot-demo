package com.example.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.mapper.PersonMapper;
import com.example.pojo.ResponseBean;
import com.example.service.IEchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EchartsServiceImpl implements IEchartsService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public ResponseBean<Map> getBarData() {
        Map<String,List> map= new HashMap<>();
        List provinceList = CollUtil.newArrayList();
        List numList = CollUtil.newArrayList();;
        List<Map<String,Integer>> list = personMapper.getProvinceAndNum();
        for(Map m : list) {
            provinceList.add(m.get("name"));
            numList.add(m.get("num"));
        }
        map.put("province",provinceList);
        map.put("num",numList);
        return ResponseBean.success(null,map);
    }
}
