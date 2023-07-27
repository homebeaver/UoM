package io.homebeaver;

import java.awt.Component;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.jdesktop.swingx.SwingXUtilities;

// MyTreeCellRenderer ist wie org.jdesktop.swingx.tree.DefaultXTreeCellRenderer 
// javax.swing.tree.DefaultTreeCellRenderer extends JLabel implements TreeCellRenderer
// ist also die "alte" swing api

// lt. kleopatra sollte die "alte" swing api ersetzt werden. durch DelegatingRenderer? - daher deprecated
// public class javax.swing.tree.DefaultTreeRenderer extends AbstractRenderer implements TreeCellRenderer 
//                                          ^
//                                          |
// public class DelegatingRenderer extends DefaultTreeRenderer implements TreeCellRenderer, RolloverRenderer
@Deprecated
public class MyTreeCellRenderer extends DefaultTreeCellRenderer {
	
	private static final Logger LOG = Logger.getLogger(MyTreeCellRenderer.class.getName());

	MyTreeCellRenderer() {
		super(); // super javax.swing.tree.DefaultTreeCellRenderer hat nur den default ctor
		// DefaultTreeCellRenderer extends JLabel und damit wird
		// JLabel(String text, Icon icon, int horizontalAlignment)
		// mit ("", null, LEADING) gerufen
	}

	public Icon getDefaultOpenIcon() {
		// in super: DefaultLookup.getIcon(this, ui, "Tree.openIcon");
//		LOG.info("sun.swing.DefaultLookup is not visible - can use UIManager::::");
		return UIManager.getIcon("Tree.openIcon");
	}
    public Icon getDefaultClosedIcon() {
		Icon icon = UIManager.getIcon("Tree.closedIcon");
		LOG.info("sun.swing.DefaultLookup is not visible - can use UIManager got "+icon);
        return icon;
    }
    public Icon getDefaultLeafIcon() {
		Icon icon = UIManager.getIcon("Tree.leafIcon");
		LOG.info("sun.swing.DefaultLookup is not visible - can use UIManager got "+icon);
        return icon;
    }

    /**
     * {@inheritDoc} <p>
     * 
     * Overridden to update icons and colors.
     */
//    @Override
//    public void updateUI() {
//        super.updateUI();
//        LOG.info("------ do updateIcons() + updateColors()");
//        updateIcons();
//        updateColors();
//    }

    protected void updateColors() {
    	LOG.fine("Colors:"
    			+"\n Tree.background="+UIManager.getColor("Tree.background")
// Tree.background=DerivedColor(color=255,255,255 parent=nimbusLightBackground offsets=0.0,0.0,0.0,0 pColor=255,255,255
    			+"\n Tree.selectionForeground="+UIManager.getColor("Tree.selectionForeground")
    			+"\n Tree.textForeground="+UIManager.getColor("Tree.textForeground")
    			+"\n Tree.selectionBackground="+UIManager.getColor("Tree.selectionBackground")
// Tree.selectionBackground=DerivedColor(color=57,105,138 parent=nimbusSelectionBackground offsets=0.0,0.0,0.0,0 pColor=57,105,138
    			+"\n Tree.textBackground="+UIManager.getColor("Tree.textBackground")
    			+"\n Tree.selectionBorderColor="+UIManager.getColor("Tree.selectionBorderColor")
    			+"\n"
    			);
        if (SwingXUtilities.isUIInstallable(getTextSelectionColor())) {
            setTextSelectionColor(UIManager.getColor("Tree.selectionForeground"));
        }
        if (SwingXUtilities.isUIInstallable(getTextNonSelectionColor())) {
            setTextNonSelectionColor(UIManager.getColor("Tree.textForeground"));
        }
        if (SwingXUtilities.isUIInstallable(getBackgroundSelectionColor())) {
            setBackgroundSelectionColor(UIManager.getColor("Tree.selectionBackground"));
        }
        if (SwingXUtilities.isUIInstallable(getBackgroundNonSelectionColor())) {
            setBackgroundNonSelectionColor(UIManager.getColor("Tree.textBackground"));
        }
        if (SwingXUtilities.isUIInstallable(getBorderSelectionColor())) {
            setBorderSelectionColor(UIManager.getColor("Tree.selectionBorderColor"));
        }
//        Object value = UIManager.get("Tree.drawsFocusBorderAroundIcon");
//        drawsFocusBorderAroundIcon = (value != null && ((Boolean)value).
//                                      booleanValue());
//        value = UIManager.get("Tree.drawDashedFocusIndicator");
//        drawDashedFocusIndicator = (value != null && ((Boolean)value).
//                                    booleanValue());
    }

    /**
     * 
     */
    protected void updateIcons() {
        if (SwingXUtilities.isUIInstallable(getLeafIcon())) {
        	//  the given property "Tree.leafIcon" should be replacedby the UI's default value
        	LOG.info("Tree.leafIcon should be replaced by the UI's default value !!!!!!");
            setLeafIcon(UIManager.getIcon("Tree.leafIcon"));
        }
        if (SwingXUtilities.isUIInstallable(getClosedIcon())) {
            setClosedIcon(UIManager.getIcon("Tree.closedIcon"));
        }
        if (SwingXUtilities.isUIInstallable(getOpenIcon())) {
            setOpenIcon(UIManager.getIcon("Tree.openIcon"));
        }

    }

//    public Icon getDefaultLeafIcon() {
//    	// The type sun.swing.DefaultLookup is not accessible:
//        return sun.swing.DefaultLookup.getIcon(this, ui, "Tree.leafIcon");
//    }

//    private boolean isDropCell; // TODO wird in getTreeCellRendererComponent und paint verwendet

