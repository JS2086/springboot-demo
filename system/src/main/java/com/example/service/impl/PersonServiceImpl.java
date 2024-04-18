package com.example.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.PersonMapper;
import com.example.mapper.ProvinceMapper;
import com.example.pojo.Pagination;
import com.example.pojo.Person;
import com.example.pojo.Province;
import com.example.pojo.ResponseBean;
import com.example.service.IPersonService;
import com.example.utils.GenerateIdCardUtil;
import com.example.utils.GenerateNameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    /**
     * 获取人员信息(分页)
     * @param currentPage
     * @param size
     * @param person
     * @return
     */
    @Override
    public ResponseBean<Pagination> getPersonByPage(Integer currentPage, Integer size, Person person) {
        //开启分页
        Page<Person> page = new Page<>(currentPage, size);
        IPage<Person> iPage = personMapper.getPersonByPage(page, person);
        Pagination pagination = new Pagination();
        pagination.setPageNo(iPage.getCurrent());
        pagination.setPageSize(iPage.getSize());
        pagination.setTotalPage(iPage.getPages());
        pagination.setTotal(iPage.getTotal());
        pagination.setList(iPage.getRecords());
        return ResponseBean.success(null,pagination);
    }

    /**
     * 添加人员信息
     * @param person
     * @return
     */
    @Override
    public ResponseBean<String> addPerson(Person person) {
        if (personMapper.insert(person)==1){
            return ResponseBean.success("添加人员信息成功");
        }
        return ResponseBean.success("添加人员信息失败");
    }

    /**
     * 随机生成10条人员信息
     */
    @Override
    public List<Person> randomGeneratePerson() {
        QueryWrapper<Province> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        List<Person> list = CollUtil.newArrayList();
        List<Province> provinces = provinceMapper.selectList(queryWrapper);
        for(int i=0; i<10; i++) {
            Person person = new Person();
            Random r = new Random();
            Boolean male = r.nextBoolean();
            String idCard = GenerateIdCardUtil.getIdNo(male);
            person.setName(GenerateNameUtil.getName(male));
            person.setIdCard(idCard);
            person.setProvince(provinces.get(r.nextInt(23)));
            person.setGender(IdcardUtil.getGenderByIdCard(idCard)==1?"男":"女");
            person.setAge(IdcardUtil.getAgeByIdCard(idCard));
            person.setBirth(DateUtil.parse(IdcardUtil.getBirth(idCard)).toLocalDateTime().toLocalDate());
            person.setWeight(r.nextInt(61)+40);
            list.add(person);
        }
        return list;
    }

    /**
     * 获取/查询人员信息
     * @param id
     * @return
     */
    @Override
    public List<Person> getPerson(Integer id) {
        return personMapper.getPerson(id);
    }


}
