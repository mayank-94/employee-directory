/**
 * 
 */
package com.spring.boot.employeedirectory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.employeedirectory.dao.EmployeeJPARepo;
import com.spring.boot.employeedirectory.model.Employee;

/**
 * @author Mayank
 *
 */
//CachePut will always execute method body, Cacheable will first check the cache, if data is not there then 
//it will hit db. CacheEvict will remove data from cache

@Service
public class JPARepositoryServiceImpl implements JPARepositoryService {
	
	@Autowired
	private EmployeeJPARepo repo;
	
	@Override
	public List<Employee> findAllEmployees() {
		return repo.findAll();
	}

	@Override
	@Cacheable(cacheNames = "employees", key = "#id") //cache is key-value pair
	public Employee findEmployeeById(int id) {
		Optional<Employee> employee = repo.findById(id);
		if(employee.isPresent())
			return employee.get();
		
		throw new RuntimeException("Employee with id, "+id+" does not exist");
	}
	
	@Transactional
	@Override
	public void save(Employee employee) {
		repo.save(employee);
	}
	
	@Transactional
	@Override
	@CacheEvict(cacheNames = "employees", key = "#id")
	public void deleteById(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Employee> findAllOrderBylastName() {
		return repo.findAllOrderBylastName();
	}
	
	@Transactional
	@Override
	@CachePut(cacheNames = "employees", key = "#id")
	public Employee update(int id, Employee employee) {
		Employee updatedEmployee = repo.findById(id)
				.orElseThrow(() -> new RuntimeException("Can't update employee"));
		updatedEmployee.setFirstName(employee.getFirstName());
		updatedEmployee.setLastName(employee.getLastName());
		updatedEmployee.setEmail(employee.getEmail());
		return updatedEmployee;
	}

}