	public Component getTreeCellRendererComponent(JTree tree, Object value, 
			boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		LOG.info(" tree:"+tree + "\n Object value:"+value);
		String stringValue = tree.convertValueToText(value, sel, expanded, leaf, row, hasFocus);
		LOG.info(" stringValue:"+stringValue);
		return super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
//		this.tree = tree;
//		this.hasFocus = hasFocus;
//		setText(stringValue);
//
//		Color fg = null;
//		isDropCell = false;
//
//		JTree.DropLocation dropLocation = tree.getDropLocation();
//		if (dropLocation != null && dropLocation.getChildIndex() == -1
//				&& tree.getRowForPath(dropLocation.getPath()) == row) {
//
//			Color col = DefaultLookup.getColor(this, ui, "Tree.dropCellForeground");
//			if (col != null) {
//				fg = col;
//			} else {
//				fg = getTextSelectionColor();
//			}
//
//			isDropCell = true;
//		} else if (sel) {
//			fg = getTextSelectionColor();
//		} else {
//			fg = getTextNonSelectionColor();
//		}
//
//		setForeground(fg);
//
//		Icon icon = null;
//		if (leaf) {
//			icon = getLeafIcon();
//		} else if (expanded) {
//			icon = getOpenIcon();
//		} else {
//			icon = getClosedIcon();
//		}
//
//		if (!tree.isEnabled()) {
//			setEnabled(false);
//			LookAndFeel laf = UIManager.getLookAndFeel();
//			Icon disabledIcon = laf.getDisabledIcon(tree, icon);
//			if (disabledIcon != null)
//				icon = disabledIcon;
//			setDisabledIcon(icon);
//		} else {
//			setEnabled(true);
//			setIcon(icon);
//		}
//		setComponentOrientation(tree.getComponentOrientation());
//
//		selected = sel;
//
//		return this;
	}

//    private boolean fillBackground; // TODO initialiseren
//    private boolean drawsFocusBorderAroundIcon; // TODO initialiseren
//    private boolean drawDashedFocusIndicator; // TODO initialiseren
//    // If drawDashedFocusIndicator is true, the following are used.
//    private Color treeBGColor;
//    private Color focusBGColor;
//    
//    // code aus super javax.swing.tree.DefaultTreeCellRenderer
//    private int getLabelStart() {
//        Icon currentI = getIcon();
//        if(currentI != null && getText() != null) {
//            return currentI.getIconWidth() + Math.max(0, getIconTextGap() - 1);
//        }
//        return 0;
//    }
//    public void paint(Graphics g) {
//        Color bColor;
//
//        if (isDropCell) {
//            bColor = DefaultLookup.getColor(this, ui, "Tree.dropCellBackground");
//            if (bColor == null) {
//                bColor = getBackgroundSelectionColor();
//            }
//        } else if (selected) {
//            bColor = getBackgroundSelectionColor();
//        } else {
//            bColor = getBackgroundNonSelectionColor();
//            if (bColor == null) {
//                bColor = getBackground();
//            }
//        }
//
//        int imageOffset = -1;
//        if (bColor != null && fillBackground) {
//            imageOffset = getLabelStart();
//            g.setColor(bColor);
//            if(getComponentOrientation().isLeftToRight()) {
//                g.fillRect(imageOffset, 0, getWidth() - imageOffset,
//                           getHeight());
//            } else {
//                g.fillRect(0, 0, getWidth() - imageOffset,
//                           getHeight());
//            }
//        }
//
//        if (hasFocus) {
//            if (drawsFocusBorderAroundIcon) {
//                imageOffset = 0;
//            }
//            else if (imageOffset == -1) {
//                imageOffset = getLabelStart();
//            }
//            if(getComponentOrientation().isLeftToRight()) {
//                paintFocus(g, imageOffset, 0, getWidth() - imageOffset,
//                           getHeight(), bColor);
//            } else {
//                paintFocus(g, 0, 0, getWidth() - imageOffset, getHeight(), bColor);
//            }
//        }
//        super.paint(g); // super.super !!!
//    }
//    private void paintFocus(Graphics g, int x, int y, int w, int h, Color notColor) {
//        Color       bsColor = getBorderSelectionColor();
//
//        if (bsColor != null && (selected || !drawDashedFocusIndicator)) {
//            g.setColor(bsColor);
//            g.drawRect(x, y, w - 1, h - 1);
//        }
//        if (drawDashedFocusIndicator && notColor != null) {
//            if (treeBGColor != notColor) {
//                treeBGColor = notColor;
//                focusBGColor = new Color(~notColor.getRGB());
//            }
//            g.setColor(focusBGColor);
//            BasicGraphicsUtils.drawDashedRect(g, x, y, w, h);
//        }
//    }

}
