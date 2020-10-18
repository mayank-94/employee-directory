/**
 * 
 */
package com.spring.boot.employeedirectory.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.employeedirectory.model.Employee;
import com.spring.boot.employeedirectory.service.JPARepositoryService;
import com.spring.boot.employeedirectory.service.NativeHibernateService;

/**
 * @author Mayank
 *
 */
@RestController
@RequestMapping(path="/api")
public class EmployeeResource {
	
	@Autowired
	private NativeHibernateService service;
	
	@Autowired
	private JPARepositoryService jpaService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> employees = service.findAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id){
		Employee employee = service.findEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK); 
	}
	
	@GetMapping("/jpa/employees")
	public ResponseEntity<List<Employee>> getAllEmployeesJpa(){
		List<Employee> employees = jpaService.findAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/jpa/employees/{id}")
	public ResponseEntity<Employee> getEmployeeJpa(@PathVariable int id){
		Employee employee = jpaService.findEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/employees")
	public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee){
		jpaService.save(employee);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/jpa/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){
		jpaService.deleteById(id);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/jpa/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
		Employee updateEmployee = jpaService.update(id, employee);
		return new ResponseEntity<Employee>(updateEmployee, HttpStatus.OK);
	}
	
	
	@GetMapping("/jpa/employees/order")
	public ResponseEntity<List<Employee>> getAllEmployeesOrderByLastName(){
		List<Employee> employees = jpaService.findAllOrderBylastName();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
}
