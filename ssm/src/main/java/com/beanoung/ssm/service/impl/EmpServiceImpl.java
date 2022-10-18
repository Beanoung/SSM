package com.beanoung.ssm.service.impl;

import com.beanoung.ssm.mapper.EmpMapper;
import com.beanoung.ssm.pojo.Emp;
import com.beanoung.ssm.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  //事务
public class EmpServiceImpl implements EmpService {

    /* @Autowired
    //在spring配置文件中配置SqlSessionFactoryBean后就可以这样自动注入,而省略繁琐的获取sqlSession过程
    private SqlSessionFactoryBean sqlSessionFactoryBean; */

    @Autowired
    //在spring配置文件中配置MapperScannerConfigurer后就可以这样自动注入,进一步优化获取过程(上面的就可以不用了)
    private EmpMapper empMapper;

    /* @Override
    //不带分页信息的所有员工信息
    public List<Emp> getAllEmp() {
        return empMapper.getAllEmp();
    } */

    @Override
    public PageInfo<Emp> getEmpPage(Integer pageNum) {
        //开启分页
        PageHelper.startPage(pageNum,5);    //本质是拦截器

        List<Emp> list = empMapper.getAllEmp();
        //获取分页信息
        PageInfo pageInfo=new PageInfo<>(list,5);
        return pageInfo;
    }

    @Override
    public Emp getEmpById(Integer empId) {
        return empMapper.getEmpById(empId);
    }

    @Override
    public void addEmp(Emp emp) {
        empMapper.addEmp(emp);
    }

    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
    }

    @Override
    public void deleteEmpById(Integer empId) {
        empMapper.deleteEmpById(empId);
    }
}
