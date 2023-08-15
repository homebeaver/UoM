package io.homebeaver;

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import io.homebeaver.GenericTreeNode;
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
    
    @Test
    public void testObjectTreeLeaf() {
    	TreeModel treeModel = new GenericTreeModel("test");
    	// root ist damit vom Typ ObjectTreeLeaf
    	MyXTree tree = new MyXTree(treeModel);
    	Object root = tree.getModel().getRoot();
    	System.out.println("root.Object:"+root.getClass()+"/"+root);
    	assertEquals(GenericTreeNode.ObjectTreeLeaf.class, root.getClass());
    	// TODO insert muss zu exception führen
    }

    @Test
    public void test2() {
    	TreeModel treeModel = new GenericTreeModel("test", null);
    	// root ist damit vom Typ ObjectTreeNode
    	MyXTree tree = new MyXTree(treeModel);
    	Object root = tree.getModel().getRoot();
    	System.out.println("root.Object type/value:"+root.getClass()+"/"+root);
    	assertEquals(GenericTreeNode.ObjectTreeNode.class, root.getClass());
    	
//    	tree.expandedStack; // field JTree.expandedStack is not visible
    	System.out.println("getToggleClickCount="+tree.getToggleClickCount());
    	System.out.println("tree:"+tree);
//    	tree.expandedState; // field JTree.expandedState is not visible
    	// zu jedem Knoten TreePath path gibt es in JTree private transient expandedState, das man aber abfragen kann
    	tree.expandAll();
    	tree.addSelectionRow(0);
    	TreePath tpToRoot = tree.getSelectionPath();
    	System.out.println("TreePathToRoot:"+tpToRoot 
    			+ " isCollapsed="+tree.isCollapsed(tpToRoot)
    			+ " isExpanded="+tree.isExpanded(tpToRoot)
    			+ " hasBeenExpanded="+tree.hasBeenExpanded(tpToRoot));
    	
    	int rows = tree.getRowCount();    	
    	for(int i=0;i<rows;i++) {
    		System.out.println("row i="+i+"/"+rows+" isExpanded="+tree.isExpanded(i));		
    	}
    	
    	GenericTreeNode.ObjectTreeNode child = new GenericTreeNode.ObjectTreeNode("child", null);
    	assertTrue(child.getAllowsChildren());
    	GenericTreeNode.ObjectTreeLeaf leafChild = new GenericTreeNode.ObjectTreeLeaf("leafChild");
    	assertFalse(leafChild.getAllowsChildren());
    	// @see https://stackoverflow.com/questions/2699788/java-is-there-a-subclassof-like-instanceof
    	if(root.getClass()!=GenericTreeNode.class && root instanceof GenericTreeNode<?> gtn) {
        	System.out.println("root.Object:"+gtn.getObject().getClass()+"/"+gtn.getObject());
        	assertTrue(gtn.getAllowsChildren());
        	gtn.insert(child, 0);
        	gtn.insert(leafChild, 1);
    	}
    	tree.expandAll(); // diese Wiederholung ist notwendig!!! damit expandedState gesetzt wird
    	// TODO sollte JXTree.expandAll setExpandedState setzen? !!!
    	rows = tree.getRowCount();    	
    	for(int i=0;i<rows;i++) {
    		TreePath tp = tree.getPathForRow(i); // hasBeenExpanded(i) gibt es nicht
    		System.out.println("row i="+i+"/"+rows+" isExpanded="+tree.isExpanded(i)+ " hasBeenExpanded="+tree.hasBeenExpanded(tp));		
    	}
 	
    }
}
