package org.emuggie.henchmen.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class TypeCastTest {
	@Test
	public void init() {
		assertTrue("",true);
	}
	
	@Test
	public void numToStr() {
		assertTrue("","12".equals(TypeCast.cast(new Integer(12), String.class)));
	}
	
	@Test
	public void strToNum() {
		assertTrue("",12 == TypeCast.cast("12", Integer.class));
	}
}
