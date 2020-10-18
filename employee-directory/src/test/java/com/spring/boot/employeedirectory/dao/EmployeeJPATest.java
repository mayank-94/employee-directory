package com.spring.boot.employeedirectory.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.boot.employeedirectory.model.Employee;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EmployeeJPATest {
	
	@Autowired
	private EmployeeJPARepo repo;
	
	@Autowired
	private EntityManager em;
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void findByIdTest() {
		Employee emp1 = repo.findById(1).orElseThrow(() -> new RuntimeException());
		Employee emp2 = repo.findById(1).orElseThrow(() -> new RuntimeException());
		assertEquals("Leslie", emp1.getFirstName());
		LOGGER.info("Employee details are - {}", emp1);
		LOGGER.info("Employee details are - {}", emp2);
	}
	
	@Test
	public void findByIdNativeTest() {
		Session session = em.unwrap(Session.class);
		Employee emp1 = session.get(Employee.class, 1);
		Employee emp2 = session.get(Employee.class, 2);
		LOGGER.info("Employee details are - {}", emp1);
		LOGGER.info("Employee details are - {}", emp2);
	}

}
