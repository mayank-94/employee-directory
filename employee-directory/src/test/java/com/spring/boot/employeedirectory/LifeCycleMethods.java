package com.spring.boot.employeedirectory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LifeCycleMethods {
	
	@BeforeAll
	static void initAll() {
		System.out.println("Before All Test Cases");
	}
	
	@BeforeEach
	void init() {
		System.out.println("Before Each Test");
	}
	
	@Test
	void test1() {
		assertFalse(10 < 0);
	}
	
	@Test
	void test2() {
		assertTrue(5 > 0);
	}
	
	@AfterEach
	void after() {
		System.out.println("After Each");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("After All Test Cases");
	}
	
}
