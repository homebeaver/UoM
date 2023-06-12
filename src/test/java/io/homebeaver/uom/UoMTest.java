package io.homebeaver.uom;

import static org.junit.Assert.*;

import java.util.Vector;

import javax.swing.tree.TreeNode;

import org.junit.Test;

import io.homebeaver.GenericTreeNode;

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

    @Test
    public void testExtIntAmpereTN() {
    	UoMTreeNode aTN = UoMTreeNode.create(UoM.create_A(), null);
    	UoM a = aTN.getObject();
    	String json = aTN.externalize();
    	System.out.println("json aTN = " + json);
    	UoMTreeNode tn = UoMTreeNode.internalize(json);
    	UoM uom = tn.getObject();
    	assertEquals(a.id, uom.id);
    	assertEquals(a.name, uom.name);
    	assertEquals(a.description, uom.description);
    	assertEquals(a.uomSymbol, uom.uomSymbol);
    }
    
    @Test
    public void testExtIntVolumen() {
    	UoMTreeNode mlTN = UoMTreeNode.create(UoM.create_ml(), null);
    	UoMTreeNode lTN = UoMTreeNode.create(UoM.create_L(), null);
    	Vector<TreeNode> childs = new Vector<TreeNode>();
    	childs.add(mlTN);
    	childs.add(lTN);
		UoMTreeNode volumen = UoMTreeNode.create(new UoM("Volumen", "https://de.wikipedia.org/wiki/Volumen"), childs);
    	//create(UoM uom, Vector<TreeNode> childs)
    	String json = volumen.externalize();
    	System.out.println("json volumen = " + json);
    	UoMTreeNode tn = UoMTreeNode.internalize(json);
    	assertEquals("Volumen", tn.getObject().name);
    	assertEquals(2, tn.getChildCount());
    	System.out.println("tn.getChildAt(0) type = " + tn.getChildAt(0).getClass());
    	GenericTreeNode tn0 = (GenericTreeNode)tn.getChildAt(0);
    	System.out.println("tn.getChildAt(0) getObject type = " + tn0.getObject().getClass());
    	if(tn0.getObject() instanceof UoMTreeNode uomTN) {
    		assertEquals(mlTN.getObject().id, uomTN.getObject().id);
    		// ??? XXX wieso zwei mal getObject()
    	} else {
    		fail("GenericTreeNode getChildAt(0)..getObject() is not UoMTreeNode!!!");
    	}
    }
    
    @Test
    public void testExtIntVolumen2() {
    	UoMTreeNode mlTN = UoMTreeNode.create(new UoM("test", "test"), null);
    	UoMTreeNode lTN = UoMTreeNode.create(UoM.create_L(), null);
    	Vector<TreeNode> childs = new Vector<TreeNode>();
    	childs.add(mlTN);
    	childs.add(lTN);
		UoMTreeNode volumen = UoMTreeNode.create(new UoM("Volumen", "test 2 childs: 1x NON quantity, 1xLiter"), childs);
    	//create(UoM uom, Vector<TreeNode> childs)
    	String json = volumen.externalize();
    	System.out.println("json volumen = " + json);
    	UoMTreeNode tn = UoMTreeNode.internalize(json);
    	assertEquals("Volumen", tn.getObject().name);
    	assertEquals(2, tn.getChildCount());
    	System.out.println("tn.getChildAt(0) type = " + tn.getChildAt(0).getClass());
    	GenericTreeNode tn0 = (GenericTreeNode)tn.getChildAt(0);
    	System.out.println("tn.getChildAt(0) getObject type = " + tn0.getObject().getClass());
    	if(tn0.getObject() instanceof UoMTreeNode uomTN) {
    		System.out.println("test:"+mlTN.getObject());
    		assertEquals(mlTN.getObject().id, uomTN.getObject().id);
    		assertEquals(mlTN.getObject().name, uomTN.getObject().name);
    	} else {
    		fail("GenericTreeNode getChildAt(0)..getObject() is not UoMTreeNode!!!");
    	}
    }

}
