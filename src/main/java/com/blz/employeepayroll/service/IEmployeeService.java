package com.blz.employeepayroll.service;

import com.blz.employeepayroll.dto.EmployeeDTO;
import com.blz.employeepayroll.model.EmployeeModel;

public interface IEmployeeService {
    EmployeeModel addEmployee(EmployeeDTO employeeDTO);
    EmployeeModel updateEmployeeById(EmployeeDTO employeeDTO, long id);

}
