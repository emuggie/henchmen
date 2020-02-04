package org.emuggie.henchmen.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class TypedMapTest {

	@Test
	public void init() {
		TypedMap map = new TypedMap();
		assertTrue("getOrElse:String", "Test".equals(map.getOrElse("", "Test")));
		assertTrue("getOrElse:Integer", 10 == map.getOrElse("", 10));
	}
	
	@Test
	public void refTest() {
		Map<Object,Object> refMap = new HashMap<Object, Object>();
		TypedMap map = new TypedMap(refMap);
		map.getOrSet("TEST", "TEST");
		assertTrue("refTest", refMap.get("TEST") == map.get("TEST"));
	}
}
