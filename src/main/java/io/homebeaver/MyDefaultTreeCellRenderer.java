package io.homebeaver;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Objects;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.tree.TreeCellRenderer;

/*
 * Replaces javax.swing.tree.DefaultTreeCellRenderer
 */
@SuppressWarnings("serial") // Same-version serialization only
// ? XXX extends JXLabel with PainterSupport
public class MyDefaultTreeCellRenderer extends JLabel implements TreeCellRenderer {

	private static final Logger LOG = Logger.getLogger(MyDefaultTreeCellRenderer.class.getName());

    /** Last tree the renderer was painted in. */
    private JTree tree;

    /** Is the value currently selected. */
    protected boolean selected;
    /** True if has focus. */
    protected boolean hasFocus;
    /** True if draws focus border around icon as well. */
    private boolean drawsFocusBorderAroundIcon;
    
    /** If true, a dashed line is drawn as the focus indicator. */
    private boolean drawDashedFocusIndicator;
    // If drawDashedFocusIndicator is true, the following are used.
    /**
     * Background color of the tree.
     */
    private Color treeBGColor;
    /**
     * Color to draw the focus indicator in, determined from the background color.
     */
    private Color focusBGColor;

    // Icons
    /** Icon used to show non-leaf nodes that aren't expanded. */
    protected transient Icon closedIcon;

    /** Icon used to show leaf nodes. */
    protected transient Icon leafIcon;

    /** Icon used to show non-leaf nodes that are expanded. */
    protected transient Icon openIcon;

    // Colors
    /** Color to use for the foreground for selected nodes. */
    protected Color textSelectionColor;

    /** Color to use for the foreground for non-selected nodes. */
    protected Color textNonSelectionColor;

    /** Color to use for the background when a node is selected. */
    protected Color backgroundSelectionColor;

    /** Color to use for the background when the node isn't selected. */
    protected Color backgroundNonSelectionColor;

    /** Color to use for the focus indicator when the node has focus. */
    protected Color borderSelectionColor;

    private boolean isDropCell;
    private boolean fillBackground;

    /**
     * Set to true after the constructor has run.
     */
    private boolean inited;

	// implements TreeCellRenderer
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, 
			boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		LOG.info(" tree:"+tree + "\n row="+row + " Object value:"+value + " "+value.getClass());
		String stringValue = tree.convertValueToText(value, sel, expanded, leaf, row, hasFocus);
//		LOG.info(" stringValue:"+stringValue);
		
        this.tree = tree;
        this.hasFocus = hasFocus;
        setText(stringValue);

        Color fg = null;
        isDropCell = false;

        JTree.DropLocation dropLocation = tree.getDropLocation();
        if (dropLocation != null
                && dropLocation.getChildIndex() == -1
                && tree.getRowForPath(dropLocation.getPath()) == row) {

//            Color col = DefaultLookup.getColor(this, ui, "Tree.dropCellForeground");
        	Color col = UIManager.getColor("Tree.dropCellForeground");
            if (col != null) {
                fg = col;
            } else {
                fg = getTextSelectionColor();
            }

            isDropCell = true;
        } else if (sel) {
            fg = getTextSelectionColor();
        } else {
            fg = getTextNonSelectionColor();
        }

        setForeground(fg);

        Icon icon = null;
        if (leaf) {
            icon = getLeafIcon();
        } else if (expanded) {
            icon = getOpenIcon();
        } else {
            icon = getClosedIcon();
        }

        if (!tree.isEnabled()) {
            setEnabled(false);
            LookAndFeel laf = UIManager.getLookAndFeel();
            Icon disabledIcon = laf.getDisabledIcon(tree, icon);
            if (disabledIcon != null) icon = disabledIcon;
            setDisabledIcon(icon);
        } else {
            setEnabled(true);
            setIcon(icon);
        }
        setComponentOrientation(tree.getComponentOrientation());

        selected = sel;

