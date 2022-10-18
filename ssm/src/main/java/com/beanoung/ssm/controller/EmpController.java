package com.beanoung.ssm.controller;

import com.beanoung.ssm.pojo.Emp;
import com.beanoung.ssm.service.EmpService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (废弃)查询所有的员工信息-->/emp-->get
 * 查询带分页功能的所有的员工信息-->/emp/page/1-->get
 * 根据id查询员工信息-->/emp/1-->get
 * 跳转到添加员工页面-->/emp/to/add-->get
 * 添加员工信息-->/emp-->post
 * 跳转到修改员工信息页面-->/emp/to/update-->get
 * 修改员工信息-->/emp-->put
 * 删除员工信息-->/emp/1-->delete
 */
@Controller
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 查询带分页功能的所有的员工信息-->/emp/page/1-->get
     *
     * @param pageNum 分页的当前页数
     * @param model   model传数据
     * @return 视图
     */
    @RequestMapping(value = "/emp/page/{pageNum}", method = RequestMethod.GET)
    public String getAllEmpWithPage(@PathVariable("pageNum") Integer pageNum, Model model) {
        PageInfo<Emp> pageInfo = empService.getEmpPage(pageNum);
        model.addAttribute("page", pageInfo);
        return "emp_list";
    }

    /**
     * 根据id查询员工信息-->/emp/1-->get
     * @param empId 员工id
     * @param model 传数据
     * @return 视图
     */
    @RequestMapping("/emp/byId")
    public String getEmpById(@RequestParam("empId") Integer empId, Model model) {
        Emp emp = empService.getEmpById(empId);
        model.addAttribute("emp",emp);
        return "emp_detail";
    }

    /**
     * 跳转到添加员工页面-->/emp/to/add-->get
     * @return 新增员工信息页面
     */
    @RequestMapping(value = "/emp/to/add",method = RequestMethod.GET)
    public String toAddEmp(){
        return "emp_add";
    }

    /**
     * 添加员工信息-->/emp-->post
     * @param emp 实体对象
     * @return 重定向到员工信息列表最后一页
     */
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    public String addEmp(Emp emp){
        empService.addEmp(emp);
        PageInfo<Emp> pageInfo = empService.getEmpPage(1);
        return "redirect:/emp/page/"+pageInfo.getPages();
    }

    /**
     * 跳转到修改员工信息页面-->/emp/to/update-->get
     * @param empId 员工ID
     * @param model 数据
     * @return 视图
     */
    @RequestMapping(value = "/emp/to/update/{empId}",method = RequestMethod.GET)
    public String toUpdateEmp(@PathVariable("empId") Integer empId,Model model){
        Emp emp = empService.getEmpById(empId);
        model.addAttribute("emp",emp);
        return "emp_update";
    }

    /**
     * 修改员工信息-->/emp-->put
     * @param emp emp对象
     * @return 视图
     */
    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String updateEmp(Emp emp){
        empService.updateEmp(emp);
        return "redirect:/emp/page/1";
    }

    /**
     * 删除员工信息-->/emp/1-->delete
     * @param empId 员工id
     * @return 视图
     */
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.DELETE)
    public String deleteEmpById(@PathVariable("empId") Integer empId){
        empService.deleteEmpById(empId);
        return "redirect:/emp/page/1";
    }
}
