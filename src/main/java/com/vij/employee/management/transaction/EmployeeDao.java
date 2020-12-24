package com.vij.employee.management.transaction;

import java.util.List;

import com.vij.employee.management.pojo.Employee;


public interface EmployeeDao {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployee(int id);
	
	public void addEmployee(Employee employee);
	
	public void updateEmployee(Employee employee, int id);
	
	public void deleteEmployee(int id);
	
}