        return this;
	}

    public MyDefaultTreeCellRenderer() {
        inited = true;
//        LOG.info("inited="+inited + " text="+getText() + " name="+getName());
    }

    // TODO in UIManagerExt aufnehmen
    public static boolean getUIManagerExtBoolean(Object key, boolean deflt) {
    	Object value = UIManager.get(key);
    	if (value instanceof Boolean) {
    		return (Boolean) value;
    	}
    	return deflt;
    }
    
    public void updateUI() {
        super.updateUI();
//		ComponentUI ui = UIManager.getUI(this);
//		LOG.info("inited="+inited + " ui="+ui); // ui=javax.swing.plaf.synth.SynthLabelUI
        // To avoid invoking new methods from the constructor, the
        // inited field is first checked. If inited is false, the constructor
        // has not run and there is no point in checking the value. As
        // all look and feels have a non-null value for these properties,
        // a null value means the developer has specifically set it to
        // null. As such, if the value is null, this does not reset the
        // value.
        if (!inited || (getLeafIcon() instanceof UIResource)) {
//            setLeafIcon(DefaultLookup.getIcon(this, ui, "Tree.leafIcon"));
            setLeafIcon(UIManager.getIcon("Tree.leafIcon"));
        }
        if (!inited || (getClosedIcon() instanceof UIResource)) {
//            setClosedIcon(DefaultLookup.getIcon(this, ui, "Tree.closedIcon"));
            setClosedIcon(UIManager.getIcon("Tree.closedIcon"));
        }
        if (!inited || (getOpenIcon() instanceof UIResource)) {
//            setOpenIcon(DefaultLookup.getIcon(this, ui, "Tree.openIcon"));
            setOpenIcon(UIManager.getIcon("Tree.openIcon"));
        }
        if (!inited || (getTextSelectionColor() instanceof UIResource)) {
            setTextSelectionColor(
//                    DefaultLookup.getColor(this, ui, "Tree.selectionForeground")
            		UIManager.getColor("Tree.selectionForeground")
                    );
        }
        if (!inited || (getTextNonSelectionColor() instanceof UIResource)) {
            setTextNonSelectionColor(
//                    DefaultLookup.getColor(this, ui, "Tree.textForeground")
            		UIManager.getColor("Tree.textForeground")
                    );
        }
        if (!inited || (getBackgroundSelectionColor() instanceof UIResource)) {
            setBackgroundSelectionColor(
//                    DefaultLookup.getColor(this, ui, "Tree.selectionBackground")
            		UIManager.getColor("Tree.selectionBackground")
                    );
        }
        if (!inited || (getBackgroundNonSelectionColor() instanceof UIResource)) {
            setBackgroundNonSelectionColor(
//                    DefaultLookup.getColor(this, ui, "Tree.textBackground")
            		UIManager.getColor("Tree.textBackground")
                    );
        }
        if (!inited || (getBorderSelectionColor() instanceof UIResource)) {
            setBorderSelectionColor(
//                    DefaultLookup.getColor(this, ui, "Tree.selectionBorderColor")
            		UIManager.getColor("Tree.selectionBorderColor")
                    );
        }
        drawsFocusBorderAroundIcon = //DefaultLookup.getBoolean(this, ui, "Tree.drawsFocusBorderAroundIcon", false);
/*
 * die doku "throws NullPointerException if key is null" on UIManager.getBoolean stimmt nicht XXX
 *  - es wird false geliefert:
        		UIManager.getBoolean("Tree.drawsFocusBorderAroundIcon");
 * daher implementiere ich es mit default:
 */
        		getUIManagerExtBoolean("Tree.drawsFocusBorderAroundIcon", true);
        drawDashedFocusIndicator = //DefaultLookup.getBoolean(this, ui, "Tree.drawDashedFocusIndicator", false);
        		getUIManagerExtBoolean("Tree.drawDashedFocusIndicator", true);
        
        fillBackground = //DefaultLookup.getBoolean(this, ui, "Tree.rendererFillBackground", true);
        		getUIManagerExtBoolean("Tree.rendererFillBackground", true);

        if (!inited || getBorder() instanceof UIResource)  {
//            Insets margins = DefaultLookup.getInsets(this, ui, "Tree.rendererMargins");
            Insets margins = UIManager.getInsets("Tree.rendererMargins");
            if (margins != null) {
                setBorder(new EmptyBorderUIResource(margins));
            } else {
                setBorder(new EmptyBorderUIResource(0, 0, 0, 0));
            }
        }

        setName("Tree.cellRenderer");
    }
    
    public Icon getDefaultOpenIcon() {
//        return DefaultLookup.getIcon(this, ui, "Tree.openIcon");
        return UIManager.getIcon("Tree.openIcon");
    }

    public Icon getDefaultClosedIcon() {
//        return DefaultLookup.getIcon(this, ui, "Tree.closedIcon");
        return UIManager.getIcon("Tree.closedIcon");
    }

    public Icon getDefaultLeafIcon() {
//        return DefaultLookup.getIcon(this, ui, "Tree.leafIcon");
        return UIManager.getIcon("Tree.leafIcon");
    }

    public void setOpenIcon(Icon newIcon) {
        openIcon = newIcon;
    }
    public Icon getOpenIcon() {
        return openIcon;
    }

    public void setClosedIcon(Icon newIcon) {
        closedIcon = newIcon;
    }
    public Icon getClosedIcon() {
        return closedIcon;
    }

    public void setLeafIcon(Icon newIcon) {
        leafIcon = newIcon;
    }
    public Icon getLeafIcon() {
        return leafIcon;
    }

    public void setTextSelectionColor(Color newColor) {
        textSelectionColor = newColor;
    }
    public Color getTextSelectionColor() {
        return textSelectionColor;
    }

    public void setTextNonSelectionColor(Color newColor) {
        textNonSelectionColor = newColor;
    }
    public Color getTextNonSelectionColor() {
        return textNonSelectionColor;
    }

    public void setBackgroundSelectionColor(Color newColor) {
        backgroundSelectionColor = newColor;
    }
    public Color getBackgroundSelectionColor() {
        return backgroundSelectionColor;
    }

    public void setBackgroundNonSelectionColor(Color newColor) {
        backgroundNonSelectionColor = newColor;
    }
    public Color getBackgroundNonSelectionColor() {
        return backgroundNonSelectionColor;
    }

    public void setBorderSelectionColor(Color newColor) {
        borderSelectionColor = newColor;
    }
    public Color getBorderSelectionColor() {
        return borderSelectionColor;
    }

    public void setFont(Font font) {
        if(font instanceof FontUIResource)
            font = null;
        super.setFont(font);
    }
    public Font getFont() {
        Font font = super.getFont();

        if (font == null && tree != null) {
            // Strive to return a non-null value, otherwise the html support
            // will typically pick up the wrong font in certain situations.
            font = tree.getFont();
        }
        return font;
    }

    /**
     * Subclassed to map <code>ColorUIResource</code>s to null. If
     * <code>color</code> is null, or a <code>ColorUIResource</code>, this
     * has the effect of letting the background color of the JTree show
     * through. On the other hand, if <code>color</code> is non-null, and not
     * a <code>ColorUIResource</code>, the background becomes
     * <code>color</code>.
     */
    public void setBackground(Color color) {
        if(color instanceof ColorUIResource)
            color = null;
        super.setBackground(color);
    }

    // in java.awt.Component
    public Color getBackground() {
    	return super.getBackground();
    }

    public void paint(Graphics g) {
        Color bColor;

        if (isDropCell) {
        	LOG.info("isDropCell selected="+selected);
//            bColor = DefaultLookup.getColor(this, ui, "Tree.dropCellBackground");
            bColor = UIManager.getColor("Tree.dropCellBackground");
            if (bColor == null) {
                bColor = getBackgroundSelectionColor();
            }
        } else if (selected) {
            bColor = getBackgroundSelectionColor();
        } else {
            bColor = getBackgroundNonSelectionColor();
            if (bColor == null) {
                bColor = getBackground();
            }
        }

        int imageOffset = -1;
        if (bColor != null && fillBackground) {
            imageOffset = getLabelStart();
            g.setColor(bColor);
            if(getComponentOrientation().isLeftToRight()) {
                g.fillRect(imageOffset, 0, getWidth() - imageOffset, getHeight());
            } else {
                g.fillRect(0, 0, getWidth() - imageOffset, getHeight());
            }
        }

        if (hasFocus) {
        	LOG.info("hasFocus drawsFocusBorderAroundIcon="+drawsFocusBorderAroundIcon);
        	// erkennt man nur in Metal
            if (drawsFocusBorderAroundIcon) {
                imageOffset = 0;
            }
            else if (imageOffset == -1) {
                imageOffset = getLabelStart();
            }
            if(getComponentOrientation().isLeftToRight()) {
                paintFocus(g, imageOffset, 0, getWidth() - imageOffset, getHeight(), bColor);
            } else {
                paintFocus(g, 0, 0, getWidth() - imageOffset, getHeight(), bColor);
            }
        }
        super.paint(g);
    }

    private void paintFocus(Graphics g, int x, int y, int w, int h, Color notColor) {
        Color       bsColor = getBorderSelectionColor();

        if (bsColor != null && (selected || !drawDashedFocusIndicator)) {
            g.setColor(bsColor);
            g.drawRect(x, y, w - 1, h - 1);
        }
        if (drawDashedFocusIndicator && notColor != null) {
            if (treeBGColor != notColor) {
                treeBGColor = notColor;
                focusBGColor = new Color(~notColor.getRGB());
            }
            g.setColor(focusBGColor);
            BasicGraphicsUtils.drawDashedRect(g, x, y, w, h);
        }
    }

    private int getLabelStart() {
        Icon currentI = getIcon();
        if(currentI != null && getText() != null) {
            return currentI.getIconWidth() + Math.max(0, getIconTextGap() - 1);
        }
        return 0;
    }

    /**
     * Overrides <code>javax.swing.JComponent.getPreferredSize</code> to
     * return slightly wider preferred size value.
     */
    public Dimension getPreferredSize() {
        Dimension        retDimension = super.getPreferredSize();

        if(retDimension != null) {
            retDimension = new Dimension(retDimension.width + 3, retDimension.height);
        }
        return retDimension;
    }

    // Overridden for performance reasons:
    public void validate() {}
    public void invalidate() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
    public void repaint() {}

    /*
     * sun.swing.SwingUtilities2 - package not accessible
     */
	private static boolean isScaleChanged(final String name, final Object oldValue, final Object newValue) {
		if (oldValue == newValue || !"graphicsConfiguration".equals(name)) {
			return false;
		}
		var newGC = (GraphicsConfiguration) oldValue;
		var oldGC = (GraphicsConfiguration) newValue;
		var newTx = newGC != null ? newGC.getDefaultTransform() : null;
		var oldTx = oldGC != null ? oldGC.getDefaultTransform() : null;
		return !Objects.equals(newTx, oldTx);
	}

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        // Strings get interned...
        if (propertyName == "text"
//            || ((SwingUtilities2.isScaleChanged(propertyName, oldValue, newValue)
            || ((isScaleChanged(propertyName, oldValue, newValue)
                    || propertyName == "font" || propertyName == "foreground")
                && oldValue != newValue
                && getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey) != null)) {

            super.firePropertyChange(propertyName, oldValue, newValue);
        }
    }
    public void firePropertyChange(String propertyName, byte oldValue, byte newValue) {}
    public void firePropertyChange(String propertyName, char oldValue, char newValue) {}
    public void firePropertyChange(String propertyName, short oldValue, short newValue) {}
    public void firePropertyChange(String propertyName, int oldValue, int newValue) {}
    public void firePropertyChange(String propertyName, long oldValue, long newValue) {}
    public void firePropertyChange(String propertyName, float oldValue, float newValue) {}
    public void firePropertyChange(String propertyName, double oldValue, double newValue) {}
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}

}
