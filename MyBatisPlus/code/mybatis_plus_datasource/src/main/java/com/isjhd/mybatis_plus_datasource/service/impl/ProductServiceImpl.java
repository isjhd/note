package com.isjhd.mybatis_plus_datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isjhd.mybatis_plus_datasource.mapper.ProductMapper;
import com.isjhd.mybatis_plus_datasource.pojo.Product;
import com.isjhd.mybatis_plus_datasource.service.ProductService;
import org.springframework.stereotype.Service;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
