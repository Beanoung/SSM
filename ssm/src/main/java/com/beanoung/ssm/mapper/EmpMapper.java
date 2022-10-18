package com.beanoung.ssm.mapper;

import com.beanoung.ssm.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    List<Emp> getAllEmp();

    Emp getEmpById(Integer empId);

    void addEmp(Emp emp);

    void updateEmp(Emp emp);

    void deleteEmpById(Integer empId);
}
