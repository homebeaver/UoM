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
public class ObjectTreeNode extends GenericTreeNode<Object>
 */
public abstract class GenericTreeNode<TN> implements TreeNode {

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
    protected GenericTreeNode<TN> parent;
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

    private static class ObjectTreeNode extends GenericTreeNode<Object> {
        public ObjectTreeNode(Object o, Vector<TreeNode> ch) {
            super(o, ch);
        }

		@Override
		public boolean isLeaf() {
			return children.isEmpty();
		}
    }

    private static class ObjectTreeLeaf extends GenericTreeNode<Object> {
        public ObjectTreeLeaf(Object o) {
            super(o);
        }
        public boolean isLeaf() {
            return true;
        }
    }
}
