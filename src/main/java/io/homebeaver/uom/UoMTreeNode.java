package io.homebeaver.uom;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.icon.JXIcon;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.IconValues;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.homebeaver.GenericTreeNode;
import io.homebeaver.icon.KorelleRCircle_icons_clock;
import io.homebeaver.icon.KorelleRCircle_icons_dolly;
import io.homebeaver.icon.KorelleRCircle_icons_plugin;
import io.homebeaver.icon.KorelleRCircle_icons_rulertriangle;
import io.homebeaver.icon.KorelleRMilk_ballonicon2;
import io.homebeaver.icon.KorelleRCircle_icons_brightness;
import io.homebeaver.icon.KorelleRCircle_icons_crop;
import io.homebeaver.icon.KorelleRCircle_icons_flame;
import net.sf.fstreem.FileSystemTreeNode;

/**
 * A tree node that is based on UoM.
 */
public class UoMTreeNode extends GenericTreeNode<UoM> {

	public static final String OBJECT = "object";
	public static final String CHILDREN = "children";

	/**
	 * factory to create a demo UoM tree
	 * @return GenericTreeNode root instance
	 */
    public static GenericTreeNode<?> getUomModelRoot() {
		UoMTreeNode uom = UoMTreeNode.create(new UoM("Maßeinheit", "https://de.wikipedia.org/wiki/Ma%C3%9Feinheit"), null);
		UoMTreeNode SI = UoMTreeNode.create(new UoM("SI-Basisgrößen", null), null);
		UoMTreeNode len = UoMTreeNode.create(new UoM("Länge", "https://de.wikipedia.org/wiki/L%C3%A4nge_%28Physik%29"), null);
		UoMTreeNode volumen = UoMTreeNode.create(new UoM("Volumen", "https://de.wikipedia.org/wiki/Volumen"), null);
		UoMTreeNode WE = UoMTreeNode.create(new UoM("Masse", "https://de.wikipedia.org/wiki/Masse_(Physik)"), null);
		UoMTreeNode time = UoMTreeNode.create(new UoM("Zeit", "https://de.wikipedia.org/wiki/Zeit"), null);
		UoMTreeNode I = UoMTreeNode.create(new UoM("Elektrische Stromstärke", "https://de.wikipedia.org/wiki/Elektrische_Stromst%C3%A4rke"), null);
		uom.add(SI);
		SI.add(len);
		SI.add(WE);
		SI.add(time);
		SI.add(I);
		// ...
		UoMTreeNode ml = UoMTreeNode.create(UoM.create_ml(), null);
		UoMTreeNode L = UoMTreeNode.create(UoM.create_L(), null);
		UoMTreeNode Kg = UoMTreeNode.create(UoM.create_Kg(), null);
		UoMTreeNode mg = UoMTreeNode.create(UoM.create_mg(), null);
		UoMTreeNode t = UoMTreeNode.create(UoM.create_t(), null);
		UoMTreeNode h = UoMTreeNode.create(UoM.create_h(), null);
		UoMTreeNode m = UoMTreeNode.create(UoM.create_m(), null);
		UoMTreeNode A = UoMTreeNode.create(UoM.create_A(), null);
		len.add(m);
		len.add(volumen);
		volumen.add(L);
		volumen.add(ml);
		WE.add(Kg);
		WE.add(mg);
		WE.add(t);
		time.add(h);
		I.add(A);

    	return (GenericTreeNode<?>)uom;    	
    }

