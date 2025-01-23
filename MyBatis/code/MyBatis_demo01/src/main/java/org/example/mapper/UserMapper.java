package org.example.mapper;

import org.example.pojo.User;

import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
public interface UserMapper {

    /**
     * 查询所有的用户信息
     */
    List<User> getAllUser();

}
