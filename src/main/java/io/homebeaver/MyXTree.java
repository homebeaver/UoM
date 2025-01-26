package io.homebeaver;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTree;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.IconValues;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;
import org.jdesktop.swingx.renderer.WrappingIconPanel;
import org.jdesktop.swingx.renderer.WrappingProvider;
import org.jdesktop.swingx.rollover.RolloverProducer;
import org.jdesktop.swingx.rollover.RolloverRenderer;

import io.homebeaver.uom.UoMTreeNode;
import net.sf.fstreem.FileSystemTreeNode;

/*
 * erweitert JXTree um

 - ctor MyXTree(TreeModel newModel, NodeElementContainer nodeElementsContainer)
 - getCellRenderer() mit IconValue
 - setSelectionMode analog zu JList, wurde in JXTree implementiert
 - getRolloverProducer() mit public boolean isDragging() accessor
 - setSelectionPath(TreePath path)
 
 */
public class MyXTree extends JXTree {

	public MyXTree(TreeModel newModel) {
		this(newModel, null);
	}
	public MyXTree(TreeModel newModel, NodeElementContainer nodeElementsContainer) {
		super(newModel);
		this.nodeElementsContainer = nodeElementsContainer;
	}
	
	NodeElementContainer nodeElementsContainer;
	
	@Override
    public void setSelectionPath(TreePath path) {
    	Object lpc = path.getLastPathComponent();
    	System.out.println("getLastPathComponent = " + lpc + " / " + lpc.getClass());
    	super.setSelectionPath(path);
    	if(lpc instanceof UoMTreeNode uom) {
        	nodeElementsContainer.add(uom);
    	} else if(lpc instanceof FileSystemTreeNode.DirectoryTreeNode dtn) {
        	System.out.println(dtn.getFile().getPath() + " : dir with " + dtn.getFile().listFiles().length + " files/dirs");
    	} else if(lpc instanceof FileSystemTreeNode.FileTreeNode ftn) {
        	System.out.println(ftn.getFile().getPath() + " : bytes = " + ftn.getFile().length() + (ftn.getFile().isHidden() ? " (hidden)" : ""));
    	}
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
	
	public TreeCellRenderer getWrappedCellRenderer() {
		return getCellRenderer();
	}
	
	public class MyDelegatingRenderer extends DefaultTreeRenderer implements TreeCellRenderer, RolloverRenderer {
		/*
MyDefaultTreeCellRenderer delegate bzw. TreeCellRenderer delegate wird nicht verwendet,
 da Component aus super.getTreeCellRendererComponent genommen wird
 
 
MyDefaultTreeCellRenderer delegate arbeitet ohne IconValue iv, StringValue sv

		 */
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
				this.delegate = myCellRenderer;
			} else {
				System.out.println("*** --- delegate:"+delegate);
			}
		}
        public TreeCellRenderer getDelegateRenderer() {
            return delegate;
        }
        /**
         * {@inheritDoc}
         * Returns a configured component, appropriate to render the given tree cell.
         */
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        	/* super.getTreeCellRendererComponent :
        	 *  cellContext.installContext(tree, value, row, 0, selected, hasFocus, expanded, leaf);
        	 *  Component comp = componentController.getRendererComponent(cellContext);
        	 *  cellContext.replaceValue(null);
        	 *  return comp;
        	 */       	
            Component comp = super.getTreeCellRendererComponent(tree, value, 
            	selected, expanded, leaf, row, hasFocus);
            if ((compoundHighlighter != null) && (row < getRowCount()) && (row >= 0)) {
            	// doHighlight comp is WrappingIconPanel:
            	ComponentAdapter componentAdapter = getComponentAdapter(row);           	
//            	System.out.println("MyDelegatingRenderer doHighlight for "+row+" value="+componentAdapter.getValue()
////            	+ " with "+componentAdapter + " and "+comp);
//            	+ " / "+((WrappingIconPanel)comp).getComponent());
            	comp = compoundHighlighter.highlight(comp, componentAdapter);
            } 
            return comp;
        }
        
        /**
         * {@inheritDoc}
         */
        /*
         * definiert in interface org.jdesktop.swingx.rollover.RolloverRenderer
         * überschreibt die implementierung aus org.jdesktop.swingx.renderer.AbstractRenderer
         *  dort wird ComponentProvider<?> componentController und nicht delegate genommen XXX ?
         *  TODO das ist nie true
         */
        @Override
        public boolean isEnabled() {
//        	boolean ret = (delegate instanceof RolloverRenderer) && ((RolloverRenderer) delegate).isEnabled();
        	boolean ret = (delegate instanceof MyDefaultTreeCellRenderer);
//        	System.out.println("isEnabled() liefert "+ret + " "+delegate);
            return ret;
        }
            
        /**
         * {@inheritDoc}
         * Programmatically perform a "click". 
         * This does the same thing as if the user had pressed and released the button.
         */
        /*
         * definiert in interface org.jdesktop.swingx.rollover.RolloverRenderer
         * überschreibt die implementierung aus org.jdesktop.swingx.renderer.AbstractRenderer,
         *  dort wird ComponentProvider<?> componentController und nicht delegate genommen XXX ?
         */
        @Override
        public void doClick() {
            if(isEnabled()) {
            	if(delegate instanceof MyDefaultTreeCellRenderer renderer) {
                	Dimension size = getSize(); //delegate.getSize(size);
                	System.out.println("isEnabled() ist TRUE, selected="+renderer.selected
                			+" size="+size
                			+" "+renderer.getText()
//                			+componentController.toString()
                			+" in this:"+this);
//                    ((RolloverRenderer) delegate).doClick();
//                	delegate.setBackground(Color.YELLOW);
//                	delegate.paintImmediately(new Rectangle(0,0, size.width, size.height));
            	}
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
		System.out.println("setCellEditor -------->:"+cellEditor);
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
