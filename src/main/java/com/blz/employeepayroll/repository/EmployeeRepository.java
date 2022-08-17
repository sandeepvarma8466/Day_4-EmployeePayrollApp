package com.blz.employeepayroll.repository;

import com.blz.employeepayroll.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    Optional<EmployeeModel> findByEmailId(String email);

}
