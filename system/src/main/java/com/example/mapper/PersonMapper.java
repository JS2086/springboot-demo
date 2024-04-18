package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pojo.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PersonMapper extends BaseMapper<Person> {

    /**
     * 查询所有人员信息(分页)
     * @param page
     * @param person
     * @return
     */
    IPage<Person> getPersonByPage(Page<Person> page, @Param("person") Person person);

    /**
     * 统计并获取人员信息中数量前十的省份以及对应数量
     * @return
     */
    @Select("select pv.name,count(pv.name) num from t_person pr,t_province pv where pr.provinceId = pv.id group by pv.name order by num desc limit 10")
    List<Map<String,Integer>> getProvinceAndNum();

    /**
     * 获取/查询人员信息
     * @param id
     * @return
     */
    List<Person> getPerson(Integer id);
}
