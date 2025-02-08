package io.homebeaver;

import java.awt.Component;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.JXFormattedTextField;
import org.jdesktop.swingx.JXTextField;
import org.json.simple.JSONObject;

import io.homebeaver.uom.UoMTreeNode;
import io.homebeaver.uom.UoMTreeNodeContainer;

@SuppressWarnings("serial")
public class NodeElementContainer extends JPanel implements UoMTreeNodeContainer {

	static final String TITLE = "Node Element";
	JCheckBox editSelected;
	UoMTreeNode uomNode;
//	DefaultListModel<UoMTreeNode> listModel; // TODO
	public NodeElementContainer(JCheckBox editSelected) {
		super(new GridLayout(0, 2));
        setBorder(new TitledBorder(TITLE));
        this.editSelected = editSelected;
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
//    	VetoableChangeListener ist uomNode; PropertyChangeEvent pce
/* What is the difference between PropertyChangeListener and VetoableChangeListener?
The main difference resides in the fact that 
PropertyChangeListener are applied to bound properties while 
VetoableChangeListener are applied to constrained properties.
 */
//    	VetoableChangeSupport vcs;
//    	addVetoableChangeListener( pce -> {
//    		System.out.println("PropertyChangeEvent "+pce);
//    	});
    	this.uomNode = uomNode;
    	return this;
	}
	
	private void add(JSONObject o) {
		HashMap<String, Object> jo = o;
		jo.forEach( (k, v) -> {
			System.out.println("key:"+k + " value:"+v 
					+ (v==null ? "" : "<of type "+v.getClass().getSimpleName()+">"));
			JLabel label = new JLabel((String)k);
       		if (v instanceof Integer) {
    			NumberFormat format = NumberFormat.getNumberInstance();
    			JXFormattedTextField field = new JXFormattedTextField(format);
    			field.setValue(v);
    			field.setHorizontalAlignment(SwingConstants.RIGHT);
//    			field.setEditable(false); // read only
    			label.setLabelFor(field);
    			add(label);
    			add(field);
       		} else if (v==null && "uomSymbol".equals(k)) {
       			// bei v==null uomSymbol nicht anzeigen
       		} else {
//    			JXFormattedTextField field = new JXFormattedTextField();
//    			field.setValue(v); // auch bei v==null
    			JXTextField field = new JXTextField();
    			field.setText((String)v); // auch bei v==null
    			label.setLabelFor(field);
    			add(label);
    			add(field);
       		}
		});
    	/* start with disabled component fields.
    	 * Disabling a component does not disable its children
    	 */
		setEnabled(editSelected.isSelected()); // disable component and children
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
					f.addPropertyChangeListener(pce -> {
						Object source = pce.getSource();
//			    		System.out.println("field PropertyChangeEvent "+pce
//			    		+ "\n Source:"+source
//			    		);
			    		if(source instanceof JXTextField tf) {
			    			JLabel labeledBy = (JLabel)tf.getClientProperty("labeledBy");
//				    		System.out.println("field "+labeledBy.getText()+" value="+tf.getText());
				    		HashMap<String, Object> jo = toJSONObject(uomNode);
				    		if(tf.getText().equals(jo.get(labeledBy.getText()))) {
				    			// unverändert
				    		} else {
					    		System.out.println("CHANGED field "+labeledBy.getText()+" value="+tf.getText());
					    		// TODO das elem UoMTreeNode in listModel ermitteln und ändern
				    		}
			    		}
/*
        	LOG.info("amountXField PropertyChangeEvent:"+pce +
        			"\n Source:"+pce.getSource());
        	xamount = ((Number)amountXField.getValue()).doubleValue();
        	Double d = Double.valueOf(computePayment(xamount, xrate, xnumPeriods));
            paymentXField.setValue(d);
            paymentXField.setForeground(d<0 ? Color.red : Color.black);

 */
					});
				}
			}
		}
    }
}
