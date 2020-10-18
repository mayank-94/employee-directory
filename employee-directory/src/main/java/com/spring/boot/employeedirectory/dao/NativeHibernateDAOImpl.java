/**
 * 
 */
package com.spring.boot.employeedirectory.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boot.employeedirectory.model.Employee;

/**
 * @author Mayank
 *
 */
@Repository
public class NativeHibernateDAOImpl implements NativeHibernateDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Employee> findAllEmployees() {
		//get the Current Hibernate Session
		Session session = entityManager.unwrap(Session.class);
		
		//create a Query
		Query<Employee> query = session.createQuery("from Employee", Employee.class);
		
		//execute the Query and get ResultList
		List<Employee> employees = query.getResultList();
		return employees;
	}

	@Override
	public Employee findEmployeeById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		Employee employee = session.get(Employee.class, id);
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		Session session = entityManager.unwrap(Session.class);
		session.save(employee);
	}

	@Override
	public void deleteById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		Query<Employee> query = session.createQuery("delete from Employee where id =:empId", Employee.class);
		query.setParameter("empId", id);
		query.executeUpdate();
		
	}

}
