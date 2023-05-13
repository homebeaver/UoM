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
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXFrame.StartPosition;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXTree;

import io.homebeaver.GenericTreeModel;
import io.homebeaver.GenericTreeNode;
import io.homebeaver.uom.UoM;
import io.homebeaver.uom.UoMTreeNode;

public class SimpleTreeView extends JXPanel {
	
	private static final long serialVersionUID = -833123829892622625L;
	private static final Logger LOG = Logger.getLogger(SimpleTreeView.class.getName());
	private static final String DESCRIPTION = "SimpleTreeView";

	protected static final boolean exitOnClose = true; // used in JXFrame of the demo

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
        JXFrame frame = new JXFrame(DESCRIPTION, exitOnClose);
        frame.setStartPosition(StartPosition.CenterInScreen);

        //Create and set up the content pane.
        SimpleTreeView newContentPane = new SimpleTreeView();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private final File fileRoot;
    private GenericTreeNode<?> gtroot;
    
    private TreeModel treeModel;
    private JXTree tree;

    private JXComboBox treeSelector; // name wie in VTreeMaintenance
    private JButton quit;

    public SimpleTreeView() throws HeadlessException {
    	super(new BorderLayout());
        File fileSystemRoot = null;
		try {
			fileSystemRoot = new File(".").getCanonicalFile();
			LOG.info("fileSystemRoot.CanonicalPath="+fileSystemRoot.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.fileRoot = fileSystemRoot;
        initGui();

        quit.setMnemonic('q'); // Alt-q
        quit.addActionListener((ActionListener) EventHandler.create(
                ActionListener.class,
                this,
                "quit"));

    }

    private void initGui() {
        treeModel = new GenericTreeModel(fileRoot);
        gtroot = (GenericTreeNode<?>)treeModel.getRoot();
        tree = new JXTree(treeModel);
        quit = new JButton("Quit");

        Box rightPanel = Box.createVerticalBox();
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(quit);
        rightPanel.add(Box.createVerticalStrut(4));

        Box centerPanel = Box.createHorizontalBox();
        centerPanel.add(new JScrollPane(tree));
        centerPanel.add(Box.createHorizontalStrut(4));
        centerPanel.add(rightPanel);

    	treeSelector = new JXComboBox(selectorData());
    	add(treeSelector, BorderLayout.NORTH);
    	treeSelector.addActionListener( ae -> {
    		Object o = treeSelector.getSelectedItem();
    		String t = (String)o;
    		selectTree(t);
    	});
        add(centerPanel, BorderLayout.CENTER);
    }

    public static final String EMPTY = "empty tree";
    public static final String FILESYSTEM = "file root(.)";
    public static final String UOM = "Units of Measure";
    public String[] selectorData() {
    	return new String[] {EMPTY, FILESYSTEM, UOM};
    }

    private void selectTree(String treeName) {
    	if(EMPTY.equals(treeName)) {
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, GenericTreeNode.create(EMPTY));
    		tree.updateUI();
    	} else if(FILESYSTEM.equals(treeName)) {
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, gtroot);
    		tree.updateUI();
    	} else if(UOM.equals(treeName)) {
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, getUomModelRoot());
    		tree.updateUI();
    	}
    }

    private TreeModel uomModel; 
    private GenericTreeNode<?> getUomModelRoot() {
    	if(uomModel==null) {
    		UoMTreeNode uom = UoMTreeNode.create(new UoM("Maßeinheit", "https://de.wikipedia.org/wiki/Ma%C3%9Feinheit"), null);
    		UoMTreeNode SI = UoMTreeNode.create(new UoM("SI-Basisgrößen", null), null);
    		UoMTreeNode len = UoMTreeNode.create(new UoM("Länge", "https://de.wikipedia.org/wiki/L%C3%A4nge_%28Physik%29"), null);
    		UoMTreeNode volumen = UoMTreeNode.create(new UoM("Volumen", "https://de.wikipedia.org/wiki/Volumen"), null);
    		UoMTreeNode WE = UoMTreeNode.create(new UoM("Masse", "https://de.wikipedia.org/wiki/Masse_(Physik)"), null);
    		UoMTreeNode time = UoMTreeNode.create(new UoM("Zeit", "https://de.wikipedia.org/wiki/Zeit"), null);
    		UoMTreeNode I = UoMTreeNode.create(new UoM("Elektrische Stromstärke", "https://de.wikipedia.org/wiki/Elektrische_Stromst%C3%A4rke"), null);
    		uom.add(SI);
    		SI.add(len);
    		SI.add(WE);
    		SI.add(time);
    		SI.add(I);
    		// ...
    		UoMTreeNode ml = UoMTreeNode.create(UoM.create_ml(), null);
    		UoMTreeNode L = UoMTreeNode.create(UoM.create_L(), null);
    		UoMTreeNode Kg = UoMTreeNode.create(UoM.create_Kg(), null);
    		UoMTreeNode mg = UoMTreeNode.create(UoM.create_mg(), null);
    		UoMTreeNode t = UoMTreeNode.create(UoM.create_t(), null);
    		UoMTreeNode h = UoMTreeNode.create(UoM.create_h(), null);
    		UoMTreeNode m = UoMTreeNode.create(UoM.create_m(), null);
    		UoMTreeNode A = UoMTreeNode.create(UoM.create_A(), null);
    		len.add(m);
    		len.add(volumen);
    		volumen.add(L);
    		volumen.add(ml);
    		WE.add(Kg);
    		WE.add(mg);
    		WE.add(t);
    		time.add(h);
    		I.add(A);
    		uomModel = new GenericTreeModel(uom);
    	}
    	return (GenericTreeNode<?>)uomModel.getRoot();    	
    }
    
    public void quit() {
        System.exit(0);
    }

}