	/**
	 * factory to create an object from JSON
	 * @param jsonString
	 * @return UoMTreeNode instance
	 */
	public static UoMTreeNode internalize(String jsonString) {
		JSONParser parser = new JSONParser();
		Reader reader = new StringReader(jsonString);
		UoMTreeNode uomTN = null;
		try {
			Object jsonObject = parser.parse(reader); // throws IOException, ParseException
			uomTN = UoMTreeNode.create((JSONObject)jsonObject);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return uomTN;
	}
	
	/**
	 * factory to create an instance from JSON object
	 * @param jsonObject
	 * @return UoMTreeNode instance
	 */
	public static UoMTreeNode create(JSONObject jsonObject) {
		JSONObject uomJson = (JSONObject)jsonObject.get(OBJECT);
		UoM uom = UoM.create(uomJson);
		JSONArray chArrayJson = (JSONArray)jsonObject.get(CHILDREN);
		Vector<TreeNode> childs = new Vector<TreeNode>();
		if(chArrayJson!=null) chArrayJson.forEach( ch -> parseJsonObject( (JSONObject) ch, childs ) );
		return create(uom, childs);
	}
//	private static UoMTreeNode create(JSONObject jsonObject, Vector<TreeNode> childs) {
//		JSONArray chArrayJson = (JSONArray)jsonObject.get(CHILDREN);
//		JSONObject uomJson = (JSONObject)jsonObject.get(OBJECT);
//		UoM uom = UoM.create(uomJson);
//		if(chArrayJson==null) {
//			return create(uom, null);
//		}
//		Iterator<JSONObject> iter = chArrayJson.iterator();
//		if(!iter.hasNext()) {
//			return create(uom, null);
//		}
//		if(childs==null) childs = new Vector<TreeNode>();
//		while (iter.hasNext()) {
//			JSONObject next = iter.next();
//			UoMTreeNode uomTN = UoMTreeNode.create(next, childs);
//			childs.add(create(uomTN, null));
//		}
//		return create(uom, childs);
//	}
	private static void parseJsonObject(JSONObject jsonObject, Vector<TreeNode> childs) {
		JSONObject uomJson = (JSONObject)jsonObject.get(OBJECT);
		JSONArray chArrayJson = (JSONArray)jsonObject.get(CHILDREN);
		if(chArrayJson==null) {
			UoMTreeNode uomTN = UoMTreeNode.create(uomJson);
			childs.add(create(uomTN, null));
		} else {
			// rekursion
			chArrayJson.forEach( ch -> parseJsonObject( (JSONObject) ch, childs ) );
		}
	}
	
	public static UoMTreeNode create(UoM uom, Vector<TreeNode> childs) {
		if (uom.isQuantity()) {
            return new QuantityTreeNode(uom);
        } else {
            return new DirectoryTreeNode(uom, childs);
        }
    }
	public static final class DirectoryTreeNode extends UoMTreeNode {
        public DirectoryTreeNode(UoM uom, Vector<TreeNode> childs) {
            super(uom, childs==null?new Vector<TreeNode>():childs);
        }
	}
    public static final class QuantityTreeNode extends UoMTreeNode {
        public QuantityTreeNode(UoM uom) {
            super(uom);
        }
    }

    /*
     * Icons zu den SI-Basisgrößen - ohne Temperatur , Stoffmenge , Lichtstärke
     * 
     * Die Icons sind aus https://commons.wikimedia.org/wiki/User:Koreller/Icon ,
     * in feather haben ich nichts passendes für Länge,Masse gefunden:
     *    Volumen-box.svg , time-clock.svg , Stromstärke-activity.svg
     */
    @SuppressWarnings("serial")
	public static final IconValue SI_ICON = new IconValue() {

		@Override
		public Icon getIcon(Object value) {
            if (value instanceof FileSystemTreeNode.DirectoryTreeNode) {
            	return UIManager.getIcon("Tree.closedIcon");
            }
            if (value instanceof FileSystemTreeNode.FileTreeNode) {
            	return UIManager.getIcon("Tree.leafIcon");
            }
            if (value instanceof DirectoryTreeNode dtn) {
            	if("Länge".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_rulertriangle.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	} else     	
            	if("Fläche".equals(dtn.getUoM().name)) { // "Fläche" ist Länge zum Quadrat
            		return KorelleRCircle_icons_crop.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	} else     	
            	if("Volumen".equals(dtn.getUoM().name)) { // Volumen ist Länge Hoch 3
            		return KorelleRMilk_ballonicon2.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	} else     	
            	if("Masse".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_dolly.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	} else     	
            	if("Zeit".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_clock.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	} else     	
            	if("Elektrische Stromstärke".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_plugin.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	} else     	
            	if("Temperatur".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_flame.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	} else     	
            	if("Lichtstärke".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_brightness.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}
            	//return FolderIcon.of(JXIcon.SMALL_ICON, JXIcon.SMALL_ICON);
            	// javax.swing.tree.DefaultTreeCellRenderer:
            	//getDefaultClosedIcon();
//            	ComponentUI ui;
//            	return sun.swing.DefaultLookup.getIcon(this, ui, "Tree.closedIcon");
            }
            return IconValues.NONE.getIcon(value);
		}

    };
    public static final IconValue UOM_ICON = new IconValue() {

		@Override
		public Icon getIcon(Object value) {
            if (value instanceof QuantityTreeNode ftn) {
            	UoMTreeNode parent = (UoMTreeNode)ftn.getParent();
            	if(parent==null) return IconValues.NONE.getIcon(value);
            	if("Länge".equals(parent.getUoM().name)) {
            		return KorelleRCircle_icons_rulertriangle.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}
            	if("Volumen".equals(parent.getUoM().name)) {
            		return KorelleRMilk_ballonicon2.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}
            	if("Masse".equals(parent.getUoM().name)) {
            		return KorelleRCircle_icons_dolly.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}
            	if("Zeit".equals(parent.getUoM().name)) {
            		return KorelleRCircle_icons_clock.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}
            	if("Elektrische Stromstärke".equals(parent.getUoM().name)) {
            		return KorelleRCircle_icons_plugin.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}            	
            }

            return IconValues.NONE.getIcon(value);
		}
    	
    };
    
    private UoMTreeNode(UoM uom) {
        super(uom);
    }
    private UoMTreeNode(UoM uom, Vector<TreeNode> ch) {
        super(uom, ch);
    }

    public UoM getUoM() {
        return super.getObject();
    }

    public boolean isQuantity() {
		return getUoM().isQuantity();
    }
    public boolean getAllowsChildren() {
		return !isQuantity();
    }
	public boolean isLeaf() {
		return isQuantity() || children.isEmpty();
	}
	
    public void setParent(UoMTreeNode newParent) {
        parent = newParent;
    }
	public void add(UoMTreeNode newChild) {
        if(newChild != null && newChild.getParent() == this) {
        	// newChild ist bereits child von this
            //insert(newChild, getChildCount() - 1);
        } else {
//            insert(newChild, getChildCount());
        	newChild.setParent(this);
        	children.add(newChild);
        }		
	}

	public JSONObject externalize(JSONObject obj) {
		obj.put(OBJECT, getUoM().externalize(new JSONObject()));
		if(isLeaf()) {
			return obj;
		}
		// non leaf - there are childs, add them to childs array
		JSONArray childs = new JSONArray();
		for (Enumeration<? extends TreeNode> e = children(); e.hasMoreElements();) {
			TreeNode tn = e.nextElement();
			if(tn instanceof UoMTreeNode uomtn) {
				JSONObject cho = new JSONObject();
				cho.put(OBJECT, uomtn.externalize(new JSONObject()));
				childs.add(cho);
			}
		}
		obj.put(CHILDREN, childs);
		return obj;
	}
	// Ergebnis als JSON
	public String externalize() {
		JSONObject obj = externalize(new JSONObject());
		return obj.toJSONString();
	}

}
