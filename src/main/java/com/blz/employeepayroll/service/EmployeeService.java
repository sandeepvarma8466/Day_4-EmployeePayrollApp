package com.blz.employeepayroll.service;

import com.blz.employeepayroll.dto.EmployeeDTO;
import com.blz.employeepayroll.exception.EmployeeNotFoundException;
import com.blz.employeepayroll.model.DepartmentModel;
import com.blz.employeepayroll.model.EmployeeModel;
import com.blz.employeepayroll.repository.DepartmentRepository;
import com.blz.employeepayroll.repository.EmployeeRepository;
import com.blz.employeepayroll.util.Response;
import com.blz.employeepayroll.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public EmployeeModel addEmployee(EmployeeDTO employeeDTO, Long departmentId) {
        Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
        if (isDepartment.isPresent()){
            EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
            employeeModel.setDepartment(isDepartment.get());
            employeeRepository.save(employeeModel);
            String body="Employee is added succesfully with employeeId "+employeeModel.getEmployeeId();
            String subject="Employee Registration Succesfull";
            mailService.send(employeeModel.getEmailId(),subject,body);
            return employeeModel;
        }
        throw  new EmployeeNotFoundException(400,"Employee Not Present");
    }

    @Override
    public EmployeeModel updateEmployeeById(EmployeeDTO employeeDTO, long id, String token, Long departmentId) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
        if (isDepartment.isPresent()) {
            Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(empId);
            if (isEmployeePresent.isPresent()) {
                isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
                isEmployeePresent.get().setLastName(employeeDTO.getLastName());
                isEmployeePresent.get().setMobileNum(employeeDTO.getMobileNum());
                isEmployeePresent.get().setSalary(employeeDTO.getSalary());
                isEmployeePresent.get().setDepartment(isDepartment.get());
                isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
                employeeRepository.save(isEmployeePresent.get());
                String body="Employee is added succesfully with employeeId "+isEmployeePresent.get().getEmployeeId();
                String subject="Employee Registration Succesfull";
                mailService.send(employeeDTO.getEmailId(),subject,body);
                return isEmployeePresent.get();
            }
            throw new EmployeeNotFoundException(400, "Employee Not Present");
        }
        throw new EmployeeNotFoundException(400, "Employee Not Present");
    }

    @Override
    public List<EmployeeModel> getAllEmployee(String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(empId);
        if(isEmployeePresent.isPresent()) {
            List<EmployeeModel> getEmployee = employeeRepository.findAll();
            if(getEmployee.size() > 0) {
                return getEmployee;
            }
            else {
                throw  new EmployeeNotFoundException(400, "No DATA found");
            }
        }
        throw  new EmployeeNotFoundException(400,"Employee Not Present");
    }

    @Override
    public EmployeeModel deleteEmployee(Long id, String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(empId);
        if (isEmployeePresent.isPresent()) {
                employeeRepository.delete(isEmployeePresent.get());
                String body = "Employee Deleted Successfully with Employee id is :" + isEmployeePresent.get().getEmployeeId();
                String subject = "Employee Deleted..";
                mailService.send(isEmployeePresent.get().getEmailId(), body, subject);
                return isEmployeePresent.get();
            }
        throw  new EmployeeNotFoundException(400,"Employee Not Present");
    }

    @Override
    public Response login(String email, String password) {
        Optional<EmployeeModel> isEmailPresent=employeeRepository.findByEmailId(email);
        if(isEmailPresent.isPresent()){
            if(isEmailPresent.get().getPassword().equals(password)){
                String token=tokenUtil.createToken(isEmailPresent.get().getEmployeeId());
                return new Response(200,"login succesfull",token);
            }
            throw new EmployeeNotFoundException(400,"Invald credentials");
        }
        throw new EmployeeNotFoundException(400,"Employee not found");
    }
}
