package com.ponking.crud.service;/*
@author Ponking
@date 2019/9/15--15:44
*/

import com.ponking.crud.BaseTest;
import com.ponking.crud.bean.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeMapperTest extends BaseTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void test01(){
        List<Employee> emps = employeeService.getAll();
        for(Employee emp: emps){
            System.out.println(emp.toString());
        }
    }
}
