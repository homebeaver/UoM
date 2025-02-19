package io.homebeaver;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import io.homebeaver.uom.UoMTreeNode;

/**
 * ListTransferHandler to move or copy an element in a List. 
 * <p>
 * Please see the <em>The Java Tutorial</em> for more information:
 * Drag and Drop and Data Transfer, 
 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/dnd/export.html">
 * Export Methods</a> and
 * <a href="https://docs.oracle.com/javase/tutorial/uiswing/dnd/import.html">
 * Import Methods</a>.
 * 
 */
@SuppressWarnings("serial")
public class ListTransferHandler extends TransferHandler {
	
    private static Logger LOG = Logger.getLogger(ListTransferHandler.class.getName());
    
    private JList<UoMTreeNode> list;
	int originOfTransferredObject = -1;
	
    /**
     * Constructs a transfer handler that can transfer a list cell
     * to another position via a drag and drop operation.
     *
     * @param list  the list with cells from and to transfer 
     */
    public ListTransferHandler(JList<UoMTreeNode> list) {
    	super();
        this.list = list;
    }
    
    // export method
    /**
     * {@inheritDoc} <p>
     * Typically, an ordinary drag requests the MOVE action. 
     * Holding the Control key while dragging requests the COPY action, 
     * and holding both Shift and Control requests the LINK action.
     */
	@Override
    public int getSourceActions(JComponent c) {
        if(c==list) {
            return COPY_OR_MOVE;
        }
        return NONE;
    }

    // export method
	/**
	 * {@inheritDoc} <p>
     * @return  the representation of the data to be transferred in JSON notation
	 */
	@Override
    protected Transferable createTransferable(JComponent c) {
        if(c==list) {
        	originOfTransferredObject = list.getSelectedIndex();
            return new StringSelection(list.getSelectedValue().externalize());
        }
        return null;
    }

    // export method
	/**
	 * {@inheritDoc} <p>
     * Invoked after data has been exported.  This method remove
     * the data that was transferred if the action was <code>MOVE</code>.
	 */
	@Override
    protected void exportDone(JComponent c, Transferable data, int action) {
        if(c==list) {
            cleanup(c, action == TransferHandler.MOVE);
        }
    }
    
    /* If the remove argument is true, the drop has been successful  
     * and it's time to remove the selected items from the list. 
     * If the remove argument is false, it was a Copy operation 
     * and the original list is left intact.
     */
    protected void cleanup(JComponent c, boolean remove) {
    	if (remove && originOfTransferredObject != -1) {
        	LOG.info((remove?"remove ":"")+"origin index of TransferredObject:"+originOfTransferredObject);
            DefaultListModel<UoMTreeNode> listModel = (DefaultListModel<UoMTreeNode>)list.getModel();
            listModel.remove(originOfTransferredObject);
    	}
    }

    // import method
    /**
	 * {@inheritDoc} <p>
     * @return  true if the area below the cursor can accept the transfer,
     * false if the transfer will be rejected
     */
	@Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        // we only import Strings
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false; // reject the transfer
        }
        JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
        if (dl.getIndex() == -1) {
            return false; // reject the transfer
        }
//        LOG.info("TransferSupport DropAction=" + info.getDropAction()
//        + " SourceDropActions="+info.getSourceDropActions()
//        + " UserDropAction="+info.getUserDropAction()
//        + " NONE==0 , COPY==1 , MOVE==2");
//        LOG.fine("TransferHandler.TransferSupport supports stringFlavor DropLocation=" + dl);
        return true;
    }

    // import method
    /**
     * {@inheritDoc}
     * <p>
	 * @param info of type TransferSupport,
     * see the <em>The Java Tutorial</em> for more information about
     * <a href="https://docs.oracle.com/javase/tutorial/uiswing/dnd/transfersupport.html">
     * TransferSupport</a>.
     */
	@Override
    public boolean importData(TransferHandler.TransferSupport info) {
    	if (!info.isDrop()) {
    		return false;
    	}
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            LOG.warning("List doesn't accept a drop of this type. "+info.getTransferable());
            return false;
        }
        if(COPY==info.getUserDropAction()) {
            boolean copySupported = (COPY & info.getSourceDropActions()) == COPY;
            if(copySupported) {
            	// do copy: import & insert data but do not remove the origin
            	LOG.info("COPY is supported.");
            } else {
            	LOG.warning("COPY is not supported.");
            }
        }

        JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
        DefaultListModel<UoMTreeNode> listModel = (DefaultListModel<UoMTreeNode>)list.getModel();
        int index = dl.getIndex();
        if(index == originOfTransferredObject || index-1 == originOfTransferredObject) {
        	LOG.warning("do not move to "+index+" from "+originOfTransferredObject);
        	return false;
        }

        boolean insert = dl.isInsert(); // true==>insert before value, false==>override value at index
        // Get the current string under or behind the drop.
        UoMTreeNode value = null;
        try {
            value = (UoMTreeNode)listModel.getElementAt(index);
        } catch (ArrayIndexOutOfBoundsException ex) {
        	LOG.info("destination is end of list");
        }
//        LOG.info("drop "+(insert?"before":"over")+" " + value + " at "+index);
        
        // Get the string that is being dropped.
        Transferable t = info.getTransferable();
        String data;
        try {
            data = (String)t.getTransferData(DataFlavor.stringFlavor);
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return false;
        }
        /* dialog with the drop information, copied from https://docs.oracle.com/javase/tutorial/uiswing/examples/dnd/index.html#BasicDnD
        String dropValue = "\"" + data + "\" dropped ";
        if (dl.isInsert()) {
            if (dl.getIndex() == 0) {
                displayDropLocation(dropValue + "at beginning of list");
            } else if (dl.getIndex() >= list.getModel().getSize()) {
                displayDropLocation(dropValue + "at end of list");
            } else {
                String value1 = (String)list.getModel().getElementAt(dl.getIndex() - 1);
                String value2 = (String)list.getModel().getElementAt(dl.getIndex());
                displayDropLocation(dropValue + "between \"" + value1 + "\" and \"" + value2 + "\"");
            }
        } else {
            displayDropLocation(dropValue + "on top of " + "\"" + value + "\"");
        }
        */
        LOG.info("drop data "+(insert?"before":"over")+" "+value+" at "+index+": "+data);
        
        // Perform the actual import:
        if (insert) {
        	UoMTreeNode uomTn = UoMTreeNode.internalize(data);
        	listModel.add(index, uomTn);
            LOG.info("inserted "+uomTn+" before " + value);
            // remove element on index originOfTransferredObject will be done in #cleanup
            // adjust the origin index if element is inserted at i<origin !!!
            if(index < originOfTransferredObject) {
            	originOfTransferredObject++;
            }
        	return true;
        } else {
        	LOG.warning("not overridden: "+value);
            return false;
        }
    }
}
