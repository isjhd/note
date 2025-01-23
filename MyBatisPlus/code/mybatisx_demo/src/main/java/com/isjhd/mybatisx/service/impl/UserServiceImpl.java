package com.isjhd.mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isjhd.mybatisx.pojo.User;
import com.isjhd.mybatisx.service.UserService;
import com.isjhd.mybatisx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 34782
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-04-10 15:11:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




