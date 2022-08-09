package com.blz.employeepayroll.repository;

import com.blz.employeepayroll.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

}
