package com.isjhd.mybatisx.mapper;
import org.apache.ibatis.annotations.Param;

import com.isjhd.mybatisx.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 34782
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-04-10 15:11:38
* @Entity com.isjhd.mybatisx.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    int insertSelective(User user);

}




