package samples.krT82822;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class BrownListCell extends JPanel {
	String name;

	public String toString() {
		return "name=" + name + super.toString();
	}

	public BrownListCell(String name) {
		super();
		setLayout(new GridLayout(1, 3, 5, 5));
		this.name = name;
		JLabel l = new JLabel(name + ",column1");
		l.setOpaque(true);
		add(l);
		l = new JLabel(name + ",column2");
		l.setOpaque(true);
		add(l);
		l = new JLabel(name + ",column3");
		l.setOpaque(true);
		add(l);
	}
}
