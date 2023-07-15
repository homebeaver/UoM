package io.homebeaver;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.renderer.ComponentProvider;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.IconValues;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;
import org.jdesktop.swingx.renderer.WrappingProvider;
import org.jdesktop.swingx.rollover.RolloverProducer;
import org.jdesktop.swingx.rollover.RolloverRenderer;

import io.homebeaver.uom.UoMTreeNode;
import net.sf.fstreem.FileSystemTreeNode;

/*
 * erweitert JXTree um

 - getCellRenderer() mit IconValue
 - setSelectionMode analog zu JList, wurde in JXTree implementiert
 - getRolloverProducer() mit public boolean isDragging() accessor
 
 */
public class MyXTree extends JXTree {

	public MyXTree(TreeModel newModel) {
		super(newModel);
	}
	
	MyTreeRolloverProducer myTreeRolloverProducer; // boolean RolloverProducer.isDragging ist private,
	// daher public boolean isDragging() accessor
	
	@Override
    protected RolloverProducer createRolloverProducer() {
		myTreeRolloverProducer = new MyTreeRolloverProducer();
        return myTreeRolloverProducer;
    }
    public RolloverProducer getRolloverProducer() {
        return myTreeRolloverProducer;
    }

	@Override
	public TreeCellRenderer getCellRenderer() {
		IconValue iv = (Object value) -> {
            if(value instanceof UoMTreeNode.QuantityTreeNode
            || value instanceof UoMTreeNode.DirectoryTreeNode
            || value instanceof FileSystemTreeNode.DirectoryTreeNode
            || value instanceof FileSystemTreeNode.FileTreeNode
            ) {
            	return UoMTreeNode.SI_ICON.getIcon(value);
            }
            return IconValues.FILE_ICON.getIcon(value);
		};
		StringValue sv = (Object value) -> {
            if(value instanceof UoMTreeNode.QuantityTreeNode
            || value instanceof UoMTreeNode.DirectoryTreeNode
//            || value instanceof FileSystemTreeNode.DirectoryTreeNode
//            || value instanceof FileSystemTreeNode.FileTreeNode
            ) {
//            	System.out.println("sv --------> value:"+value);
            	return StringValues.TO_STRING.getString(value);
            }
            return StringValues.FILE_NAME.getString(value);
		};
		//return new JXTree.DelegatingRenderer(iv, null); gleichwertig mit
		//       new JXTree.DelegatingRenderer((TreeCellRenderer)null, iv, null)
		// dieser ctor instanziert new DefaultXTreeCellRenderer(), mit MyTreeCellRenderer kann ich experimentieren:
//		return new JXTree.DelegatingRenderer(new MyTreeCellRenderer(), iv, sv);
		return new MyDelegatingRenderer(new MyDefaultTreeCellRenderer(), iv, sv);
	}
	
	public class MyDelegatingRenderer extends DefaultTreeRenderer implements TreeCellRenderer, RolloverRenderer {
        private TreeCellRenderer delegate;
		public MyDelegatingRenderer(TreeCellRenderer delegate, IconValue iv, StringValue sv) {
			//super(iv, sv);
			// in super org.jdesktop.swingx.renderer.DefaultTreeRenderer ctor: creates new WrappingProvider(iv, sv)
			//    which is ComponentProvider<?> componentProvider
			//    and calls AbstractRenderer ctor super(componentProvider);
			//                                    this.cellContext = new TreeCellContext();
			// boolean unwrapUserObject
			super(new WrappingProvider(iv, sv, false));
			if(delegate instanceof MyDefaultTreeCellRenderer myCellRenderer) {
				//System.out.println("*** +++ delegate:"+delegate);
				delegate = myCellRenderer;
			} else {
				System.out.println("*** --- delegate:"+delegate);
			}
		}
        public TreeCellRenderer getDelegateRenderer() {
            return delegate;
        }
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,boolean hasFocus) {
            Component result = super.getTreeCellRendererComponent(tree, value, 
            	selected, expanded, leaf, row, hasFocus);
//            System.out.println("*** --- result Component:"+result);
            if ((compoundHighlighter != null) && (row < getRowCount()) && (row >= 0)) {
                result = compoundHighlighter.highlight(result, getComponentAdapter(row));
//                System.out.println("*** --- compoundHighlighter:"+compoundHighlighter);
            } 
            System.out.println("***"+(compoundHighlighter!=null?row:"")+" --- result Component:"+result);
            
            return result;
        }
        
        /**
         * {@inheritDoc}
         */
        /*
         * definiert in interface org.jdesktop.swingx.rollover.RolloverRenderer
         * überschreibt die implementierung aus org.jdesktop.swingx.renderer.AbstractRenderer
         *  dort wird ComponentProvider<?> componentController und nicht delegate genommen XXX ?
         *  TODO kann ich auf delegate verzichten und stattdessen componentController nutzen
         *  TODO das ist nie true
         */
        @Override
        public boolean isEnabled() {
//        	boolean ret = (delegate instanceof RolloverRenderer) && ((RolloverRenderer) delegate).isEnabled();
        	boolean ret = (delegate instanceof MyDefaultTreeCellRenderer);
        	System.out.println("isEnabled() liefert "+ret + " "+delegate);
//        	((MyDefaultTreeCellRenderer)delegate).selected
            return ret;
        }
            
        /**
         * {@inheritDoc}
         */
        /*
         * definiert in interface org.jdesktop.swingx.rollover.RolloverRenderer
         * überschreibt die implementierung aus org.jdesktop.swingx.renderer.AbstractRenderer,
         *  dort wird ComponentProvider<?> componentController und nicht delegate genommen XXX ?
         */
        @Override
        public void doClick() {
            if (isEnabled()) {
            	System.out.println("isEnabled() ist TRUE in this:"+this);
                ((RolloverRenderer) delegate).doClick();
            }
        }
	}

	public void setCellEditor(TreeCellEditor cellEditor) {
		// in super.init:
		//             setCellEditor(new DefaultXTreeCellEditor(this, (DefaultTreeCellRenderer) getWrappedCellRenderer()));
		// in super ist diese Methode nicht definiert
		/* in super.super JTree
        TreeCellEditor        oldEditor = this.cellEditor;

        this.cellEditor = cellEditor;
        firePropertyChange(CELL_EDITOR_PROPERTY, oldEditor, cellEditor);
        invalidate();
		 */
		System.out.println("--------> cellEditor:"+cellEditor);
		super.setCellEditor(cellEditor);
	}
	
    /*
     * in javax.swing.tree.DefaultTreeSelectionModel gibt es eine private Methode
     * zum Validieren: private static int validateSelectionMode(int mode)
     * 
     * Bei "falschem" selectionMode wird DISCONTIGUOUS_TREE_SELECTION gesetzt! kein Exception!
     * 
     * setSelectionMode nicht mehr notwendig, da in JXTree implementiert
     */
//    @BeanProperty(bound = true, enumerationValues = {
//            "TreeSelectionModel.SINGLE_TREE_SELECTION",
//            "TreeSelectionModel.CONTIGUOUS_TREE_SELECTION",
//            "TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION"}, description = "The selection mode.")
//	public void setSelectionMode(int selectionMode) {
//		getSelectionModel().setSelectionMode(selectionMode);
//	}
//    public int getSelectionMode() {
//        return getSelectionModel().getSelectionMode();
//    }

}
