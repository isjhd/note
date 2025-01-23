package com.isjhd.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.isjhd.mybatisplus.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
