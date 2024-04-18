package com.example.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.example.pojo.Pagination;
import com.example.pojo.Person;
import com.example.pojo.Province;
import com.example.pojo.ResponseBean;
import com.example.service.IPersonService;
import com.example.service.IProvinceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private IProvinceService provinceService;

    @ApiOperation(value = "获取所有人员信息(分页)")
    @GetMapping("/get")
    public ResponseBean<Pagination> getPersonByPage(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer size,
                                                    Person person){
        return personService.getPersonByPage(currentPage,size,person);
    }

    @ApiOperation(value = "添加人员信息")
    @PostMapping("/add")
    public ResponseBean<String> addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @ApiOperation(value = "更新人员信息")
    @PutMapping("/update")
    public ResponseBean<String> updatePerson(@RequestBody Person person){
        if (personService.updateById(person)){
            return ResponseBean.success("更新成功");
        }
        return ResponseBean.error("更新失败");
    }

    @ApiOperation(value = "删除人员信息")
    @DeleteMapping("/delete/{id}")
    public ResponseBean<String> deletePerson(@PathVariable Integer id){
        if (personService.removeById(id)){
            return ResponseBean.success("删除成功");
        }
        return ResponseBean.error("删除失败");
    }

    @ApiOperation(value = "导入人员信息")
    @ApiImplicitParams({@ApiImplicitParam(name="file",value = "上传文件",dataType = "MultipartFile")})
    @PostMapping("/import")
    public ResponseBean<String> importPerson(MultipartFile file) {
        ImportParams params = new ImportParams();
        List<Province> provinces = provinceService.list();
        try {
            List<Person> list = ExcelImportUtil.importExcel(file.getInputStream(), Person.class, params);
            list.forEach(person ->{
                person.setProvinceId(provinces.get(provinces.indexOf(new Province(person.getProvince().getName()))).getId());
            });
            if (personService.saveBatch(list)){
                return ResponseBean.success("导入成功");
            }
            return ResponseBean.error("导入失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseBean.error("导入失败");
    }

    @ApiOperation(value = "导出模板")
    @GetMapping(value="/export_template",produces = "application/octet-stream")
    public void exportTemplate(HttpServletResponse response) {
        List<Person> list = personService.randomGeneratePerson();
        ExportParams params = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(params,Person.class,list);
        ServletOutputStream outputStream = null;
        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("人员信息模板.xls","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导出人员信息数据")
    @GetMapping(value="/export_data",produces = "application/octet-stream")
    public void exportPersonData(HttpServletResponse response) {
        List<Person> list = personService.getPerson(null);
        ExportParams params = new ExportParams();
        Workbook workbook = ExcelExportUtil.exportExcel(params,Person.class,list);
        ServletOutputStream outputStream = null;
        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("人员信息表.xls","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
