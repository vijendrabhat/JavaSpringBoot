package com.vij.employee.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vij.employee.management.exception.EmployeeException;
import com.vij.employee.management.pojo.Employee;
import com.vij.employee.management.transaction.EmployeeDao;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;

	// fetching all employees
	public List<Employee> getAllEmployees() {
		List<Employee> emps = employeeDao.getAllEmployees();
		return emps;
	}

	// fetching employee by id
	public Employee getEmployee(int id) throws EmployeeException{
		return employeeDao.getEmployee(id);
	}

	// adding employee
	public void addEmployee(Employee e) throws EmployeeException{
		int maxId = 0;
		List<Employee> empList = employeeDao.getAllEmployees();
		for(Employee emp: empList)
		{
			if(maxId < emp.getEmployeeId())
			{
				maxId = emp.getEmployeeId();
			}
		}
		Integer id = maxId + 1;
		e.setEmployeeId(id);
		employeeDao.addEmployee(e);
	}

	// updating employee by id
	public void updateEmployee(Employee emp, int id) throws EmployeeException{
		employeeDao.updateEmployee(emp, id);
	}

	// deleting employee by id
	public void deleteEmployee(int id) throws EmployeeException{
		employeeDao.deleteEmployee(id);
	}

}
