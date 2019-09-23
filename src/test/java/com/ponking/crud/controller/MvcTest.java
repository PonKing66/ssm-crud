package com.ponking.crud.controller;/*
@author Ponking
@date 2019/9/15--14:39
*/

import com.github.pagehelper.PageInfo;
import com.ponking.crud.BaseTest;
import com.ponking.crud.bean.Department;
import com.ponking.crud.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


public class MvcTest extends BaseTest {

    // 引入Spring mvc的ioc容器
    @Autowired
    WebApplicationContext context;

    //虚拟mvc请求, 获取到处理结果
    MockMvc mockMvc;
    @Before
    public void initMock(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception{
        //模拟请求拿到返回值
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/emps").
                param("pn","7")).andReturn();

        //取出pageInfo

    }


    @Test
    public void testDepts() throws Exception{
        //模拟请求拿到返回值
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/depts")).andReturn();
        //取出pageInfo
        MockHttpServletRequest request = mvcResult.getRequest();
    }
}
