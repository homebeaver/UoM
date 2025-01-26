package io.homebeaver;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.JXFormattedTextField;
import org.json.simple.JSONObject;

import io.homebeaver.uom.UoMTreeNode;
import layout.SpringUtilities;

@SuppressWarnings("serial")
public class NodeElementContainer extends JPanel {

	static final String TITLE = "Node Element";
//	SpringLayout springLayout;
	public NodeElementContainer() {
		super(new GridLayout(0, 2)); // TODO ==> SpringLayout()
//		super(new SpringLayout());
        setBorder(new TitledBorder(TITLE));
//    	LayoutManager layout = super.getLayout();
//    	if(layout instanceof SpringLayout spring) {
//    		springLayout = spring;
//    	}
	}
	
	// in (super) public Component Comtainer.add(Component comp)
	public void add(UoMTreeNode uomNode) {
		int rows = -1;
    	TreeNode parent = uomNode.getParent();
    	setBorder(new TitledBorder(parent == null ? TITLE : TITLE + " of Type "+uomNode.getParent()));
    	removeAll();
    	
    	if (uomNode instanceof UoMTreeNode.DirectoryTreeNode dtn) {
    		JSONObject jsonUom = dtn.externalize(new JSONObject());
    		HashMap<String, Object> jo = (JSONObject)jsonUom.get(UoMTreeNode.OBJECT);
        	rows = jo.keySet().size();
        	System.out.println("DirectoryTreeNode = " + jsonUom + " \n " + jo);
        	// Type safety: The method forEach(BiConsumer) belongs to the raw type HashMap. 
        	// References to generic type HashMap<K,V> should be parameterized
        	// Daher oben HashMap<String, Object> jo = (JSONObject)... und nicht JSONObject jo ...
        	jo.forEach( (k, v) -> {
        		System.out.println("key:"+k + " value:"+v);
    			JLabel label = new JLabel((String)k);
           		if (v instanceof Integer iv) {
        			NumberFormat format = NumberFormat.getNumberInstance();
        			JXFormattedTextField field = new JXFormattedTextField(format);
        			field.setValue(v);
        			field.setHorizontalAlignment(SwingConstants.RIGHT);
        			field.setEditable(false); // read only
//        			field.setPreferredSize(...); TODO feld zu hoch!
//        			field.setMaximumSize(new Dimension(200, 40));
        			label.setLabelFor(field);
        			add(label);
        			add(field);
           		} else if (v==null && "uomSymbol".equals(k)) {
           			// bei v==null uomSymbol nicht anzeigen
//           			rows--; // Local variable rows defined in an enclosing scope must be final or effectively final
           		} else {
        			JFormattedTextField field = new JFormattedTextField();
        			field.setValue(v); // auch bei v==null
        			label.setLabelFor(field);
        			add(label);
        			add(field);
           		}
        	});
    	} else if (uomNode instanceof UoMTreeNode.QuantityTreeNode uom) { 
    		JSONObject jsonUom = uom.externalize(new JSONObject());
//        	System.out.println(jsonUom.toJSONString());
        	JSONObject jo = (JSONObject)jsonUom.get(UoMTreeNode.OBJECT);
//        	System.out.println("jo:"+jo + jo.getClass());
        	rows = jo.keySet().size();
        	for (Object o : jo.keySet()) {
        		System.out.println(""+o);
        		if (o instanceof String k) {
        			JLabel label = new JLabel(k);
               		Object v = jo.get(k);
               		if (v instanceof Integer iv) {
            			NumberFormat format = NumberFormat.getNumberInstance();
            			JXFormattedTextField field = new JXFormattedTextField(format);
            			field.setValue(v);
            			field.setHorizontalAlignment(SwingConstants.RIGHT);
            			field.setEditable(false); // read only
            			label.setLabelFor(field);
            			add(label);
            			add(field);
//            			if(springLayout!=null) {
//            				System.out.println("**************putConstraint");
//            				springLayout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
//            				springLayout.putConstraint(SpringLayout.EAST, field, 5, SpringLayout.EAST, this);
//            			}
               		} else {
            			JFormattedTextField field = new JFormattedTextField();
            			field.setValue(v); // auch bei v==null
            			label.setLabelFor(field);
            			add(label);
            			add(field);
//            			if(springLayout!=null) {
//            				springLayout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, this);
//            				springLayout.putConstraint(SpringLayout.EAST, field, 5, SpringLayout.EAST, this);
//            			}
               		}
        		}
        	}
    		
    	}
    	// bei SpringLayout:
    	LayoutManager layout = super.getLayout();
    	if(layout instanceof SpringLayout spring) {
//        	layout.putConstraint(SpringLayout.WEST, label,
//                    5,
//                    SpringLayout.WEST, contentPane);
    		SpringUtilities.makeCompactGrid(this,
    				rows, 2, //rows, cols
    				6, 6,        //initX, initY
    				6, 6);       //xPad, yPad
    	}
    	revalidate();
    	setVisible(true);
	}
}
