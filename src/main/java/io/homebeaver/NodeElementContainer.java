package io.homebeaver;

import java.awt.Component;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.JXFormattedTextField;
import org.jdesktop.swingx.JXTextField;
import org.json.simple.JSONObject;

import io.homebeaver.uom.UoMTreeNode;
import io.homebeaver.uom.UoMTreeNodeContainer;

@SuppressWarnings("serial")
public class NodeElementContainer extends JPanel implements UoMTreeNodeContainer, DocumentListener {

	static final String TITLE = "Node Element";
	JCheckBox editSelected;
	UoMTreeNode uomNode;
	JComponent uomLlist; // component to update when editing
	HashMap<Document, String> doc2key = new HashMap<Document, String>();
	public NodeElementContainer(JCheckBox editSelected, JComponent uomLlist) {
		super(new GridLayout(0, 2));
        setBorder(new TitledBorder(TITLE));
        this.editSelected = editSelected;
        this.uomLlist = uomLlist;
	}
	
	private JSONObject toJSONObject(UoMTreeNode uomNode) {
    	if (uomNode instanceof UoMTreeNode.DirectoryTreeNode dtn) {
    		JSONObject jsonUom = dtn.externalize(new JSONObject());
    		return (JSONObject)jsonUom.get(UoMTreeNode.OBJECT);
    	} else if (uomNode instanceof UoMTreeNode.QuantityTreeNode uom) { 
    		JSONObject jsonUom = uom.externalize(new JSONObject());
    		return (JSONObject)jsonUom.get(UoMTreeNode.OBJECT);
    	}
    	return null;
	}
	// in (super) public Component Container.add(Component comp)
    //            public Component Container.add(String name, Component comp) 
	//            public Component Container.add(Component comp, int index)
	@Override
	public Component add(UoMTreeNode uomNode) {
    	TreeNode parent = uomNode.getParent();
    	setBorder(new TitledBorder(parent == null ? TITLE : TITLE + " of Type "+uomNode.getParent()));
    	removeAll();
    	
    	add(toJSONObject(uomNode));
    	
    	revalidate();
    	setVisible(true);
    	this.uomNode = uomNode;
    	return this;
	}
	
	private void add(JSONObject o) {
		doc2key.clear();
		HashMap<String, Object> jo = o;
		jo.forEach( (k, v) -> {
//			System.out.println("key:"+k + " value:"+v 
//					+ (v==null ? "" : "<of type "+v.getClass().getSimpleName()+">"));
			JLabel label = new JLabel((String)k);
       		if (v instanceof Integer) {
    			NumberFormat format = NumberFormat.getNumberInstance();
    			JXFormattedTextField field = new JXFormattedTextField(format);
    			field.setValue(v);
    			field.setHorizontalAlignment(SwingConstants.RIGHT);
    			field.setEditable(false); // read only
    			label.setLabelFor(field);
    			add(label);
    			add(field);
       		} else if (v==null && "uomSymbol".equals(k)) {
       			// bei v==null uomSymbol nicht anzeigen
       		} else {
    			JXTextField field = new JXTextField();
    			field.setText((String)v); // auch bei v==null
    			label.setLabelFor(field);
    			add(label);
    			add(field);
    			doc2key.put(field.getDocument(), k);
//    			try {
//    				
//					System.out.println(field.getDocument().getText(0, field.getDocument().getLength()));
////					field.getDocument().addDocumentListener(null);
//				} catch (BadLocationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
       		}
		});
		setEnabled(editSelected.isSelected()); // start with disabled fields
	}
	
	/*
	 * Disabling/Enabling a component does not disable its children
	 */
    public void setEnabled(boolean enabled) {
    	super.setEnabled(enabled);
		Component[] components = getComponents();
		for (Component c : components) {
			if(c instanceof JXFormattedTextField f) {
				f.setEnabled(enabled);
			} else if(c instanceof JXTextField f) {
				f.setEnabled(enabled);
				if(enabled) {
					f.getDocument().addDocumentListener(this);
//					f.addPropertyChangeListener(pce -> {
/*
     * Note that text is not a bound property, 
     * so no <code>PropertyChangeEvent</code> is fired when it changes. 
     * To listen for changes to the text, use <code>DocumentListener</code>.
 */
//						Object source = pce.getSource();
////			    		System.out.println("field PropertyChangeEvent "+pce
////			    		+ "\n Source:"+source
////			    		);
//			    		if(source instanceof JXTextField tf) {
//			    			JLabel labeledBy = (JLabel)tf.getClientProperty("labeledBy");
////				    		System.out.println("field "+labeledBy.getText()+" value="+tf.getText());
//				    		HashMap<String, Object> jo = toJSONObject(uomNode);
//				    		Object old = jo.get(labeledBy.getText());
//				    		if(tf.getText().equals(old)) {
//				    			// unverändert
//				    		} else {
//					    		System.out.println("CHANGED field "+labeledBy.getText()+" value="+tf.getText());
//					    		// TODO das elem UoMTreeNode in listModel ermitteln und ändern
//				    		}
//			    		}
///*
//        	LOG.info("amountXField PropertyChangeEvent:"+pce +
//        			"\n Source:"+pce.getSource());
//        	xamount = ((Number)amountXField.getValue()).doubleValue();
//        	Double d = Double.valueOf(computePayment(xamount, xrate, xnumPeriods));
//            paymentXField.setValue(d);
//            paymentXField.setForeground(d<0 ? Color.red : Color.black);
//
// */
//					});
				}
			}
		}
    }

    // implements DocumentListener aka field listener
	@Override
	public void insertUpdate(DocumentEvent de) {
//		System.out.println("field insertUpdate "+de);
		displayEditInfo(de);
	}

	@Override
	public void removeUpdate(DocumentEvent de) {
//		System.out.println("field removeUpdate "+de);
		displayEditInfo(de);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		System.out.println("Plain text components do not fire these events "+e);
		
	}
	
	private void displayEditInfo(DocumentEvent e) {
		Document document = e.getDocument();
		String k = doc2key.get(document);
		try {
			String v = document.getText(0, document.getLength());
//			System.out.println(k + ":" + v);
			uomNode.getObject().set(k, v);
			uomLlist.updateUI();
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
