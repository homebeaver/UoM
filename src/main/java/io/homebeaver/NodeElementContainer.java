package io.homebeaver;

import java.awt.Component;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
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
	public NodeElementContainer(JCheckBox editSelected) {
		super(new GridLayout(0, 2));
        setBorder(new TitledBorder(TITLE));
        this.editSelected = editSelected;
	}
	
	// in (super) public Component Container.add(Component comp)
    //            public Component Container.add(String name, Component comp) 
	//            public Component Container.add(Component comp, int index)
	@Override
	public Component add(UoMTreeNode uomNode) {
    	TreeNode parent = uomNode.getParent();
    	setBorder(new TitledBorder(parent == null ? TITLE : TITLE + " of Type "+uomNode.getParent()));
    	removeAll();
    	
    	if (uomNode instanceof UoMTreeNode.DirectoryTreeNode dtn) {
    		JSONObject jsonUom = dtn.externalize(new JSONObject());
    		add((JSONObject)jsonUom.get(UoMTreeNode.OBJECT));
    	} else if (uomNode instanceof UoMTreeNode.QuantityTreeNode uom) { 
    		JSONObject jsonUom = uom.externalize(new JSONObject());
    		add((JSONObject)jsonUom.get(UoMTreeNode.OBJECT));    		
    	}
    	revalidate();
    	setVisible(true);
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
    			/*
    			 * in nimbus ist das feld read only, aber Background ist unverändert
    			 */
    			field.setEditable(false); // read only
    			label.setLabelFor(field);
    			add(label);
    			add(field);
       		} else if (v==null && "uomSymbol".equals(k)) {
       			// bei v==null uomSymbol nicht anzeigen
       			// doch anzeigen wg Nimbus BG - TODO wieder raus
    			JXTextField field = new JXTextField();
    			field.setEditable(false); // read only
    			label.setLabelFor(field);
    			add(label);
    			add(field);
       		} else {
    			JFormattedTextField field = new JFormattedTextField();
    			field.setValue(v); // auch bei v==null
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
			if(c instanceof JFormattedTextField f) {
				f.setEnabled(enabled);
			}
		}
    }
}
