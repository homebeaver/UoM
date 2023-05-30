package io.homebeaver;

import java.io.File;
import java.io.FileFilter;
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
        this.root = GenericTreeNode.create(root);
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
		throwArgException(FileSystemTreeNode.class, parent);
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
	 * called in BasicTreeUI#completeEditing
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
    	LOG.info("path:"+path + ", root:"+root + " newValue:"+newValue + (newValue==null?"":", "+newValue.getClass()));
    	if(path.getLastPathComponent()==root) {
    		if(newValue instanceof GenericTreeNode<?> newRoot) {
    			// called in SimpleTreeView#selectTree to change the selected Tree
        		System.out.println("path == root:"+root + " newRoot:"+newRoot);
        		root = newRoot;
    		} else {
    			LOG.warning("Do not change the root object:"+root); // warum nicht?
    		}
    		return;
    	} else {
    		if(newValue==null) {
    			// remove node
    			GenericTreeNode<?> oldValue = (GenericTreeNode<?>)path.getLastPathComponent();
    			System.out.println("remove node "+oldValue + " because newValue is null");
    			oldValue.removeFromParent(); // ist definiert in interface MutableTreeNode extends TreeNode
    			// aber noch nicht in GenericTreeNode<TN> TODO - fertig
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

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

    private void throwArgException(Class<?> expectedClass, Object node) {
        throw new IllegalArgumentException("Expected a " + expectedClass.getName() +
        	" instance, received a " + node.getClass().getName());
    }

}
