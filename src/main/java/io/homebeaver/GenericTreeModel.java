package io.homebeaver;

import java.io.File;
import java.io.FileFilter;
import java.util.Enumeration;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import io.homebeaver.uom.UoMTreeNode;
import net.sf.fstreem.FileSystemTreeNode;

// public class DefaultTreeModel implements Serializable, TreeModel {
public class GenericTreeModel implements TreeModel {

	private static final Logger LOG = Logger.getLogger(GenericTreeModel.class.getName());
	
	protected GenericTreeNode<?> root;
	
	@Deprecated // this ctor is not used
    public GenericTreeModel(File root, Vector<FileFilter> filters) {
        this.root = FileSystemTreeNode.create(root, filters);
    }
    public GenericTreeModel(File root) {
        Vector<FileFilter> filters = new Vector<FileFilter>();
        this.root = FileSystemTreeNode.create(root, filters);
    }
    public GenericTreeModel(UoMTreeNode root) {
        this.root = root;
    }
    public GenericTreeModel(Object root) {
    	// root ist damit vom Typ GenericTreeNode.ObjectTreeLeaf
        this.root = GenericTreeNode.create(root);
    }
    public GenericTreeModel(Object root, Vector<TreeNode> ch) {
    	// root ist damit vom Typ GenericTreeNode.ObjectTreeNode
        this.root = GenericTreeNode.create(root, ch);
    }

	@Override
	public Object getRoot() {
		return root;
	}

	@Override
	public Object getChild(Object parent, int index) {
        return ((TreeNode)parent).getChildAt(index);
	}

