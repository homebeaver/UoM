package io.homebeaver.uom;

import java.util.Vector;

import javax.swing.tree.TreeNode;

import io.homebeaver.GenericTreeNode;

/**
 * A tree node that is based on UoM.
 */
public abstract class UoMTreeNode extends GenericTreeNode<UoM> {

	public static UoMTreeNode create(UoM uom, Vector<TreeNode> childs) {
		if (uom.isQuantity()) {
            return new FileTreeNode(uom);
        } else {
            return new DirectoryTreeNode(uom, childs);
        }
    }
	private static final class DirectoryTreeNode extends UoMTreeNode {
        public DirectoryTreeNode(UoM uom, Vector<TreeNode> childs) {
            super(uom, childs==null?new Vector<TreeNode>():childs);
        }

//		@Override
//		public boolean isQuantity() {
//        	// ==getUoM().isQuantity();
//			return false;
//		}

	}
    // rename to ADUoM oder so
    private static final class FileTreeNode extends UoMTreeNode {
        public FileTreeNode(UoM uom) {
            super(uom);
        }

//        public boolean isQuantity() {
//        	// ==getUoM().isQuantity();
//            return true;
//        }
    }

    private UoMTreeNode(UoM uom) {
        super(uom);
    }
    private UoMTreeNode(UoM uom, Vector<TreeNode> ch) {
        super(uom, ch);
    }

    public UoM getUoM() {
        return super.getObject();
    }

    public boolean isQuantity() {
		return getUoM().isQuantity();
    }
	public boolean isLeaf() {
		return isQuantity() || children.isEmpty();
	}
	
    public void setParent(UoMTreeNode newParent) {
        parent = newParent;
    }
	public void add(UoMTreeNode newChild) {
        if(newChild != null && newChild.getParent() == this) {
        	// newChild ist bereits child von this
            //insert(newChild, getChildCount() - 1);
        } else {
//            insert(newChild, getChildCount());
        	newChild.setParent(this);
        	children.add(newChild);
        }		
	}
/*
    protected Vector<Object> uoms;

    public static UoMTreeNode create(UoM uom, Vector<Object> filters) {
    	if (uom.isQuantity()) {
            return new FileTreeNode(uom);
        } else {
            return new DirectoryTreeNode(uom, filters);
        }
    }
    public static UoMTreeNode create(UoM uom) {
        Vector<Object> filters = new Vector<Object>();

    	if (uom.isQuantity()) {
            return new FileTreeNode(uom);
        } else {
            return new DirectoryTreeNode(uom, filters);
        }
    }
    
//    private UoMTreeNode(UoM uom) {
//        this(uom, new Vector<Object>());
//    }
//    private UoMTreeNode(UoM uom, Vector<Object> filters) {
//        this.uom = uom;
//        this.uoms = filters;
//    }

    public abstract int getChildCount(); // throws NotAFolderException;
    public abstract UoMTreeNode getChildAt(int index);

    // rename to ADUoM oder so
    private static final class FileTreeNode extends UoMTreeNode {
        public FileTreeNode(UoM uom) {
            super(uom);
        }

        public boolean isQuantity() {
        	// ==getUoM().isQuantity();
            return true;
        }

        public UoMTreeNode getChildAt(int index) {
//            throw new NotAFolderException(location);
        	return null;
        }

        public int getChildCount() {
        	return 0;
        }
    }

    private static final class DirectoryTreeNode extends UoMTreeNode {
        //private File[] children;

        private Vector<UoM> children;
        
        public DirectoryTreeNode(UoM uom, Vector<Object> filters) {
            super(uom, filters);
        }

        public boolean isQuantity() {
        	// ==getUoM().isQuantity();
            return false;
        }
		@Override
		public int getChildCount() {
            loadChildren();
            if (children != null) {
            	return children.size();	
            } else {
            	return 0;
            }
		}

		@Override
		public UoMTreeNode getChildAt(int index) {
            loadChildren();
            return UoMTreeNode.create(children.elementAt(index), uoms);
		}

        private synchronized void loadChildren() {
        	// TODO
        }
    }
 */
}
