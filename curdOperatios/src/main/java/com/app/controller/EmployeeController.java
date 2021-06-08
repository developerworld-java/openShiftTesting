package com.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.EmployeeService;
import com.app.service.exception.RecordNotFoundException;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping("/hello")
	public ResponseEntity<List<String>> getDemoList(){
		 List<String> list = Arrays.asList("emp1","emp2","emp3");
		return new  ResponseEntity<List<String>>(list, new HttpHeaders(), HttpStatus.OK);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployeeList(){
		 List<Employee> list = empService.getEmployeeList();
		return new  ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
		
	}

	 @GetMapping("/{id}")
	    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) 
	                                                    throws RecordNotFoundException {
		 Employee entity = empService.getEmployeeById(id);
	 
	        return new ResponseEntity<Employee>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @PostMapping
	    public ResponseEntity<Employee> createOrUpdateEmployee(Employee employee)
	                                                    throws RecordNotFoundException {
	    	Employee updated = empService.createOrUpdateEmployee(employee);
	        return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @DeleteMapping("/{id}")
	    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id) 
	                                                    throws RecordNotFoundException {
	    	empService.deleteEmployeeById(id);
	        return HttpStatus.FORBIDDEN;
	    }
}
