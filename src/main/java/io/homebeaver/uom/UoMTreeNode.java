package io.homebeaver.uom;

import java.util.Vector;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.icon.JXIcon;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.IconValues;

import io.homebeaver.GenericTreeNode;
import io.homebeaver.icon.KorelleRCircle_icons_clock;
import io.homebeaver.icon.KorelleRCircle_icons_dolly;
import io.homebeaver.icon.KorelleRCircle_icons_plugin;
import io.homebeaver.icon.KorelleRCircle_icons_rulertriangle;
import io.homebeaver.icon.KorelleRMilk_ballonicon2;
import net.sf.fstreem.FileSystemTreeNode;

/**
 * A tree node that is based on UoM.
 */
public abstract class UoMTreeNode extends GenericTreeNode<UoM> {

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
            	}
            	// "Fläche" ist Länge zum Quadrat
            	if("Volumen".equals(dtn.getUoM().name)) { // Volumen ist Länge Hoch 3
            		return KorelleRMilk_ballonicon2.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}
            	if("Masse".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_dolly.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}
            	if("Zeit".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_clock.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
            	}
            	if("Elektrische Stromstärke".equals(dtn.getUoM().name)) {
            		return KorelleRCircle_icons_plugin.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON);
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

}
