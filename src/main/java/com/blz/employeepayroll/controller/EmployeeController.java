package com.blz.employeepayroll.controller;

import com.blz.employeepayroll.dto.EmployeeDTO;
import com.blz.employeepayroll.model.EmployeeModel;
import com.blz.employeepayroll.service.IEmployeeService;
import com.blz.employeepayroll.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayroll")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @PostMapping("/addemployee")
    public EmployeeModel addEmployee(@RequestBody EmployeeDTO employeeDTO,@RequestParam Long departmentId) {
        return employeeService.addEmployee(employeeDTO,departmentId);
    }

    @PutMapping("/updateemployee/{id}")
    public EmployeeModel updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable long id,@RequestHeader String token, @RequestParam Long departmentId) {
        return employeeService.updateEmployeeById(employeeDTO,id,token, departmentId);
    }

    @GetMapping("/getemployeedata")
    public List<EmployeeModel> getAllEmployee(@RequestHeader String token) {
        return employeeService.getAllEmployee(token);
    }

    @DeleteMapping("/deleteemployee/{id}")
    public EmployeeModel deleteEmployee(@PathVariable Long id, @RequestHeader String token) {
        return employeeService.deleteEmployee(id, token);
    }

    @PostMapping("/login")
    public Response login(@RequestParam String email, @RequestParam String password){
        return employeeService.login(email,password);
    }
}
