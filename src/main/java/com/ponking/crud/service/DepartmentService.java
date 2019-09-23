package com.ponking.crud.service;/*
@author Ponking
@date 2019/9/18--0:01
*/

import com.ponking.crud.bean.Department;
import com.ponking.crud.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepts() {
       List<Department> list = departmentMapper.selectByExample(null);
       return  list;
    }
}
