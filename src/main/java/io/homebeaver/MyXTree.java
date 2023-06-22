package io.homebeaver;

import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.IconValues;
import org.jdesktop.swingx.rollover.RolloverProducer;

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
		//return new JXTree.DelegatingRenderer(iv, null); gleichwertig mit
		//       new JXTree.DelegatingRenderer((TreeCellRenderer)null, iv, null)
		// dieser ctor instanziert new DefaultXTreeCellRenderer(), mit MyTreeCellRenderer kann ich experimentieren:
		return new JXTree.DelegatingRenderer(
				new MyTreeCellRenderer(), iv, null);
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
