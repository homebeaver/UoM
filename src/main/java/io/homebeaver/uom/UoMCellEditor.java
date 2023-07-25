package io.homebeaver.uom;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.EventObject;
import java.util.logging.Logger;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.TableCellEditor;
import javax.swing.text.NumberFormatter;
import javax.swing.tree.TreeCellEditor;

import org.jdesktop.swingx.JXPanel;

import layout.SpringUtilities;

/* copied from javax.swing.DefaultCellEditor

public interface javax.swing.table.TableCellEditor extends CellEditor
methods to implement:
	Component TableCellEditor#getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	public Object CellEditor#getCellEditorValue()
	public boolean CellEditor#isCellEditable(EventObject anEvent)
	public boolean CellEditor#shouldSelectCell(EventObject anEvent)
	public boolean CellEditor#stopCellEditing()
	public void CellEditor#cancelCellEditing()
	public void CellEditor#addCellEditorListener(CellEditorListener l)
	public void CellEditor#removeCellEditorListener(CellEditorListener l)
	
 */
@SuppressWarnings("serial") // Same-version serialization only
public class UoMCellEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor {

    private static Logger LOG = Logger.getLogger(UoMCellEditor.class.getName());

//
//  Instance Variables
//

    /** The Swing component being edited. */
    protected JComponent editorComponent;
    /**
     * The delegate inner class which handles all methods sent from the
     * <code>CellEditor</code>.
     */
    protected EditorDelegate delegate;
    /**
     * An integer specifying the number of clicks needed to start editing.
     * Even if <code>clickCountToStart</code> is defined as zero, it
     * will not initiate until a click occurs.
     */
    protected int clickCountToStart = 1;

    /*

die Position des Editors ist per default anstelle des tree objekts im renderer.
Ich hätte es lieber im separatem Fenster, dafür überschreibe ich
valueChanged(TreeSelectionEvent e) in MyTreeCellEditor

     */
    @ConstructorProperties({"component"})
    public UoMCellEditor(final JPanel jPanel) {
        editorComponent = jPanel;
        this.clickCountToStart = 2;
        delegate = new EditorDelegate() {
            public void setValue(Object value) {
            	// @see https://stackoverflow.com/questions/2699788/java-is-there-a-subclassof-like-instanceof
            	if(value != null && value.getClass()!=UoMTreeNode.class && value instanceof UoMTreeNode uomtn) {
//            		LOG.info("Hurra?????????????????value:"+value.getClass()+" : "+value);
            		UoMComponent uomPanel = (UoMComponent)editorComponent;
            		uomPanel.setFields(uomtn.getObject());
            	} else {
            		LOG.warning("MIST?????????????????value:"+value.getClass()+" : "+value);
            	}
            }

            public Object getCellEditorValue() {
                //return textField.getText();
            	if(editorComponent instanceof UoMComponent uomPanel) {
            		uomPanel.idField.getText();
            		((JFormattedTextField)uomPanel.idField).getValue();
            		return new UoM(-99, uomPanel.nameField.getText(), uomPanel.descriptionField.getText(), uomPanel.uomSymbolField.getText());
            	}
				return null;
            }
        };
        // addActionListener(UoMCellEditor.EditorDelegate) is undefined for the type JPanel
        //jPanel.addActionListener(delegate);
        UoMComponent uomPanel = (UoMComponent)editorComponent;
        uomPanel.addActionListener(delegate);
    }
    
    @ConstructorProperties({"component"})
    public UoMCellEditor(final JTextField textField) {
        editorComponent = textField;
        this.clickCountToStart = 2;
        delegate = new EditorDelegate() {
            public void setValue(Object value) {
                textField.setText((value != null) ? value.toString() : "");
            }

            public Object getCellEditorValue() {
                return textField.getText();
            }
        };
        textField.addActionListener(delegate);
    }

    public Component getComponent() {
        return editorComponent;
    }

