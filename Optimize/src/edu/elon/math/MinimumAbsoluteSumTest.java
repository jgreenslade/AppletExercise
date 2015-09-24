/**
 * Copyright (c) 2015, Jacopo Greenslade
 */
package edu.elon.math;

/**
 * Tests the MinimumAbsoluteSum Class
 * @author jgreenslade
 *
 */
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MinimumAbsoluteSumTest {

	private MinimumAbsoluteSum MAS;
	
	@Before
	public void setUp() throws Exception {
		MAS = new MinimumAbsoluteSum();
	}

	@After
	public void tearDown() throws Exception {
		MAS = null;
	}

	@Test
	public void testEvaluate() {
		System.out.println(MAS.evaluate());
		assertEquals(MAS.evaluate(), 1000.0, 0.1);
	}

}
