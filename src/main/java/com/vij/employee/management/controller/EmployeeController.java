package com.vij.employee.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vij.employee.management.controller.EmployeeController;
import com.vij.employee.management.exception.EmployeeException;
import com.vij.employee.management.pojo.Employee;
import com.vij.employee.management.service.EmployeeService;
import com.vij.employee.management.transaction.EmployeeDao;

@RestController
public class EmployeeController {

	static final Logger logger = LogManager.getLogger(EmployeeController.class.getName());

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeDao employeeDao;

	// displaying list of all employees
	@GetMapping(path = "/employees/get", produces = "application/json")
	public List<Employee> getAllEmployee() {
		logger.log(Level.INFO, this.getClass().getSimpleName() + " - Get all employees.");
		return employeeService.getAllEmployees();
	}

	// displaying employee by id
	@GetMapping("/employees/get/{id}")
	public Employee getEmployee(@PathVariable @NotBlank @Size(max = 100) int id) throws EmployeeException {
		logger.log(Level.INFO, this.getClass().getSimpleName() + " - Get employee details by id- " + id);
		if (isEmployeeAvailable(id))
			return employeeService.getEmployee(id);
		else
			throw new EmployeeException("Could not find employee with id- " + id);
	}

	// inserting employee
	@PostMapping(path = "/employees/add")
	public ResponseEntity<Map<String, String>> addEmployees(@Valid @RequestBody Employee employee)
			throws EmployeeException {
		logger.log(Level.INFO, this.getClass().getSimpleName() + " - Add a new employee");
		Map<String, String> response = new HashMap<String, String>();
		try {
			employeeService.addEmployee(employee);
			response.put("ok", "Successfully added a new Employee");
			return ResponseEntity.accepted().body(response);
		} catch (Exception e) {
			throw new EmployeeException("An error expected on processing request" + e.getMessage());
		}
	}

	// updating employee by id
	@PutMapping(path = "/employees/update/{id}")
	public ResponseEntity<Map<String, String>> updateEmployee(@Valid @RequestBody Employee employee,
			@PathVariable  @NotBlank @Size(max = 100) int id) throws EmployeeException {
		System.out.println(this.getClass().getSimpleName() + " - Update a employee with id- " + id);

		Map<String, String> response = new HashMap<String, String>();
		try {
			if (isEmployeeAvailable(id)) {

				Employee emp = employeeService.getEmployee(id);

				if (employee.getName() == null || employee.getName().isEmpty())
					employee.setName(emp.getName());
				if (employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty())
					employee.setPhoneNumber(emp.getPhoneNumber());
				if (employee.getEmailId() == null || employee.getEmailId().isEmpty())
					employee.setEmailId(emp.getEmailId());
				employee.setEmployeeId(id);
				employeeService.updateEmployee(employee, id);
				response.put("ok", "Successfully updated a Employee");
				return ResponseEntity.accepted().body(response);
			} else {
				response.put("error", "Could not find employee with id- " + id);
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			throw new EmployeeException("An error expected on processing request" + e.getMessage());
		}
	}

	// deleting all employees
	@DeleteMapping("/employees/delete/{id}")
	public ResponseEntity<Map<String, String>> deleteEmployees(@PathVariable  @NotBlank @Size(max = 100) int id)
			throws EmployeeException {
		logger.log(Level.INFO, this.getClass().getSimpleName() + " - Delete a employee with id- " + id);
		Map<String, String> response = new HashMap<String, String>();

		try {
			if (isEmployeeAvailable(id)) {
				employeeService.deleteEmployee(id);
				response.put("ok", "Successfully deleted a Employee");
				return ResponseEntity.accepted().body(response);
			} else {
				response.put("error", "Could not find employee with id- " + id);
				return ResponseEntity.badRequest().body(response);
			}
		} catch (Exception e) {
			throw new EmployeeException("An error expected on processing request" + e.getMessage());
		}
	}
	
	public boolean isEmployeeAvailable(int id) {
		List<Employee> empList = employeeDao.getAllEmployees();
		boolean isAvailable = false;
		for (Employee employee : empList) {
			if (id == employee.getEmployeeId()) {
				isAvailable = true;
			}
		}
		return isAvailable;
	}

}
