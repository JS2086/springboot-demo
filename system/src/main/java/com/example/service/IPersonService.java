package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Pagination;
import com.example.pojo.Person;
import com.example.pojo.ResponseBean;

import java.util.List;

public interface IPersonService extends IService<Person> {

    /**
     * 获取人员信息(分页)
     * @param currentPage
     * @param size
     * @param person
     * @return
     */
    ResponseBean<Pagination> getPersonByPage(Integer currentPage, Integer size, Person person);

    /**
     * 添加人员信息
     * @param person
     * @return
     */
    ResponseBean<String> addPerson(Person person);

    /**
     * 随机生成人员信息
     */
    List<Person> randomGeneratePerson();

    /**
     * 获取/查询人员信息
     * @param id
     * @return
     */
    List<Person> getPerson(Integer id);

}
