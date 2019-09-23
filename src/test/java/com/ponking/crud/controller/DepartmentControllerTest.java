package com.ponking.crud.controller;/*
@author Ponking
@date 2019/9/18--0:13
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ponking.crud.BaseTest;
import com.ponking.crud.bean.Employee;
import com.ponking.crud.service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentControllerTest extends BaseTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void test01(){
        //startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps1 = employeeService.getAll();
        for (Employee emp:emps1){
            System.out.println(emp.toString());
        }


    }
}
