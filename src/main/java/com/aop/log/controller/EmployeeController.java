package com.aop.log.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.log.exception.ResourceNotFoundException;
import com.aop.log.model.Employee;
import com.aop.log.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> getEmployees() {

		return employeeService.getEmployees();
	}

	@GetMapping("/employess/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathParam(value = "id") Long employeeId)
			throws ResourceNotFoundException {

		Employee employee = employeeService.getEmployeeById(employeeId);

		return ResponseEntity.ok(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {

		employeeService.createEmployee(employee);
		return employee;
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathParam(value = "id") Long employeeId,
			@RequestBody Employee employee) throws ResourceNotFoundException {
		Employee result = employeeService.updateEmployee(employee, employeeId);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/employee/{id}")
	public Map<String, Boolean> deleteEmployee(@PathParam(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Map<String, Boolean> result = employeeService.deleteEmployee(employeeId);
		return result;
	}
}
