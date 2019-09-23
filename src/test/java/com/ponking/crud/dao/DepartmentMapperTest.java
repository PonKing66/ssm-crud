package com.ponking.crud.dao;/*
@author Ponking
@date 2019/9/14--16:15
*/


import com.ponking.crud.BaseTest;
import com.ponking.crud.bean.Department;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class DepartmentMapperTest extends BaseTest {

    @Autowired
    DepartmentMapper departmentMapper;


    @Test
    public void test01(){
        departmentMapper.insertSelective(new Department(null,"开发部"));
        departmentMapper.insertSelective(new Department(null,"测试部"));
    }


    @Test
    public void test02(){
        List<Department> list = departmentMapper.selectByExample(null);
        for(Department dept: list){
            System.out.println(dept.toString());
        }
    }


}
