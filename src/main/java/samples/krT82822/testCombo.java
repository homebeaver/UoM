package samples.krT82822;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class testCombo {

	public static void main(String[] args) {
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};

		JFrame f = new JFrame("JCombo");
		f.addWindowListener(l);
		f.getContentPane().add(new BrownCompoundCellTest());
		f.pack();
//		f.show();
        f.setVisible(true);
	}

}
