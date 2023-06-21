package io.homebeaver;

import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import org.jdesktop.swingx.JXTree;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.IconValues;

import io.homebeaver.uom.UoMTreeNode;
import net.sf.fstreem.FileSystemTreeNode;

/*
 * erweitert JXTree um

 - getCellRenderer() mit IconValue
 - setSelectionMode analog zu JList, wurde in JXTree implementiert
 
 */
public class MyXTree extends JXTree {

	public MyXTree(TreeModel newModel) {
		super(newModel);
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
		return new JXTree.DelegatingRenderer((TreeCellRenderer)null, iv, null);
		// dieser ctor instanziert new DefaultXTreeCellRenderer()
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
