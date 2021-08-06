package com.aop.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aop.log.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
