/**
 * 
 */
package com.spring.boot.employeedirectory.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.employeedirectory.model.Employee;

/**
 * @author Mayank
 *
 */
@Repository
public interface EmployeeJPARepo extends JpaRepository<Employee, Integer>{
	
	@Query("Select e From Employee e Order by lastName")
	List<Employee> findAllOrderBylastName();	
}
