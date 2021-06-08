package com.app.service;

import java.util.List;

import com.app.model.Employee;

public interface EmployeeService {

	List<Employee> getEmployeeList();

	Employee getEmployeeById(Long id);

	Employee createOrUpdateEmployee(Employee employee);

	void deleteEmployeeById(Long id);

}
