package io.homebeaver.uom;

import static org.junit.Assert.*;

import org.junit.Test;

public class UoMTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
	}

    @Test
    public void testExtInt() {
    	UoM ml = UoM.create_ml();
    	String json = ml.externalize();
    	System.out.println("json ml = " + json);
    	UoM uom = UoM.internalize(json);
    	assertEquals(ml.id, uom.id);
    	assertEquals(ml.name, uom.name);
    	assertEquals(ml.description, uom.description);
    	assertEquals(ml.uomSymbol, uom.uomSymbol);
    }


    @Test
    public void testExtIntAmpere() {
    	UoM a = UoM.create_A();
    	String json = a.externalize();
    	System.out.println("json a = " + json);
    	UoM uom = UoM.internalize(json);
    	assertEquals(a.id, uom.id);
    	assertEquals(a.name, uom.name);
    	assertEquals(a.description, uom.description);
    	assertEquals(a.uomSymbol, uom.uomSymbol);
    }
}
