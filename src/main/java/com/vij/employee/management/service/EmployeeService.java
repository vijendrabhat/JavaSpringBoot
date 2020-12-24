package com.vij.employee.management.service;

import java.util.List;

import com.vij.employee.management.exception.EmployeeException;
import com.vij.employee.management.pojo.Employee;


public interface EmployeeService {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployee(int id) throws EmployeeException;
	
	public void addEmployee(Employee e) throws EmployeeException;
	
	public void updateEmployee(Employee emp, int id) throws EmployeeException;
	
	public void deleteEmployee(int id) throws EmployeeException;
}
