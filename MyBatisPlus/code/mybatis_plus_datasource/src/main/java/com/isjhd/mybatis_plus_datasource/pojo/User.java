package com.isjhd.mybatis_plus_datasource.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Data
public class User {

    @TableId
    private Integer uid;

    private String name;

    private Integer age;

    private Integer sex;

    private String email;

    private Integer isDeleted;

}
