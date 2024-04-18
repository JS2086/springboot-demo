package com.example.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_person")
@ApiModel(value="Person对象", description="")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "人员id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    @Excel(name = "身份证号",width = 20)
    private String idCard;

    @ApiModelProperty(value = "省份id")
    private Integer provinceId;

    @ApiModelProperty(value = "性别")
    @Excel(name = "性别")
    private String gender;

    @ApiModelProperty(value = "年龄")
    @Excel(name = "年龄")
    private Integer age;

    @ApiModelProperty(value = "出生日期")
    @Excel(name = "出生日期",format = "yyyy-MM-dd")
    private LocalDate birth;

    @ApiModelProperty(value = "体重")
    @Excel(name = "体重")
    private Integer weight;

    @ApiModelProperty(value = "省份")
    @TableField(exist = false)
    @ExcelEntity(name = "省份")
    private Province province;

}
