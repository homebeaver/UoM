package io.homebeaver;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.TransferHandler;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JYList;

import io.homebeaver.uom.UoMTreeNode;

/*

Ein tree element löschen:
Dafür das element per Drag-and-drop auf delete list ziehen.

Ablauf:
- getSourceActions liefert COPY_OR_MOVE
- createTransferable liefert StringSelection Transferable mit JSON externaliserte element
  (Transferable wird zum remove nicht benötigt)
- importData wird bei drop ausgeführt und liefert true für delete list 
- cleanup entfernt das tree element (und alle childs)


Ein tree element verschieben:
Dafür das element (source/drop data/newValue) per Drag-and-drop auf ein anderes tree element(target) ziehen.

source tree element : das element, das verschoben wird
drop data : externalisertes source tree element JSON
newValue : internalisiertes drop data, 
           also Instanz vom Typ UoMTreeNode.QuantityTreeNode oder UoMTreeNode.DirectoryTreeNode,
           SubClass von GenericTreeNode<?> 

target tree element : wohin wird source verschoben (immer hinter/unterhalb target)
- target instanceof QuantityTreeNode: source muss QuantityTreeNode sein!
                                      source instanceof DirectoryTreeNode ==> warnung: target QuantityTreeNode darf keine children haben
                                      source wird hinter target verschoben
- target instanceof DirectoryTreeNode: 
-- source instanceof QuantityTreeNode: source wird unterhalb target verschoben (als letztes child)
-- source instanceof DirectoryTreeNode: source-tree wird unterhalb target verschoben (als letztes child)

TODO tree element bearbeiten und neues tree element erstellen

 */
/**
 * TreeTransferHandler used in tree drag-and-drop operations.
 * 
 * The source is copied from ListTransferHandler, which is used by the 
 *  <a href="https://docs.oracle.com/javase/tutorial/uiswing/dnd/dropmodedemo.html">DropDemo example</a>.
 * <p>
 * Example:
 * <pre>
 *   transferHandler = new TreeTransferHandler(); ...
 *   tree.setTransferHandler(transferHandler);
 *   tree.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
 *   tree.setTransferHandler(transferHandler);
 *   tree.setDropMode(DropMode.ON);
 *   // to delete a tree element drag it and drop it onto the delete list:
 *   list.setTransferHandler(transferHandler);
 *   list.setDropMode(DropMode.ON);
 * </pre>
 */
@SuppressWarnings("serial")
public class TreeTransferHandler extends TransferHandler {
	
	private static final Logger LOG = Logger.getLogger(TreeTransferHandler.class.getName());

	private TreePath[] treeSelectionPath = null;
    private int[] indices = null;
    protected void setIndizes(int[] indizes) {
    	this.indices = indizes;
    }
    private int addIndex = -1; //Location where items were added
    private int addCount = 0;  //Number of items added.
            
    public boolean canImport(TransferHandler.TransferSupport info) {
        // Check for String flavor
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
   }

    protected Transferable createTransferable(JComponent c) {
		LOG.info("JComponent "+c);
        return new StringSelection(exportString(c));
    }
    
    public int getSourceActions(JComponent c) {
		LOG.fine("JComponent "+c);
        return TransferHandler.COPY_OR_MOVE;
    }
    
    public boolean importData(TransferHandler.TransferSupport info) {
        if (!info.isDrop()) {
            return false;
        }

		LOG.info("Drop !!! to "+info.getComponent());
		LOG.info("DropLocation:"+info.getDropLocation()); // JTree.DropLocation

        // Get the string that is being dropped.
        Transferable t = info.getTransferable();
        String data;
        try {
            data = (String)t.getTransferData(DataFlavor.stringFlavor);
    		LOG.info("Drop data:"+data);
        } 
        catch (Exception e) { return false; }
                        
        Component comp = info.getComponent();
        
        if(comp instanceof MyXTree tree) {
        	JTree.DropLocation shownDropLocation =tree.getDropLocation(); // the location that this component should visually indicate
        	JTree.DropLocation transferDropLocation = (JTree.DropLocation)info.getDropLocation();
        	TreePath tp = transferDropLocation.getPath();
        	LOG.info("Drop data:"+data
        			+"\n    shownDropLocation:"+shownDropLocation
        			+"\n transferDropLocation:"+transferDropLocation
        			+"\n transferDropLocation.Path:"+tp
        			);
        	// TODO insert data to PATH 
        	TreeModel model = tree.getModel();
        	if(model instanceof GenericTreeModel gtm) {
        		GenericTreeNode<?> root = (GenericTreeNode<?>)gtm.getRoot();
        		LOG.info(">>>es wird in GenericTreeModel gtm "+gtm+" eingefügt, transferDropLocation.Path:"+tp);
        		GenericTreeNode<?> newValue = UoMTreeNode.internalize(data);
        		gtm.valueForPathChanged(tp, newValue);
        	}
        	return true;
        }
        
        if(comp instanceof JYList<?> list) {
        	if("deleteList".equals(list.getName())) {
        		LOG.info("Drop to deleteList data:"+data);
        		return true;
        	}
        }
        return false;
    }

    protected void exportDone(JComponent c, Transferable data, int action) {
        cleanup(c, action == TransferHandler.MOVE);
    }

