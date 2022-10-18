package com.beanoung.ssm.service;

import com.beanoung.ssm.pojo.Emp;
import com.github.pagehelper.PageInfo;

public interface EmpService {
    /* List<Emp> getAllEmp(); */

    PageInfo<Emp> getEmpPage(Integer pageNum);

    Emp getEmpById(Integer empId);

    void addEmp(Emp emp);

    void updateEmp(Emp emp);

    void deleteEmpById(Integer empId);
}
