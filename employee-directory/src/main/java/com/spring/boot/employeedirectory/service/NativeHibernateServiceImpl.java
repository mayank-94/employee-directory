/**
 * 
 */
package com.spring.boot.employeedirectory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.employeedirectory.dao.NativeHibernateDAO;
import com.spring.boot.employeedirectory.model.Employee;

/**
 * @author Mayank
 *
 */
@Service
public class NativeHibernateServiceImpl implements NativeHibernateService {
	
	@Autowired
	private NativeHibernateDAO dao;
	
	@Override
	@Transactional
	public List<Employee> findAllEmployees() {
		return dao.findAllEmployees();
	}

	@Override
	@Transactional
	@Cacheable(cacheNames = "employeesNative", key = "#id")
	public Employee findEmployeeById(int id) {
		return dao.findEmployeeById(id);
	}

	@Override
	@Transactional
	public void save(Employee employee) {
		dao.save(employee);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		dao.deleteById(id);
	}

}
