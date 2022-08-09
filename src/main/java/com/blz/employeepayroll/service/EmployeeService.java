package com.blz.employeepayroll.service;

import com.blz.employeepayroll.dto.EmployeeDTO;
import com.blz.employeepayroll.model.EmployeeModel;
import com.blz.employeepayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeModel addEmployee(EmployeeDTO employeeDTO) {
        EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
        employeeRepository.save(employeeModel);
        return employeeModel;
    }

    @Override
    public EmployeeModel updateEmployeeById(EmployeeDTO employeeDTO, long id) {
        Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
        if (isEmployeePresent.isPresent()) {
            isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
            isEmployeePresent.get().setLastName(employeeDTO.getLastName());
            isEmployeePresent.get().setMobileNum(employeeDTO.getMobileNum());
            isEmployeePresent.get().setSalary(employeeDTO.getSalary());
            isEmployeePresent.get().setDepartment(employeeDTO.getDepartment());
            isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
            employeeRepository.save(isEmployeePresent.get());
            return isEmployeePresent.get();
        }
        return null;
    }
}
