package com.ponking.crud;/*
@author Ponking
@date 2019/9/14--16:19
*/

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.context.web.WebAppConfiguration;


/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:spring/spring-mvc.xml" })
public class BaseTest {
}
