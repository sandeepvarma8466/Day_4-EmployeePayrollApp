package com.blz.employeepayroll.service;

import com.blz.employeepayroll.dto.EmployeeDTO;
import com.blz.employeepayroll.model.EmployeeModel;
import com.blz.employeepayroll.util.Response;

import java.util.List;

public interface IEmployeeService {
    EmployeeModel addEmployee(EmployeeDTO employeeDTO, Long departmentId);
    EmployeeModel updateEmployeeById(EmployeeDTO employeeDTO, long id, String token, Long departmentId);

    List<EmployeeModel> getAllEmployee(String token);

    EmployeeModel deleteEmployee(Long id, String token);

    Response login(String email, String password);
}
