package samples.krT82822;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.Hashtable;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

class BrownCellRenderer implements ListCellRenderer<Object> {
	
	Hashtable<Object, Component> comps = new Hashtable<>(10);

	void addCell(Object key, Component val) {
		comps.put(key, val);
	}

	public Component getListCellRendererComponent(JList<?> l, Object k, int index, boolean isSelected, boolean hasFocus) {
		Container c;
		if ((c = (Container) comps.get(k)) == null) {
			return null;
		}
		for (int i = 0; i < c.getComponentCount(); i++) {
			Component child = c.getComponent(i);
			if (isSelected) {
				child.setForeground(Color.red);
				child.setBackground(Color.blue);
			} else {
				child.setForeground(Color.black);
				child.setBackground(Color.white);
			}
		}
		return c;
	}
}
