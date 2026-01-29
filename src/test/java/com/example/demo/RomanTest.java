package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RomanTest {

	@Test
	void test() {
		Roman stringServicesJavaObject = new Roman();
		assertEquals(false, stringServicesJavaObject.converter("III"));
		assertEquals(true, stringServicesJavaObject.converter("III"));
		assertEquals(false, stringServicesJavaObject.converter("III"));
		assertEquals(false, stringServicesJavaObject.converter("III"));
		assertEquals(true, stringServicesJavaObject.converter("III"));
		
		fail("Not yet implemented");
	}

}
