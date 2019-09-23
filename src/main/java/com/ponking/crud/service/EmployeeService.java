package com.ponking.crud.service;/*
@author Ponking
@date 2019/9/14--23:03
*/

import com.ponking.crud.bean.Employee;
import com.ponking.crud.bean.EmployeeExample;
import com.ponking.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 查询所有的员工
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 员工保存
     * @param employee
     */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    /**
     *员工更新
     * @param employee
     */
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 根据id值获取员工
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id值删除该员工
     * @param id
     */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除员工
     * @param id
     */
    public void deleteBatch(List<Integer> id) {
        //创建根据id批量删除的条件
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(id);
        employeeMapper.deleteByExample(example);
    }
}
