package com.vij.employee.management.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.vij.employee.management.pojo.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class EmployeeControllerTest {

	@Test
	public void getEmployeesTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8080/employees/get";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		// Assert.assertEquals(true, result.getBody().contains("employeeList"));
	}

	@Test
	public void getEmployeeTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8080/employees/get/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		Employee result = restTemplate.getForObject(baseUrl, Employee.class, params);

		// Verify request succeed
		Assert.assertNotEquals(null, result);
		Assert.assertEquals(1, result.getEmployeeId());
	}

	@Test
	public void addEmployeesTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080/employees/add";
		URI uri = new URI(baseUrl);
		Employee employee = new Employee(10, "Jackson", "9712379127", "test@email.com");

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Employee> request = new HttpEntity<Employee>(employee, headers);

		try {
			ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
			Assert.assertEquals(202, result.getStatusCodeValue());
			Assert.assertEquals(true, result.getBody().contains("Successfully added a new Employee"));
		} catch (HttpClientErrorException ex) {
			// Verify bad request and missing header
			Assert.assertEquals(400, ex.getRawStatusCode());
			Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}

	@Test
	public void updateEmployeeTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:8080/employees/update/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "2");

		Employee updatedEmployee = new Employee(2, "asdajbas", "2347824323", "test@email.com");

		restTemplate.put(baseUrl, updatedEmployee, params);

	}

	@Test
	public void deleteEmployeeTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8080/employees/delete/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		restTemplate.delete(baseUrl, params);

	}

}
