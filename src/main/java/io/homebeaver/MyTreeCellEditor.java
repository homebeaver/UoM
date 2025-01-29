package io.homebeaver;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.EventObject;
import java.util.logging.Logger;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.TreeCellEditor;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.plaf.UIDependent;

import io.homebeaver.uom.UoMCellEditor;
import io.homebeaver.uom.UoMTreeNode;

/** wie class DefaultXTreeCellEditor extends DefaultTreeCellEditor implements UIDependent
 * 
 * Die Oberklasse javax.swing.tree.DefaultTreeCellEditor 
 *  implements ActionListener, TreeCellEditor, TreeSelectionListener
 * mit ctor DefaultTreeCellEditor(JTree tree, 
 *  DefaultTreeCellRenderer renderer, // extends JLabel - renderer muss also ein JLabel sein
 *  TreeCellEditor editor) // realEditor bei null gibt es ein default protected createTreeCellEditor()
 * und protected Container editingContainer = createContainer(); 
 *   + inner public class EditorContainer extends Container
 * 
 * @author EUGen
 */
public class MyTreeCellEditor extends DefaultTreeCellEditor implements UIDependent {

	private static final Logger LOG = Logger.getLogger(MyTreeCellEditor.class.getName());

	MyDefaultTreeCellRenderer myRenderer;
	
	public MyTreeCellEditor(JTree tree, MyDefaultTreeCellRenderer renderer) {
		this(tree, renderer, null);
	}

	public MyTreeCellEditor(JTree tree, MyDefaultTreeCellRenderer renderer, TreeCellEditor editor) {
		super(tree, null, editor); // TODO in super ist nur javax.swing.tree.DefaultTreeCellRenderer vorgesehen
		// MyDefaultTreeCellRenderer ist aber nicht davon abgeleitet, sondern:
		// class MyDefaultTreeCellRenderer extends JLabel implements TreeCellRenderer
		LOG.info("ctor fertig mit \n renderer:"+renderer + " \n editor:"+editor + " \n realEditor:"+realEditor);
		myRenderer = renderer;
	}

	/*
	 * defined in interface TreeSelectionListener
	 */
	public void valueChanged(TreeSelectionEvent e) {
//		LOG.info("number of nodes selected="+(tree==null?"":tree.getSelectionCount())+" TreeSelectionEvent:"+e);
		super.valueChanged(e); // Resets <code>lastPath</code>.
//		LOG.info("path that was selected="+lastPath+" TreeSelectionEvent:"+e);
		if(lastPath!=null) {
			Object lpo = lastPath.getLastPathComponent();
			int[] selRows = tree.getSelectionRows();
//			LOG.info("lastPath Object (selected)="+lpo.getClass()+"/"+lpo + " row="+selRows[0]+ "\n realEditor:"+realEditor);
			// show selected Object in Editor:
			// @param   isSelected      true if the cell is to be rendered with selection highlighting
			// @param   expanded        true if the node is expanded
			// @param   leaf            true if the node is a leaf node
			Component comp = realEditor.getTreeCellEditorComponent(tree, lpo, true, true, lpo instanceof UoMTreeNode.DirectoryTreeNode, selRows[0]);
			LOG.info("lastPath Object (selected)="+lpo.getClass()+"/"+lpo + " row="+selRows[0]+ "\n realEditor component:"+comp);
			// @see https://stackoverflow.com/questions/2699788/java-is-there-a-subclassof-like-instanceof
			if(comp instanceof JXPanel panel && comp.getClass()!=JXPanel.class) {
//				System.out.println("enable "+comp.getClass().getName());
//				panel.setEnabled(true);
//				Component[] components = panel.getComponents();
//				for(int i=0; i<components.length; i++) {
//		        	if(components[i] instanceof JTextField iTextField) {
//		        		iTextField.setEnabled(panel.isEnabled());
//		        	} else if(components[i] instanceof JLabel iLabel) {
//		        		iLabel.setEnabled(panel.isEnabled());
//		        	}
//				}
			}
			if(comp instanceof UoMCellEditor.UoMComponent uomc) {
				uomc.add((UoMTreeNode)lpo);
			}
		}
	}
	
