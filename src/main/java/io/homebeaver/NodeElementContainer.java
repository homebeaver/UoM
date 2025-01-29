package io.homebeaver;

import java.awt.Component;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.JXFormattedTextField;
import org.json.simple.JSONObject;

import io.homebeaver.uom.UoMTreeNode;
import io.homebeaver.uom.UoMTreeNodeContainer;

@SuppressWarnings("serial")
public class NodeElementContainer extends JPanel implements UoMTreeNodeContainer {

	static final String TITLE = "Node Element";
	public NodeElementContainer() {
		super(new GridLayout(0, 2));
        setBorder(new TitledBorder(TITLE));
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
    			field.setEditable(false); // read only
    			label.setLabelFor(field);
    			add(label);
    			add(field);
       		} else if (v==null && "uomSymbol".equals(k)) {
       			// bei v==null uomSymbol nicht anzeigen
       		} else {
    			JFormattedTextField field = new JFormattedTextField();
    			field.setValue(v); // auch bei v==null
    			label.setLabelFor(field);
    			add(label);
    			add(field);
       		}
		});
	}
}
