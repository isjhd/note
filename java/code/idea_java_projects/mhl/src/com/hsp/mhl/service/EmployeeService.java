package com.hsp.mhl.service;

import com.hsp.mhl.dao.EmployeeDAO;
import com.hsp.mhl.domain.Employee;

/* @author  i-s-j-h-d
 * @version 1.0
 * 该类完成对employee表的各种操作(通过调用EmployeeDAO对象完成)
 */
public class EmployeeService {

    //定义一个 EmployeeDAO 属性
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    //方法，根据empId 和 pwd 返回一个Employee对象
    //如果查询不到，就返回null
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {

        Employee employee = employeeDAO.querySingle
                ("select * from employee where empId=? and pwd=md5(?)", Employee.class, empId, pwd);

        return employee;
    }

}
