/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
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

import io.homebeaver.uom.UoM;
import io.homebeaver.uom.UoMTreeNode;

/*
 * ListTransferHandler.java is used by the DropDemo example.
 * copied from https://docs.oracle.com/javase/tutorial/uiswing/dnd/dropmodedemo.html
 * 
 * honor instanceof DefaultListModel and DefaultComboBoxModel as listModel
 */
@SuppressWarnings("serial")
public class TreeTransferHandler extends TransferHandler {
	
	private static final Logger LOG = Logger.getLogger(TreeTransferHandler.class.getName());

	private int[] treeSelectionRows = null;
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
		LOG.info("JComponent "+c);
        return TransferHandler.COPY_OR_MOVE;
    }
    
    public boolean importData(TransferHandler.TransferSupport info) {
        if (!info.isDrop()) {
            return false;
        }

		LOG.info("Drop !!! to "+info.getComponent());
		LOG.info("DropLocation:"+info.getDropLocation()); // JTree.DropLocation
//        JList.DropLocation dl = (JList.DropLocation)info.getDropLocation();
//        int index = dl.getIndex();
//        boolean insert = dl.isInsert();

        // Get the string that is being dropped.
        Transferable t = info.getTransferable();
        String data;
        try {
            data = (String)t.getTransferData(DataFlavor.stringFlavor);
    		LOG.info("Drop data:"+data);
        } 
        catch (Exception e) { return false; }
                        
        Component comp = info.getComponent();
/* wird nicht benötigt:
        if(comp instanceof JList<?> list) {
        	ListModel<?> listModel = list.getModel();
        	if(listModel instanceof DefaultListModel<?>) {
        		DefaultListModel<Object> model = (DefaultListModel<Object>)listModel;
                // Perform the actual import.  
                if (insert) {
                	model.add(index, data);
                } else {
                	model.set(index, data);
                }
                return true;
        	}
        	if(listModel instanceof DefaultComboBoxModel<?>) {
        		DefaultComboBoxModel<Object> model = (DefaultComboBoxModel<Object>)listModel;
                // Perform the actual import.  
                if (insert || index<0) {
                	model.addElement(data);
                } else {
                	model.insertElementAt(data, index);
                }
                return true;
        	}
        }
 */
        
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
        	// BUG TODO: root nicht überschreiben
        	TreeModel model = tree.getModel();
        	if(model instanceof GenericTreeModel gtm) {
        		LOG.info(">>>es wird in GenericTreeModel gtm "+gtm+" eingefügt transferDropLocation.Path:"+tp);
        		UoM uom = UoM.createFromJsonString(data);
        		LOG.info(">>>was wird eingefügt uom:"+uom);
        		GenericTreeNode<?> newValue = GenericTreeNode.create(uom);
        		gtm.valueForPathChanged(tp, newValue);
        		tree.updateUI(); // wird in cleanup gemacht
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
    		treeSelectionRows = tree.getSelectionRows();
    		LOG.info("tree.selRows#="+treeSelectionRows.length);
    		TreePath[] selPath = tree.getSelectionPaths();
    		LOG.info("tree.selPath#="+selPath.length + "/"+selPath[0].getPathCount() + " "+selPath[0]);
    		LOG.info("LastPathComponent:"+selPath[0].getLastPathComponent().getClass());
    		if(selPath.length==0) {
    			LOG.warning("SelectionPath is empty! SelectionRow is "+(treeSelectionRows.length==0?" empty!":treeSelectionRows[0]));
    		} else {
    			LOG.info("SelectionPath is NOT empty! SelectionRow is "+(treeSelectionRows.length==0?" empty!":treeSelectionRows[0]));
//    			if(selPath[0].getLastPathComponent() instanceof UoMTreeNode.QuantityTreeNode leaf) {
//    				UoM uom = leaf.getObject();
//    				buff.append(uom.externalize());
//    			} else {
//    				// TODO
//    			}
    			Object lpc = selPath[0].getLastPathComponent();
    			UoMTreeNode uomTreeNode = (UoMTreeNode)lpc;
    			UoM uom = uomTreeNode.getObject();
    			buff.append(uom.externalize());
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
        		TreePath tp = tree.getPathForRow(treeSelectionRows[0]);
        		gtm.valueForPathChanged(tp, null); // remove == set value to null
        		tree.updateUI();
        	}
        }
        indices = null;
        addCount = 0;
        addIndex = -1;
        treeSelectionRows = null;
    }
}
