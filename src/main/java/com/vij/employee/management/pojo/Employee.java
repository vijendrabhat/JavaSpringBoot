package com.vij.employee.management.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.fasterxml.jackson.annotation.JsonIgnore;

@EntityScan
public class Employee {
	
	private int employeeId;
	
	@NotNull(message = "Name cannot be null")
	@Pattern(regexp="^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$", message="Name is not valid!")
	private String name;
	
	@NotNull(message = "Phonenumber cannot be null")
	@Pattern(regexp="(?:\\(\\d{3}\\)|\\d{3}[-]*)\\d{3}[-]*\\d{4}" , message="Phonenumber is not valid!")
	private String phoneNumber;
	
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid!")
	private String emailId;
	
	public Employee()
	{
		
	}

	public Employee(int employeeId, String name, String phoneNumber, String emailId) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}



	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@JsonIgnore
     private List<Employee> employeeList;
    
    public List<Employee> getEmployeeList() {
        if(employeeList == null) {
            employeeList = new ArrayList<Employee>();
        }
        return employeeList;
    }
  
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", emailId="
				+ emailId + "]";
	}

	public Object contains(String string) {
		// TODO Auto-generated method stub
		return null;
	}
    
    

}
