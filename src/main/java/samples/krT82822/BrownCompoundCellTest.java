package samples.krT82822;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
class BrownCompoundCellTest extends Container {
	
	static String[] data = { "Elem A", "Elem B", "Elem C" };

	BrownCompoundCellTest() {
		JComboBox<String> combo = new JComboBox<>(data);
		BrownCellRenderer rend = new BrownCellRenderer();

		for (int i = 0; i < data.length; i++) {
			Container lab = new BrownListCell(data[i]);
			rend.addCell(data[i], lab);
		}
		combo.setBackground(Color.lightGray);
		combo.setRenderer(rend);
//		combo.setEditable(true); // ?
		setLayout(new BorderLayout());
		add(combo, BorderLayout.CENTER);
		
		combo.setSelectedIndex(0);
	}
}
