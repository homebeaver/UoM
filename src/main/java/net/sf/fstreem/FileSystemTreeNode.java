package net.sf.fstreem;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import io.homebeaver.GenericTreeNode;

/**
 * A tree node that is based on the underlying file system.
 */
public abstract class FileSystemTreeNode extends GenericTreeNode<File> {
//    protected final File location; // in super: protected TN object;
    
    protected Vector<FileFilter> filters;
    
    private FileSystemTreeNode(File location) {
        this(location, new Vector<FileFilter>());
    }

    private FileSystemTreeNode(File location, Vector<FileFilter> filters) {
    	super(location);
        this.filters = filters;
    }
    
    /**
     * Returns the {@link File File} instance behind this node.
     *
     * @return The {#link File File} instance behind this node.
     */
    public File getFile() {
        return super.getObject();
    }

    /**
     * Returns the number of child {@link File File} instances under this
     * node.
     *
     * @return The number of child folders and files under this node.
     * @throws NotAFolderException If this node returns <code>true</code> for
     *                             {@link #isFile()}.
     */
    public abstract int getChildCount() throws NotAFolderException;

    /**
     * Returns the node at the specified index.
     * <p>The child {@link File File} instances are returned in order,
     * according to {@link FileComparator FileComparator}.</p>
     *
     * @param index Must be <code>0 &lt;= index &lt;= {@link #getChildCount() getChildCount() - 1}</code>
     * @return A {@link FileSystemTreeNode FileSystemTreeNode} instance
     *         representing the node at the specified index.
     * @throws NotAFolderException If this node returns <code>true</code> for
     *                             {@link #isFile()}.
     */
    public abstract FileSystemTreeNode getChildAt(int index) throws NotAFolderException;

    public abstract boolean isFile();
	public boolean isLeaf() {
		return isFile();
	}

    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Two nodes are equal if their underlying {@link File File} instances are
     * equal.
     *
     * @param obj
     * @return <code>true</code> if the two nodes represent the same
     *         file system location, <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if (null == obj || !(obj instanceof FileSystemTreeNode)) {
            return false;
        }

        FileSystemTreeNode other = (FileSystemTreeNode) obj;
        return getObject().equals(other.getObject());
    }

    public String toString() {
        return object.getName();
    }
    

    /**
     * Public factory for creating
     * {@link FileSystemTreeNode FileSystemTreeNode}s.
     *
     * @param location The file system location to which the node should be
     *                 attached.
     * @return A {@link FileSystemTreeNode FileSystemTreeNode} instance.
     */
    public static FileSystemTreeNode create(File location, Vector<FileFilter> filters) {
    	if (location.isDirectory()) {
            return new DirectoryTreeNode(location, filters);
        } else {
            return new FileTreeNode(location);
        }
    }
    
    public static FileSystemTreeNode create(File location) {
        Vector<FileFilter> filters = new Vector<FileFilter>();

    	if (location.isDirectory()) {
            return new DirectoryTreeNode(location, filters);
        } else {
            return new FileTreeNode(location);
        }
    }
    
    public static final class DirectoryTreeNode extends FileSystemTreeNode {
        //in super: Vector<TreeNode> children;

        private Vector<File> children;
        
        public DirectoryTreeNode(File location, Vector<FileFilter> filters) {
            super(location, filters);
        }

        public boolean isFile() {
            return false;
        }

        public FileSystemTreeNode getChildAt(int index) {
            loadChildren();
            return FileSystemTreeNode.create(children.elementAt(index), filters);
        }

        public int getChildCount() {
            loadChildren();
            if (children != null) {
            	return children.size();	
            } else {
            	return 0;
            }
        }

        private synchronized void loadChildren() {
            if (null != children) return;

            children = new Vector<File>();
            File[] preFilteredChildren = object.listFiles();
            
            if (preFilteredChildren != null) {
            	if (!filters.isEmpty()) {
		            // only accept children into Vector if they pass a filter
		            for (File file : preFilteredChildren) {
		            	for (FileFilter filter : filters) {
		            		if (filter.accept(file)) {
		            			children.add(file);
		            		}
		            	}
		            }
            	} else {
            		children.addAll(Arrays.asList(preFilteredChildren));
            	}
            }
            
            if (children != null) {
            	Collections.sort(children, FileComparator.INSTANCE);
            }
        }
    }

    public static final class FileTreeNode extends FileSystemTreeNode {
        public FileTreeNode(File location) {
            super(location);
        }

        public boolean isFile() {
            return true;
        }

        public FileSystemTreeNode getChildAt(int index) {
            throw new NotAFolderException(object);
        }

        public int getChildCount() {
        	return 0;
        }
    }
}
