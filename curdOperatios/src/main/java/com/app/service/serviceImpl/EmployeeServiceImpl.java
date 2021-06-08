package com.app.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;
import com.app.service.EmployeeService;
import com.app.service.exception.RecordNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList =  employeeRepository.findAll();
		
		 if(employeeList.size() > 0) {
	            return employeeList;
	        } else {
	            return new ArrayList<Employee>();
	        }
	}

	 public Employee getEmployeeById(Long id) throws RecordNotFoundException 
	    {
	        Optional<Employee> employee = employeeRepository.findById(id);
	         
	        if(employee.isPresent()) {
	            return employee.get();
	        } else {
	            throw new RecordNotFoundException("No employee record exist for given id");
	        }
	    }
	     
	    public Employee createOrUpdateEmployee(Employee entity) throws RecordNotFoundException 
	    {
	        Optional<Employee> employee = employeeRepository.findById(entity.getEmpId());
	         
	        if(employee.isPresent()) 
	        {
	        	Employee newEntity = employee.get();
	            newEntity.setName(entity.getName());
	            newEntity.setLocation(entity.getLocation());
	 
	            newEntity = employeeRepository.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = employeeRepository.save(entity);
	             
	            return entity;
	        }
	    } 
	     
	    public void deleteEmployeeById(Long id) throws RecordNotFoundException 
	    {
	        Optional<Employee> employee = employeeRepository.findById(id);
	         
	        if(employee.isPresent()) 
	        {
	        	employeeRepository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No employee record exist for given id");
	        }
	    } 
	
}
