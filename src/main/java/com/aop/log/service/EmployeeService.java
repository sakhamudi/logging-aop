package com.aop.log.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aop.log.exception.ResourceNotFoundException;
import com.aop.log.model.Employee;
import com.aop.log.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepositary;

	public List<Employee> getEmployees() {
		return employeeRepositary.findAll();
	}

	public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
		Employee employee = employeeRepositary.getById(employeeId);
		if (employee == null) {

			throw new ResourceNotFoundException("Employee not found ");

		}
		return employee;
	}

	public void createEmployee(Employee employee) {

		employeeRepositary.save(employee);

	}

	public Employee updateEmployee(Employee employee, Long employeeId) throws ResourceNotFoundException {

		Employee existingEmployee = employeeRepositary.getById(employeeId);
		if (existingEmployee == null) {
			throw new ResourceNotFoundException("Employee not found ");
		}
		existingEmployee.setEmailId(employee.getEmailId());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setFirstName(employee.getFirstName());
		return employeeRepositary.save(employee);

	}

	public Map<String, Boolean> deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		Map<String, Boolean> response = new HashMap<>();
		try {
			employeeRepositary.deleteById(employeeId);
			response.put("deleted", Boolean.TRUE);
		} catch (Exception e) {
			// TODO: handle exception
			response.put("deleted", Boolean.FALSE);
		}
		return response;
	}

}
