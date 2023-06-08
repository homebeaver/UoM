package io.homebeaver;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class MyXTreeTest extends TestCase {

    @Test
    public void testSelectionMode() {
    	TreeModel treeModel = new GenericTreeModel("test");
    	MyXTree tree = new MyXTree(treeModel);
    	System.out.println("getSelectionModel:"+tree.getSelectionModel());
    	assertEquals(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION, tree.getSelectionMode());
    	tree.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    	assertEquals(TreeSelectionModel.SINGLE_TREE_SELECTION, tree.getSelectionMode());
    	tree.setSelectionMode(99); // invalid ==> DISCONTIGUOUS_TREE_SELECTION ist set
    	assertEquals(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION, tree.getSelectionMode());
    }
    
}
