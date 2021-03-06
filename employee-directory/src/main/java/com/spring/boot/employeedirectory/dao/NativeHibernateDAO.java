/**
 * 
 */
package com.spring.boot.employeedirectory.dao;

import java.util.List;

import com.spring.boot.employeedirectory.model.Employee;

/**
 * @author Mayank
 *
 */
public interface NativeHibernateDAO {
	
	List<Employee> findAllEmployees();
	
	Employee findEmployeeById(int id);
	
	void save(Employee employee);
	
	void deleteById(int id);
	
}
