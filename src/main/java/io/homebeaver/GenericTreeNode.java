package io.homebeaver;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.tree.TreeNode;

/**
 * A tree node that is based on TN.
 *
 * @param <TN> a tree node type
 */
/* Simpler then DefaultMutableTreeNode:

public class javax.swing.tree.DefaultMutableTreeNode implements Cloneable, MutableTreeNode, Serializable
public interface javax.swing.tree.MutableTreeNode extends TreeNode

implementation: 
ObjectTreeNode extends GenericTreeNode<Object> -- sehr allgemein
UoMTreeNode extends GenericTreeNode<UoM>
FileSystemTreeNode extends GenericTreeNode<File>
 */
public abstract class GenericTreeNode<TN> implements GenericMutableTreeNode<TN> {

    public static GenericTreeNode<?> create(Object o, Vector<TreeNode> ch) {
    	return new ObjectTreeNode(o, ch);
    }
    public static GenericTreeNode<?> create(Object o) {
    	return new ObjectTreeLeaf(o);
    }

    /**
     * An enumeration that is always empty. 
     * This is used when an enumeration of a leaf node's children is requested.
     */
    public static final Enumeration<GenericTreeNode<?>> EMPTY_ENUMERATION = Collections.emptyEnumeration();
    /** this node's parent, or null if this node has no parent */
    protected GenericMutableTreeNode<?> parent;
    protected TN object;
    protected Vector<TreeNode> children;
    
    protected GenericTreeNode(TN o) {
        this(o, new Vector<TreeNode>());
    }

    protected GenericTreeNode(TN o, Vector<TreeNode> ch) {
        this.object = o;
        this.children = ch;
    }
    
    /**
     * Returns the {@link TN TN} instance behind this node.
     */
    public TN getObject() {
        return object;
    }

    public String externalize() {
//    	throw new Exception("not implemented"); // to be implemented by subclass!!!
    	return null;
    }
    
    /* interface TreeNode implementation

    TreeNode getChildAt(int childIndex);
    int getChildCount();
    TreeNode getParent();
    int getIndex(TreeNode node);
    boolean getAllowsChildren();
    boolean isLeaf();
    Enumeration<? extends TreeNode> children();

     */
	@Override
    public TreeNode getChildAt(int index) {
    	return children.elementAt(index);
    }
	@Override
    public int getChildCount() {
    	return children.size();
    }
	@Override
	public TreeNode getParent() {
		return parent;
	}
	@Override
	public int getIndex(TreeNode aChild) {
        if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (!isNodeChild(aChild)) {
            return -1;
        }
        return children.indexOf(aChild);
	}
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	@Override
    public abstract boolean isLeaf();
	@Override
	public Enumeration<? extends TreeNode> children() {
        if (children == null) {
            return EMPTY_ENUMERATION;
        } else {
            return children.elements();
        }
	}
	
    private boolean isNodeChild(TreeNode aNode) {
        boolean retval;

        if (aNode == null) {
            retval = false;
        } else {
            if (getChildCount() == 0) {
                retval = false;
            } else {
                retval = (aNode.getParent() == this);
            }
        }

        return retval;
    }

    public int hashCode() {
        return object.hashCode();
    }

    /**
     * Two nodes are equal if their underlying {@link TN TN} instances are equal.
     *
     * @param obj
     * @return <code>true</code> if the two nodes represent the same object, 
     * <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
    	if(obj instanceof GenericTreeNode<?> gtn) {
    		return gtn.getObject().equals(this.getObject());
    	}
    	return false;
    }

    public String toString() {
        return object.toString();
    }

    // interface GenericMutableTreeNode implementation :

	@Override
	public void insert(GenericMutableTreeNode<?> newChild, int childIndex) {
		if (newChild == null) {
			throw new IllegalArgumentException("new child is null");
		} else if (isNodeAncestor(newChild)) {
			throw new IllegalArgumentException("new child is an ancestor");
		}

		TreeNode oldParent = newChild.getParent();

		if (oldParent != null && oldParent instanceof GenericMutableTreeNode<?> op) {
			op.remove(newChild);
		}
		newChild.setParent(this);
		if (children == null) {
			children = new Vector<>();
		}
		children.insertElementAt(newChild, childIndex);
	}

    // copied from DefaultMutableTreeNode
    private boolean isNodeAncestor(TreeNode anotherNode) {
        if (anotherNode == null) {
            return false;
        }

        TreeNode ancestor = this;

        do {
            if (ancestor == anotherNode) {
                return true;
            }
        } while((ancestor = ancestor.getParent()) != null);

        return false;
    }
    
	@Override
    public void remove(int childIndex) {
    	System.out.println("remove child at "+childIndex);
    	GenericTreeNode<?> child = (GenericTreeNode<?>)getChildAt(childIndex);
        children.removeElementAt(childIndex);
        child.setParent(null);
    }
	@Override
	public void remove(GenericMutableTreeNode<?> aChild) {
        if (aChild == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (!isNodeChild(aChild)) {
            throw new IllegalArgumentException("argument is not a child");
        }
        System.out.println("remove "+aChild);
        remove(getIndex(aChild)); // linear search
    }
	@Override
    public void removeFromParent() {
    	GenericTreeNode<?> parent = (GenericTreeNode<?>)getParent();
        if (parent != null) {
            parent.remove(this);
        }
    }
	@Override
	public void setUserObject(TN object) {
		try {
			this.object = object;
//			return;
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public void setParent(GenericMutableTreeNode<?> newParent) {
    	parent = newParent;
    }
    
	public static class ObjectTreeNode extends GenericTreeNode<Object> {
        public ObjectTreeNode(Object o, Vector<TreeNode> ch) {
            super(o, ch);
        }
		@Override
		public boolean isLeaf() {
			return children==null || children.isEmpty();
		}
		@Override
		public boolean getAllowsChildren() {
			return true;
		}
    }

    public static class ObjectTreeLeaf extends GenericTreeNode<Object> {
        public ObjectTreeLeaf(Object o) {
            super(o);
        }
		@Override
        public boolean isLeaf() {
            return true;
        }
		@Override
		public boolean getAllowsChildren() {
			return false;
		}
    }
}
