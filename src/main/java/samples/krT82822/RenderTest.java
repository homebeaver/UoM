package samples.krT82822;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class RenderTest extends JFrame {
    
    public static String[] data = {"one", "two", "three", "four"};
    
    public RenderTest() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
        
        JComboBox combo1 = new JComboBox(data);
        
        Container container = getContentPane();
        container.add(combo1, BorderLayout.NORTH);
        
        // This doesn't select the first item in the list.
        JComboBox combo2 = new JComboBox(data);
        combo2.setRenderer(new ComboRenderer());
        container.add(combo2, BorderLayout.CENTER);

        JComboBox combo3 = new JComboBox(data);
        combo3.setRenderer(new ComboRenderer2());
        container.add(combo3, BorderLayout.SOUTH);
        
        // XXX - debug
  /* System.out.println("Selected Indexes: ");
        System.out.println("combo1 - " + combo1.getSelectedIndex());
        System.out.println("combo2 - " + combo1.getSelectedIndex());
        System.out.println("combo3 - " + combo1.getSelectedIndex());
    */

    }
    
    // this is just like the basic combo cell renderer and works correctly.
    private class ComboRenderer2 extends JLabel implements ListCellRenderer {

        public ComboRenderer2() {
            super();
            setOpaque(true);
            setBorder(new EmptyBorder(1, 1, 1, 1));
        }
    
        public Dimension getPreferredSize() {
            Dimension size;
        
            if ((this.getText() == null) || (this.getText().equals( "" ))) {
                setText( " " );
                size = super.getPreferredSize();
                setText( "" );
            } else {
                size = super.getPreferredSize();
            }
        
            return size;
        }

        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                     boolean isSelected,
                                                     boolean cellHasFocus) {
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            setFont(list.getFont());

            if (value instanceof Icon) {
                setIcon((Icon)value);
            } else {
                setText((value == null) ? "" : value.toString());
            }
            return this;
        }
    }
    
    private class ComboRenderer extends JPanel implements ListCellRenderer {
        private JLabel label;
        
        public ComboRenderer() {
            super();
            setOpaque(true);
            setBorder(new EmptyBorder(1, 1, 1, 1));

            label = new JLabel();
            add(label);
        }
    
        public Dimension getPreferredSize() {
            Dimension size;
        
            if ((label.getText() == null) || (label.getText().equals( "" ))) {
                label.setText( " " );
                size = super.getPreferredSize();
                label.setText( "" );
            } else {
                size = super.getPreferredSize();
            }
        
            return size;
        }

        public Component getListCellRendererComponent(JList l, Object obj, int index,
                                            boolean isSelected, boolean hasFocus) {
            
            // XXX - debug
// System.out.println("getListCellRenderer for: " + obj.toString());
            
            if (isSelected) {
                label.setForeground(Color.red);
                label.setBackground(Color.blue);
            } else {
                label.setForeground(Color.black);
                label.setBackground(Color.white);
            }

            label.setText((obj == null) ? "" : obj.toString());

            return this;
        }
    }
   
    public static void main(String[] args) {
        RenderTest test = new RenderTest();
        test.pack();
        test.setVisible(true);
    }
}
