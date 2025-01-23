package com.atguigu.admin.mapper;

import com.atguigu.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Mapper
public interface AccountMapper {

    public Account getAcct(Integer id);

}
