package com.ponking.crud.controller;/*
@author Ponking
@date 2019/9/14--22:48
*/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ponking.crud.bean.Employee;
import com.ponking.crud.bean.Msg;
import com.ponking.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
    public Msg getEmp(@PathVariable("id")Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }


    /**
     * 检查用户名是否可用
     * @param empName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkUser(@RequestParam("empName") String empName){
        String regx = "^[0-9a-zA-Z-_\\u2E80-\\u9FFF]{4,16}$";
        if(!empName.matches(regx)){
            return Msg.fail().add("validateMessage","用户名可以由6-16位由数字、大小写字母和中文-_组成");
        }
        boolean bool = employeeService.checkUser(empName);
        if(bool){
            return Msg.success();
        }else {
            return Msg.fail().add("validateMessage","用户名不可用");
        }
    }
    /**
     *分页数据
     * @param pn
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/emps")
    public Msg getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        /*
       这不是一个分页查询
       引入PageHelper分页插件
       在查询之前只需要调用,传入页码,以及每页的大小
         */
        PageHelper.startPage(pn,5);
        //startPage后面紧跟的这个查询就是一个分页查询
        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果,只需要将pageInfo交给页面就行
        PageInfo pageInfo = new PageInfo(emps);
        //Msg result = new Msg();
        return Msg.success().add("pageInfo",pageInfo);
    }



    /**
     * 更新员工
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
    public Msg saveEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success().add("emp", employee.toString());
    }


    /**
     * 员工保存
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        //JSR303校验
        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            Map<String,Object> map=new HashMap<String,Object>();
            for(FieldError fieldError: errors){
                System.out.println("错误信息的字段名"+fieldError.getField());
                System.out.println("错误信息"+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorField",map);
        }
        employeeService.saveEmp(employee);
        return Msg.success();
    }

    /**
     * 根据id值删除单个员工
     * @param ids
     * @return
     */
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmpById(@PathVariable("ids") String ids){
        if(!ids.contains("-")){
            //如果ids有"-",认为传入的是单个id,进行单个id,进行单个删除操作
            Integer id = Integer.parseInt(ids);
            System.out.println(id);
            employeeService.deleteEmp(id);
        }else {
            //如果ids中有"-",认为传入的是多个id,将ids解析进行批量删除操作
            List<Integer> id = new ArrayList<>();
            String [] idStr = ids.split("-");
            for(String str:idStr){
                id.add(Integer.parseInt(str));
                System.out.println(str+" ");
            }
            employeeService.deleteBatch(id);
        }
        return Msg.success();
    }
}
