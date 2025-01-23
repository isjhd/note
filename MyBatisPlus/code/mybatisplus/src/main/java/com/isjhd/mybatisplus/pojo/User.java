package com.isjhd.mybatisplus.pojo;

/* @author  i-s-j-h-d
 * @version 1.0 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.isjhd.mybatisplus.enums.SexEnum;
import lombok.*;

@Data
public class User {

    //将属性所对应的字段指定为主键

    @TableId(value = "uid")
    private Long id;

    //指定属性所对应的字段名
//    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    private SexEnum sex;

    @TableLogic
    private Integer isDeleted;

}