	@Override
	public int getChildCount(Object parent) {
		if (parent instanceof FileSystemTreeNode fstn) {
			return fstn.getChildCount();
		}
		if (parent instanceof UoMTreeNode uomtn) {
			return uomtn.getChildCount();
		}
		LOG.warning("parent is instanceof "+parent.getClass());
		/*
java.lang.IllegalArgumentException: Expected a net.sf.fstreem.FileSystemTreeNode instance
, received a io.homebeaver.GenericTreeNode$ObjectTreeNode
	at io.homebeaver.GenericTreeModel.throwArgException(GenericTreeModel.java:200)
	at io.homebeaver.GenericTreeModel.getChildCount(GenericTreeModel.java:62)
	at java.desktop/javax.swing.tree.VariableHeightLayoutCache$TreeStateNode.expand(VariableHeightLayoutCache.java:1457)
	at java.desktop/javax.swing.tree.VariableHeightLayoutCache$TreeStateNode.expand(VariableHeightLayoutCache.java:1272)
	at java.desktop/javax.swing.tree.VariableHeightLayoutCache.rebuild(VariableHeightLayoutCache.java:728)
	at java.desktop/javax.swing.tree.VariableHeightLayoutCache.setModel(VariableHeightLayoutCache.java:111)
	at java.desktop/javax.swing.plaf.basic.BasicTreeUI.configureLayoutCache(BasicTreeUI.java:2131)
	at java.desktop/javax.swing.plaf.basic.BasicTreeUI.completeUIInstall(BasicTreeUI.java:840)
	at java.desktop/javax.swing.plaf.basic.BasicTreeUI.installUI(BasicTreeUI.java:800)
	at java.desktop/javax.swing.plaf.metal.MetalTreeUI.installUI(MetalTreeUI.java:121)
	at java.desktop/javax.swing.JComponent.setUI(JComponent.java:685)
	at java.desktop/javax.swing.JTree.setUI(JTree.java:706)
	at java.desktop/javax.swing.JTree.updateUI(JTree.java:727)
	at org.jdesktop.swingx.JXTree.updateUI(JXTree.java:741)
	at io.homebeaver.MyXTreeTest.test2(MyXTreeTest.java:75)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:78)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:567)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:93)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:40)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:529)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:756)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:452)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:210)
		 */
    	// @see https://stackoverflow.com/questions/2699788/java-is-there-a-subclassof-like-instanceof
    	if(parent.getClass()!=GenericTreeNode.class && parent instanceof GenericTreeNode gtn) {
    		LOG.info("parent is instanceof "+parent.getClass() + " with ChildCount="+gtn.getChildCount());
    		return gtn.getChildCount();
    	}

		throwArgException(GenericTreeNode.class, parent);
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof FileSystemTreeNode fstn) {
			return fstn.isLeaf();
		}
		if (node instanceof GenericTreeNode<?> gtn) {
			return gtn.isLeaf();
		}
		throwArgException(FileSystemTreeNode.class, node);
		return false;
	}

	/**
	 * {@inheritDoc} <p>
	 * 
	 * called in BasicTreeUI#completeEditing , TreeTransferHandler#cleanup
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
    	LOG.info("path:"+path + ", root:"+root + " newValue:"+newValue + (newValue==null?"":", "+newValue.getClass()));
    	if(path==null) {
    		LOG.warning("path==null");
    		return;
    	}
    	/*
    	 * wann ist path root? beides ist möglich:
    	 * - path.getParentPath()==null
    	 * - path.getLastPathComponent()==root
    	 */
    	if(path.getParentPath()==null) {
    		if(newValue instanceof UoMTreeNode && getRoot() instanceof UoMTreeNode) {
    			// called in TreeTransferHandler#importData to move a UoMTreeNode node to root
    			System.out.println("newValue instanceof UoMTreeNode && getRoot() instanceof UoMTreeNode");
    			UoMTreeNode uomtn = (UoMTreeNode)getRoot();
    			int cc = uomtn.getChildCount();
    			TreeNode tn = uomtn.getChildAt(cc-1);
    			//path = path.pathByAddingChild(tn);
    			GenericTreeNode<?> oldValue = (GenericTreeNode<?>)path.getLastPathComponent();
    			// TODO setParent für alle newValue childs !!! rekursiv
    			UoMTreeNode uomNewValue = (UoMTreeNode)newValue;
    			for (Enumeration<? extends TreeNode> e = uomNewValue.children(); e.hasMoreElements();) {
    				TreeNode next = e.nextElement();
    				System.out.println("setParent for "+ next.getClass());
    				if(next instanceof GenericTreeNode.ObjectTreeNode uomtnext) {
    					System.out.println("setParent for "+ uomtnext.getParent());
    					uomtnext.setParent((GenericTreeNode<?>)newValue);
    				}
    			}
    			
    			oldValue.insert(uomNewValue, oldValue.getChildCount());
    			return;
    		}
    		if(newValue instanceof GenericTreeNode<?> newRoot) {
    			LOG.info("called in SimpleTreeView#selectTree to change the selected Tree."
    				+ " path == root:"+root + " newRoot:"+newRoot);
        		root = newRoot;
    		} else {
    			LOG.warning("Do not change the root object:"+root); // warum nicht?
    		}
    		return;
    	} else {
    		if(newValue==null) {
    			// remove node
    			GenericTreeNode<?> oldValue = (GenericTreeNode<?>)path.getLastPathComponent();
    			System.out.println("remove node "+oldValue +"/type:"+oldValue.getClass() + " because newValue is null");
    			oldValue.removeFromParent(); // ist definiert in interface MutableTreeNode extends TreeNode
    		} else {
    			GenericTreeNode<?> oldValue = (GenericTreeNode<?>)path.getLastPathComponent();
    			// insert leaf
    			System.out.println("insert at node "+oldValue + "/type="+oldValue.getClass()
    					+ " newValue:"+newValue + " instanceof "+newValue.getClass());
    			if(newValue instanceof UoMTreeNode.QuantityTreeNode qnode) {
    				if(oldValue instanceof UoMTreeNode.QuantityTreeNode target) {
    					int i = getIndexOfChild(oldValue.getParent(), oldValue);
    					GenericTreeNode<?> p = (GenericTreeNode<?>) oldValue.getParent();
    					p.insert(qnode, i+1);
    				} else {
    					oldValue.insert(qnode, oldValue.getChildCount());
    				}
    			}
    			if(newValue instanceof UoMTreeNode.DirectoryTreeNode dnode) {
    				if(oldValue.isLeaf()) {
    					LOG.warning("darf das sein ?????????? 143");
    				} else {
    					oldValue.insert(dnode, oldValue.getChildCount());
    				}
    			} else if(newValue instanceof GenericTreeNode.ObjectTreeLeaf leaf) {
    				if(oldValue.isLeaf()) {
    					int i = getIndexOfChild(oldValue.getParent(), oldValue);
    					GenericTreeNode<?> p = (GenericTreeNode<?>) oldValue.getParent();
    					p.insert(leaf, i+1);
    				} else {
    					// BUG insert ==> muss public sein: void insert(GenericTreeNode<?> newChild, int childIndex) 
    					System.out.println("insert leaf "+leaf+" in node "+oldValue);
    					oldValue.insert(leaf, oldValue.getChildCount());
    				}
    			}
    		}
    	}
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
        if (null == parent || null == child) {
            return -1;
        }

//        checkNodeType(parent);
//        checkNodeType(child);

        GenericTreeNode<?> home = (GenericTreeNode<?>) parent;
        GenericTreeNode<?> target = (GenericTreeNode<?>) child;
        for (int i = 0; i < home.getChildCount(); i++) {
            if (home.getChildAt(i).equals(target)) {
                return i;
            }
        }

        return -1;
	}

	@Override // Change Event
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override // Change Event
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub		
	}

    private void throwArgException(Class<?> expectedClass, Object node) {
        throw new IllegalArgumentException("Expected a " + expectedClass.getName() +
        	" instance, received a " + node.getClass().getName());
    }

}
