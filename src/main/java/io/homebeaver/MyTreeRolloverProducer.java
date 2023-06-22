package io.homebeaver;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JTree;

import org.jdesktop.swingx.rollover.TreeRolloverProducer;

public class MyTreeRolloverProducer extends TreeRolloverProducer {

	public boolean isDragging() {
		return super.isDragging();
	}
	
    @Override
	public void mouseReleased(MouseEvent e) {
//    	System.out.println("mouseReleased rollover="+rollover+" MouseEvent"+e);
    	super.mouseReleased(e);
//        Point oldCell = new Point(rollover); 
//        // JW: fix for #456-swingx - rollover not updated after end of dragging
//        updateRollover(e, ROLLOVER_KEY, false);
//        // Fix Issue 1387-swingx - no click on release-after-drag
//        if (isClick(e, oldCell, isDragging)) {
//            updateRollover(e, CLICKED_KEY, true);
//        }
//        isDragging = false;

	}
    
    @Override
    public void mousePressed(MouseEvent e) {
        JTree tree = (JTree) e.getComponent();
        Point mousePoint = e.getPoint();
        int labelRow = tree.getRowForLocation(mousePoint.x, mousePoint.y);
        // default selection
        if (labelRow >= 0)
            return;
        int row = tree.getClosestRowForLocation(mousePoint.x, mousePoint.y);
        Rectangle bounds = tree.getRowBounds(row);
        if (bounds == null) {
            row = -1;
        } else {
            if ((bounds.y + bounds.height < mousePoint.y)
                    || bounds.x > mousePoint.x) {
                row = -1;
            }
        }
        // no hit
        if (row < 0)
            return;
        System.out.println("dispatch a new  MouseEvent");
        tree.dispatchEvent(new MouseEvent(tree, e.getID(), e.getWhen(), e.getModifiersEx(), 
        		bounds.x + bounds.width - 2, mousePoint.y, e.getClickCount(), e.isPopupTrigger(), e.getButton()));
    }

    @Override
    protected void updateRolloverPoint(JComponent component, Point mousePoint) {
        JTree tree = (JTree) component;
        int row = tree.getClosestRowForLocation(mousePoint.x, mousePoint.y);
        Rectangle bounds = tree.getRowBounds(row);
        if (bounds == null) {
            row = -1;
        } else {
            if ((bounds.y + bounds.height < mousePoint.y)
                    || bounds.x > mousePoint.x) {
                row = -1;
            }
        }
        int col = row < 0 ? -1 : 0;
        rollover.x = col;
        rollover.y = row;
    }

}
