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
	
//	@Deprecated // this ctor is not used
//    public GenericTreeModel(File root, Vector<FileFilter> filters) {
//        this.root = FileSystemTreeNode.create(root, filters);
//    }
    public GenericTreeModel(File root) {
        Vector<FileFilter> filters = new Vector<FileFilter>();
        this.root = FileSystemTreeNode.create(root, filters);
    }
    public GenericTreeModel(UoMTreeNode root) {
        this.root = root;
    }
    
    /*
     * root ist damit vom Typ GenericTreeNode.ObjectTreeLeaf
     */
    public GenericTreeModel(Object root) {
        this.root = GenericTreeNode.create(root);
    }
    /*
     * root ist damit vom Typ GenericTreeNode.ObjectTreeNode
     * GenericTreeModel tml = new GenericTreeModel(object);
     * GenericTreeModel tmn = new GenericTreeModel(object, null);
     * tml unterscheidet sich von tmn dadurch, dass man in tmn child-elemente hinzufügen kann
     */
    public GenericTreeModel(Object root, Vector<TreeNode> ch) {
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