    //Bundle up the selected items in the list as a single string, for export.
    protected String exportString(JComponent c) {
    	StringBuffer buff = new StringBuffer();
    	if(c instanceof JList<?> list) {
    		setIndizes(list.getSelectedIndices());
    		List<?> l = list.getSelectedValuesList();
    		Object[] values = new Object[l.size()];
    		l.toArray(values); // fill the array
    		for (int i = 0; i < values.length; i++) {
                Object val = values[i];
                buff.append(val == null ? "" : val.toString());
                if (i != values.length - 1) {
                    buff.append("\n");
                }
    		}
    	}
    	if(c instanceof MyXTree tree) {
    		// default SelectionMode ist 4 == DISCONTIGUOUS_TREE_SELECTION
    		LOG.info("tree.SelectionMode="+tree.getSelectionMode()); // expected 1
    		int[] treeSelectionRows = tree.getSelectionRows();
    		LOG.info("tree.selRows#="+treeSelectionRows.length);
    		treeSelectionPath = tree.getSelectionPaths();
    		LOG.info("tree.selPath#="+treeSelectionPath.length + "/"+treeSelectionPath[0].getPathCount() + " "+treeSelectionPath[0]);
    		LOG.info("LastPathComponent:"+treeSelectionPath[0].getLastPathComponent().getClass());
    		if(treeSelectionPath.length==0) {
    			LOG.warning("SelectionPath is empty! SelectionRow is "+(treeSelectionRows.length==0?" empty!":treeSelectionRows[0]));
    		} else {
    			LOG.info("SelectionPath is NOT empty! SelectionRow is "+(treeSelectionRows.length==0?" empty!":treeSelectionRows[0]));
    			Object lpc = treeSelectionPath[0].getLastPathComponent();
    			UoMTreeNode uomTreeNode = null;
    			if(lpc instanceof UoMTreeNode uomtn) {
    				uomTreeNode = uomtn;
    			} else if(lpc instanceof GenericTreeNode gtn) {
    				uomTreeNode = (UoMTreeNode)gtn.getObject();
    			}
    			buff.append(uomTreeNode.externalize());
    		}
    	}
		LOG.info("buff:"+buff.toString());
        return buff.toString();
    }

    //Take the incoming string and wherever there is a newline, 
    //break it into a separate item in the list.
    protected void importString(JComponent c, String str) {
    	int index = -1;
    	int max = 0;
    	ListModel<?> listModel = null;
    	if(c instanceof JList<?> list) {
    		index = list.getSelectedIndex();
    		listModel = list.getModel();
    		max = listModel.getSize();
    	}

        //Prevent the user from dropping data back on itself.
        //For example, if the user is moving items #4,#5,#6 and #7 and
        //attempts to insert the items after item #5, this would
        //be problematic when removing the original items.
        //So this is not allowed.
        if (indices != null && index >= indices[0] - 1 && index <= indices[indices.length - 1]) {
            indices = null;
            return;
        }

        if (index < 0) {
            index = max;
        } else {
            index++;
            if (index > max) {
                index = max;
            }
        }
        addIndex = index;
        String[] values = str.split("\n");
        addCount = values.length;
        for (int i = 0; i < values.length; i++) {
        	if(listModel instanceof DefaultListModel<?>) {
        		DefaultListModel<Object> model = (DefaultListModel<Object>)listModel;
                model.add(index++, values[i]);
        	}
        	if(listModel instanceof DefaultComboBoxModel<?>) {
        		DefaultComboBoxModel<Object> model = (DefaultComboBoxModel<Object>)listModel;
        		model.insertElementAt(values[i], index++);
        	}
        }
    }
    
    //If the remove argument is true, the drop has been
    //successful and it's time to remove the selected items 
    //from the list. If the remove argument is false, it
    //was a Copy operation and the original list is left
    //intact.
    protected void cleanup(JComponent c, boolean remove) {
    	LOG.info((remove?"remove ":"")+"c:"+c);
        if (remove && indices != null) {
            //If we are moving items around in the same list, we need to adjust the indices accordingly, 
        	//since those after the insertion point have moved.
            if (addCount > 0) {
                for (int i = 0; i < indices.length; i++) {
                    if (indices[i] > addIndex) {
                        indices[i] += addCount;
                    }
                }
            }
            if(c instanceof JList<?> list) {
            	ListModel<?> listModel = list.getModel();
            	if(listModel instanceof DefaultListModel<?> model) {
                    for (int i = indices.length - 1; i >= 0; i--) {
                        model.remove(indices[i]);
                    }
            	} 
            	if(listModel instanceof DefaultComboBoxModel<?> model) {
                    for (int i = indices.length - 1; i >= 0; i--) {
                        model.removeElement(indices[i]);
                    }
            	} 
            }
        }
        if(remove && c instanceof MyXTree tree) {
        	TreeModel model = tree.getModel();
        	LOG.info((remove?"remove ":"")+"from TreeModel:"+model);
        	if(model instanceof GenericTreeModel gtm) {
        		LOG.info("remove tree.getSelectionRows().length =???= "+tree.getSelectionRows().length);
        		TreePath tp = //tree.getPathForRow(treeSelectionRows[0]);
        				treeSelectionPath[0];
        		LOG.info("remove TreePath "+tp.getLastPathComponent());
        		gtm.valueForPathChanged(tp, null); // remove == set value to null
        		tree.updateUI();
        	}
        }
        indices = null;
        addCount = 0;
        addIndex = -1;
        treeSelectionPath = null;
    }
}
