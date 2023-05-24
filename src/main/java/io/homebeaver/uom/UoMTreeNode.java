package io.homebeaver.uom;

import java.util.Vector;

import javax.swing.Icon;
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

/**
 * A tree node that is based on UoM.
 */
public abstract class UoMTreeNode extends GenericTreeNode<UoM> {

	public static UoMTreeNode create(UoM uom, Vector<TreeNode> childs) {
		if (uom.isQuantity()) {
            return new FileTreeNode(uom);
        } else {
            return new DirectoryTreeNode(uom, childs);
        }
    }
	public static final class DirectoryTreeNode extends UoMTreeNode {
        public DirectoryTreeNode(UoM uom, Vector<TreeNode> childs) {
            super(uom, childs==null?new Vector<TreeNode>():childs);
        }
	}
    // rename to ADUoM oder so
    public static final class FileTreeNode extends UoMTreeNode {
        public FileTreeNode(UoM uom) {
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
            }
            return IconValues.NONE.getIcon(value);
		}

    };
    public static final IconValue UOM_ICON = new IconValue() {

		@Override
		public Icon getIcon(Object value) {
            if (value instanceof FileTreeNode ftn) {
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
/*
    protected Vector<Object> uoms;

    public static UoMTreeNode create(UoM uom, Vector<Object> filters) {
    	if (uom.isQuantity()) {
            return new FileTreeNode(uom);
        } else {
            return new DirectoryTreeNode(uom, filters);
        }
    }
    public static UoMTreeNode create(UoM uom) {
        Vector<Object> filters = new Vector<Object>();

    	if (uom.isQuantity()) {
            return new FileTreeNode(uom);
        } else {
            return new DirectoryTreeNode(uom, filters);
        }
    }
    
//    private UoMTreeNode(UoM uom) {
//        this(uom, new Vector<Object>());
//    }
//    private UoMTreeNode(UoM uom, Vector<Object> filters) {
//        this.uom = uom;
//        this.uoms = filters;
//    }

    public abstract int getChildCount(); // throws NotAFolderException;
    public abstract UoMTreeNode getChildAt(int index);

    // rename to ADUoM oder so
    private static final class FileTreeNode extends UoMTreeNode {
        public FileTreeNode(UoM uom) {
            super(uom);
        }

        public boolean isQuantity() {
        	// ==getUoM().isQuantity();
            return true;
        }

        public UoMTreeNode getChildAt(int index) {
//            throw new NotAFolderException(location);
        	return null;
        }

        public int getChildCount() {
        	return 0;
        }
    }

    private static final class DirectoryTreeNode extends UoMTreeNode {
        //private File[] children;

        private Vector<UoM> children;
        
        public DirectoryTreeNode(UoM uom, Vector<Object> filters) {
            super(uom, filters);
        }

        public boolean isQuantity() {
        	// ==getUoM().isQuantity();
            return false;
        }
		@Override
		public int getChildCount() {
            loadChildren();
            if (children != null) {
            	return children.size();	
            } else {
            	return 0;
            }
		}

		@Override
		public UoMTreeNode getChildAt(int index) {
            loadChildren();
            return UoMTreeNode.create(children.elementAt(index), uoms);
		}

        private synchronized void loadChildren() {
        	// TODO
        }
    }
 */
}
