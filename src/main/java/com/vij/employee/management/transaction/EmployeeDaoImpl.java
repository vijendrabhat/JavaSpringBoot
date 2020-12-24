package com.vij.employee.management.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vij.employee.management.pojo.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

private static List<Employee> list = new ArrayList<Employee>();
    
    static
    {
        list.add(new Employee(1,"Vijendra", "1234567890", "xyz@gmail.com"));
        list.add(new Employee(2,"Goutham", "5678912340", "abc@gmail.com"));
        list.add(new Employee(3,"Utkarsh", "1237893451","asjdasd@gmail.com"));
    }
     
    public List<Employee> getAllEmployees()
    {
        return list;
    }
    
    public Employee getEmployee(int id)
    {
    	List<Employee> empList = getAllEmployees();
    	for(Employee emp : empList)
    	{
    		if(id == emp.getEmployeeId())
    		{
    			return emp;
    		}
    	}
        return null;
    }
     
    public void addEmployee(Employee employee) {
        list.add(employee);
    }
    
    public void updateEmployee(Employee employee, int id) {
        list.set(id - 1, employee);
    }
    
    public void deleteEmployee(int id) {
        list.remove(id - 1);
    }
}