	/*
	 * defined in interface UIDependent
	 * code copied from org.jdesktop.swingx.tree.DefaultXTreeCellEditor
	 */
	@Override
	public void updateUI() {
        if (getRenderer() != null) {
            SwingUtilities.updateComponentTreeUI(getRenderer());
        }
//        LOG.info("renderer:"+renderer + "\n realEditor:"+realEditor);
        if (realEditor instanceof JComponent) {
            SwingUtilities.updateComponentTreeUI((JComponent) realEditor);
        } else if (realEditor instanceof UIDependent) {
            ((UIDependent) realEditor).updateUI();
        }
	}

//    public void setRenderer(DefaultTreeCellRenderer renderer) {
//        this.renderer = renderer;
//    }
//    public DefaultTreeCellRenderer getRenderer() {
//        return renderer;
//    }
	public Component getRenderer() {
		if(myRenderer!=null) return myRenderer;
		return renderer;
	}
	
    public Object getCellEditorValue() {
    	// liefert UoMCellEditor.EditorDelegate Z.79 public Object getCellEditorValue()
        return super.getCellEditorValue(); //realEditor.getCellEditorValue();
    }

    // copied from super javax.swing.tree.DefaultTreeCellEditor
    // used in ctor
    protected TreeCellEditor createTreeCellEditor() {
    	Border aBorder = UIManager.getBorder("Tree.editorBorder");
    	LOG.info("editor with DefaultTextField and border "+aBorder);
    	// dort: this.clickCountToStart = 2;
		DefaultCellEditor editor = new DefaultCellEditor(new DefaultTextField(aBorder)) {
			public boolean shouldSelectCell(EventObject event) {
				boolean retValue = super.shouldSelectCell(event);
				return retValue;
			}
		};

		// One click to edit.
		editor.setClickCountToStart(1);
		return editor;
    }
    
    // code copied from org.jdesktop.swingx.tree.DefaultXTreeCellEditor
    // used in ctor
    protected Container createContainer() {
    	LOG.info("-------editingContainer-------");
        return new XEditorContainer();
    }

    private boolean isRightToLeft() {
        return (tree != null) && (!tree.getComponentOrientation().isLeftToRight());
    }

    public Component getTreeCellEditorComponent(JTree tree, Object value,
            boolean isSelected, boolean expanded, boolean leaf, int row) {
    	LOG.info("---------->JTree:"+tree + "\n value:"+value);
    	return super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
    }

    public class XEditorContainer extends EditorContainer {

        @Override
        public Dimension getPreferredSize() {
            if (isRightToLeft()) {
                if(editingComponent != null) {
                    Dimension         pSize = editingComponent.getPreferredSize();
    
                    pSize.width += offset + 5;
    
                    Dimension         rSize = (renderer != null) ?
                                              renderer.getPreferredSize() : null;
    
                    if(rSize != null)
                        pSize.height = Math.max(pSize.height, rSize.height);
                    if(editingIcon != null)
                        pSize.height = Math.max(pSize.height,
                                                editingIcon.getIconHeight());
    
                    // trying to enforce a minimum size leads to field being painted over the icon
                    // Make sure width is at least 100.
    //                pSize.width = Math.max(pSize.width, 100);
                    return pSize;
                }
                return new Dimension(0, 0);
            }
            return super.getPreferredSize();
            
        }

        @Override
        public void doLayout() {
            if (isRightToLeft()) {
                Dimension             cSize = getSize();

                editingComponent.getPreferredSize();
                editingComponent.setLocation(0, 0);
                editingComponent.setBounds(0, 0,
                                           cSize.width - offset,
                                           cSize.height);
            } else {

                //super.doLayout();
                if(editingComponent != null) {
                    int width = getWidth();
                    int height = getHeight();
                    if (getComponentOrientation().isLeftToRight()) {
                        editingComponent.setBounds(offset, 0, width - offset, height);
                    } else {
                        editingComponent.setBounds(0, 0, width - offset, height);
                    }
                }
            }
        }


        @Override
        public void paint(Graphics g) {
            if (isRightToLeft()) {
                Dimension size = getSize();

                // Then the icon.
                if (editingIcon != null) {
                    int yLoc = Math.max(0, (size.height - editingIcon.getIconHeight()) / 2);
                    int xLoc = Math.max(0, size.width - offset);
                    editingIcon.paintIcon(this, g, xLoc, yLoc);
                }
                // need to prevent super from painting the icon
                Icon rememberIcon = editingIcon;
                editingIcon = null;
                super.paint(g);
                editingIcon = rememberIcon;
                
            } else {
                super.paint(g);
            }
        }
        
    }

}
