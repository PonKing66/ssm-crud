package com.ponking.crud.dao;/*
@author Ponking
@date 2019/9/14--16:30
*/

import com.ponking.crud.BaseTest;
import com.ponking.crud.bean.Employee;
import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EmployeeMapperTest extends BaseTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCrud() {
       // employeeMapper.insertSelective(new Employee(1, "Jerry", "M", "Jerry@vi.com", 1));
        //批量增加employee
        EmployeeMapper em = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int number = random.nextInt(10);
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            em.insertSelective(new Employee(null, uid, number%2==1?"M":"F", uid + "@ponking.top", number%2+1));
        }
    }


    @Test
    public void testSelectByPrimaryKeyWithDept(){
        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(115);
        System.out.println(employee.toString());

    }

    @Test
    public void test012(){
        List<Employee> employees = employeeMapper.selectByExampleWithDept(null);
        for(Employee emp : employees){
            System.out.println(emp.toString());
        }

    }
}
