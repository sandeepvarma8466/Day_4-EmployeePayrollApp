package com.blz.employeepayroll.model;

import com.blz.employeepayroll.dto.EmployeeDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;
    private String firstName;
    private String lastName;
    private long mobileNum;
    private long salary;
    private String companyName;
    @OneToOne
    private DepartmentModel department;
    private String emailId;
    private String password;

    public EmployeeModel(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.salary = employeeDTO.getSalary();
        this.mobileNum = employeeDTO.getMobileNum();
        this.companyName = employeeDTO.getCompanyName();
        this.emailId = employeeDTO.getEmailId();
        this.password = employeeDTO.getPassword();
    }

    public EmployeeModel() {

    }
}
