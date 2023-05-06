package samples;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

import net.sf.fstreem.FileSystemTreeModel;

public class SimpleTreeView extends JPanel {
	
	private static final long serialVersionUID = -833123829892622625L;
	private static final Logger LOG = Logger.getLogger(SimpleTreeView.class.getName());
	
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	if(args.length>0) { 
        	LOG.info("args: "+args[0]);
    		LaFUtils.setLAF(args[0]);
    	}
        SwingUtilities.invokeLater( () -> {
            createAndShowGUI();
        });
    }

    /**
     * Create the GUI and show it.
     * For thread safety, this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("SimpleTreeView - FileSystemTreeModel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        File fileSystemRoot = null;
		try {
			fileSystemRoot = new File(".").getCanonicalFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SimpleTreeView newContentPane = new SimpleTreeView(fileSystemRoot);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private final File root;

    private JTree tree;
    private JButton quit;
    private JButton refresh;

    public SimpleTreeView(File root) throws HeadlessException {
    	super(new BorderLayout());
        this.root = root;
        initGui();
        refresh();

        refresh.setMnemonic('r');
        refresh.addActionListener((ActionListener) EventHandler.create(
                ActionListener.class,
                this,
                "refresh"));

        quit.setMnemonic('q');
        quit.addActionListener((ActionListener) EventHandler.create(
                ActionListener.class,
                this,
                "quit"));

    }

    private void initGui() {
        tree = new JTree();
        refresh = new JButton("Refresh");
        quit = new JButton("Quit");

        Box rightPanel = Box.createVerticalBox();
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(refresh);
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(quit);
        rightPanel.add(Box.createVerticalStrut(4));

        Box centerPanel = Box.createHorizontalBox();
        centerPanel.add(new JScrollPane(tree));
        centerPanel.add(Box.createHorizontalStrut(4));
        centerPanel.add(rightPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    public void refresh() {
        tree.setModel(new FileSystemTreeModel(root));
    }

    public void quit() {
        System.exit(0);
    }

}
