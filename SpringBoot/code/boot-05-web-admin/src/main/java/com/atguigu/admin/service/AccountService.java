package com.atguigu.admin.service;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account getAcctByid(Integer id) {
        return accountMapper.getAcct(id);
    }

}