    public void setClickCountToStart(int count) {
        clickCountToStart = count;
    }
    public int getClickCountToStart() {
        return clickCountToStart;
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#getCellEditorValue
     */
	@Override
    public Object getCellEditorValue() {
        return delegate.getCellEditorValue();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#isCellEditable(EventObject)
     */
	@Override
    public boolean isCellEditable(EventObject anEvent) {
        return delegate.isCellEditable(anEvent);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#shouldSelectCell(EventObject)
     */
	@Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return delegate.shouldSelectCell(anEvent);
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#stopCellEditing
     */
	@Override
    public boolean stopCellEditing() {
        return delegate.stopCellEditing();
    }

    /**
     * Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#cancelCellEditing
     */
	@Override
    public void cancelCellEditing() {
        delegate.cancelCellEditing();
    }

//
//  Implementing the TreeCellEditor Interface
//

    /** Implements the <code>TreeCellEditor</code> interface. */
	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
			boolean leaf, int row) {
		LOG.info("Object value:"+value.getClass()+" "+value);
		String stringValue = tree.convertValueToText(value, isSelected, expanded, leaf, row, false);
//		delegate.setValue(stringValue);
		delegate.setValue(value);
		return editorComponent;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		LOG.info("--------------- TODO --------------------- value:"+value);
		return null;
	}

	public static class UoMComponent extends JXPanel {
		
		static String[] labels = {UoM.ID, UoM.NAME, UoM.DESCRIPTION, UoM.UOMSYMBOL};
		static JTextField[] fields = new JTextField[labels.length];
		JTextField idField;
    	JTextField nameField = new JTextField(20);
    	JTextField descriptionField = new JTextField(40);
    	JTextField uomSymbolField = new JTextField(20);
    	
		public UoMComponent(LayoutManager layout) {
			super(layout);
			LOG.info("--------------- ctor --------------------- \n layout:"+layout);
//			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			NumberFormat format = NumberFormat.getInstance();
		    NumberFormatter formatter = new NumberFormatter(format);
		    formatter.setValueClass(Integer.class);
		    formatter.setMinimum(0);
		    formatter.setMaximum(Integer.MAX_VALUE);
		    formatter.setAllowsInvalid(false);
		    // If you want the value to be committed on each keystroke instead of focus lost
		    formatter.setCommitsOnValidEdit(true);
		    idField = new JFormattedTextField(formatter);
	    	nameField = new JTextField(20);
	    	descriptionField = new JTextField(40);
	    	uomSymbolField = new JTextField(20);
	    	fields[0] = idField;
	    	fields[1] = nameField;
	    	fields[2] = descriptionField;
	    	fields[3] = uomSymbolField;
//	    	add(idField);
//	    	add(nameField);
//	    	add(descriptionField);
//	    	add(uomSymbolField);
			for (int i = 0; i < labels.length; i++) {
			    JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			    this.add(l);
			    l.setLabelFor(fields[i]);
			    this.add(fields[i]);
			}
			//Lay out the panel.
			SpringUtilities.makeCompactGrid(this,
					labels.length, 2, //rows, cols
					6, 6,        //initX, initY
					6, 6);       //xPad, yPad
		}
		
		void setFields(UoM uom) {
			((JFormattedTextField)idField).setValue(uom.id);
	    	nameField.setText(uom.name);
	    	descriptionField.setText(uom.description);
	    	uomSymbolField.setText(uom.uomSymbol);
		}
		
	    public synchronized void addActionListener(ActionListener l) {
	        listenerList.add(ActionListener.class, l);
	    }

	}
    protected class EditorDelegate implements ActionListener, ItemListener, Serializable {

        /**  The value of this cell. Usually of type UoM */
        protected Object value;

        /**
         * Constructs an {@code EditorDelegate}.
         */
        protected EditorDelegate() {}

       /**
        * Returns the value of this cell.
        * @return the value of this cell
        */
        public Object getCellEditorValue() {
            return value;
        }

       /**
        * Sets the value of this cell.
        * @param value the new value of this cell
        */
        public void setValue(Object value) {
            this.value = value;
        }

       /**
        * Returns true if <code>anEvent</code> is <b>not</b> a
        * <code>MouseEvent</code>.  Otherwise, it returns true
        * if the necessary number of clicks have occurred, and
        * returns false otherwise.
        *
        * @param   anEvent         the event
        * @return  true  if cell is ready for editing, false otherwise
        * @see #setClickCountToStart
        * @see #shouldSelectCell
        */
        public boolean isCellEditable(EventObject anEvent) {
            if (anEvent instanceof MouseEvent) {
                return ((MouseEvent)anEvent).getClickCount() >= clickCountToStart;
            }
            return true;
        }

       /**
        * Returns true to indicate that the editing cell may
        * be selected.
        *
        * @param   anEvent         the event
        * @return  true
        * @see #isCellEditable
        */
        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }

       /**
        * Returns true to indicate that editing has begun.
        *
        * @param anEvent          the event
        * @return true to indicate editing has begun
        */
        public boolean startCellEditing(EventObject anEvent) {
            return true;
        }

       /**
        * Stops editing and
        * returns true to indicate that editing has stopped.
        * This method calls <code>fireEditingStopped</code>.
        *
        * @return  true
        */
        public boolean stopCellEditing() {
            fireEditingStopped();
            return true;
        }

       /**
        * Cancels editing.  This method calls <code>fireEditingCanceled</code>.
        */
       public void cancelCellEditing() {
           fireEditingCanceled();
       }

       /**
        * When an action is performed, editing is ended.
        * @param e the action event
        * @see #stopCellEditing
        */
        public void actionPerformed(ActionEvent e) {
            UoMCellEditor.this.stopCellEditing();
        }

       /**
        * When an item's state changes, editing is ended.
        * @param e the action event
        * @see #stopCellEditing
        */
        public void itemStateChanged(ItemEvent e) {
        	UoMCellEditor.this.stopCellEditing();
        }
    }

}
