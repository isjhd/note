package com.isjhd.mybatis_plus_datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isjhd.mybatis_plus_datasource.mapper.UserMapper;
import com.isjhd.mybatis_plus_datasource.pojo.User;
import com.isjhd.mybatis_plus_datasource.service.UserService;
import org.springframework.stereotype.Service;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
