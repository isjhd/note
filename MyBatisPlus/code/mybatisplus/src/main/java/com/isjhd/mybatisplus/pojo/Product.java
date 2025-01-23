package com.isjhd.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Data
public class Product {

    private Long id;
    private String name;
    private Integer price;
    @Version //标识乐观锁版本号总段
    private Integer version;

}
