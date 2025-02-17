package samples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXFrame.StartPosition;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JYList;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.icon.JXIcon;

import io.homebeaver.GenericTreeModel;
import io.homebeaver.GenericTreeNode;
import io.homebeaver.MyDefaultTreeCellRenderer;
import io.homebeaver.MyTreeCellEditor;
import io.homebeaver.MyXTree;
import io.homebeaver.TreeTransferHandler;
import io.homebeaver.icon.KorelleRtrash_svgrepo_com;
import io.homebeaver.uom.UoMCellEditor;
import io.homebeaver.uom.UoMTreeNode;
import io.homebeaver.uom.UoMTreeNodeContainer;

// TODO search
// I18N
// XXX welche  HighlightPredicate ???
public class SimpleTreeView extends JXPanel {
	
	private static final long serialVersionUID = -833123829892622625L;
	private static final Logger LOG = Logger.getLogger(SimpleTreeView.class.getName());
	private static final String DESCRIPTION = "SimpleTreeView";

	// The preferred size of the demo
    static int PREFERRED_WIDTH = 700;
    static int PREFERRED_HEIGHT = 600;
    public static final Dimension PREFERRED_SIZE = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
	protected static final boolean exitOnClose = true; // used in JXFrame of the demo

	/**
	 * 
	 * @param args (optional) 
	 *  1st : LookAndFeel (metal, nimbus, ...), 
	 *  2nd : metal theme class name (f.i. javax.swing.plaf.metal.DefaultMetalTheme)
	 */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	if(args.length>0) {
    		List<String> a = Arrays.asList(args);
        	LOG.info("args: "+a);
    		LaFUtils.setLAFandTheme(a);
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
//        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private final File fileRoot;
    private GenericTreeNode<?> gtroot;
    
    private TreeModel treeModel;
    private MyXTree tree;

    private JXComboBox<String> treeSelector;
    private TransferHandler transferHandler;

    private JXLabel trashLabel;
    private JList<Object> list; // a trash to drop for nodes to delete
    private GeneratedListModel<?> listModel;
    private JComponent createList() {
        list = new JYList<>();
        list.setName("deleteList");
        //list.setLayoutOrientation(JList.HORIZONTAL_WRAP); // default is VERTICAL
        list.setCellRenderer(new TrashListCellRenderer());
        listModel = new GeneratedListModel<>();
        list.setModel(listModel);
        list.setVisibleRowCount(1);
        //list.setDragEnabled(true);
        list.setDropMode(DropMode.ON);
        list.setTransferHandler(transferHandler);
        list.setVisible(false);
        list.setAlignmentX(0.0f);
        return list;
    }
    
//    private NodeElementContainer nodeElementContainer; // aka editPane
    private JPanel editPane;
//    private UoMTreeNodeContainer createNodeElementContainer() {
//    	editPane = new NodeElementContainer();
//    	editPane.setVisible(false);
//        return (UoMTreeNodeContainer)editPane;
//	}
    private UoMTreeNodeContainer createEditPane() {
        editPane = new UoMCellEditor.UoMComponent();
        editPane.setVisible(false);
        return (UoMTreeNodeContainer)editPane;
    }
    class TrashListCellRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList<?> list, Object value
				, int index, boolean isSelected, boolean cellHasFocus) {
			
			Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			setIcon(KorelleRtrash_svgrepo_com.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON));
			
			return comp;
		}

    }
    class GeneratedListModel<E> extends AbstractListModel<Object> {
        public GeneratedListModel() {
        }

		@Override
		public int getSize() {
			return 1;
		}

		@Override
		public Object getElementAt(int index) {
			return "trash can";
		}

    }
    private JXButton expand;
    private JXButton quit;

    public SimpleTreeView() throws HeadlessException {
    	super(new BorderLayout());
    	super.setPreferredSize(PREFERRED_SIZE);
        File fileSystemRoot = null;
		try {
			fileSystemRoot = new File(".").getCanonicalFile();
			LOG.info("fileSystemRoot.CanonicalPath="+fileSystemRoot.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.fileRoot = fileSystemRoot;
        initGui();

        quit.setMnemonic('q'); // Alt-q
        quit.addActionListener((ActionListener) EventHandler.create(ActionListener.class, this, "quit"));

        expand.setMnemonic('e');
        expand.addActionListener((ActionListener) EventHandler.create(ActionListener.class, this, "expand"));
    }

    private void initGui() {
        treeModel = new GenericTreeModel(fileRoot);
        gtroot = (GenericTreeNode<?>)treeModel.getRoot();
//        tree = new MyXTree(treeModel, createNodeElementContainer());
        tree = new MyXTree(treeModel, createEditPane());
        Highlighter redText = new ColorHighlighter(HighlightPredicate.ROLLOVER_CELL, null, Color.RED);
        tree.addHighlighter(redText);
        tree.setRolloverEnabled(true); // to show the rollover Highlighter
        tree.setOverwriteRendererIcons(true);
        
        transferHandler = new TreeTransferHandler();
        
        quit = new JXButton("Quit");
        expand = new JXButton("Expand");

        Box rightPanel = Box.createVerticalBox();
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(Box.createVerticalStrut(4));
//        createEditPane();
        rightPanel.add(editPane);
        rightPanel.add(Box.createVerticalStrut(4));
        trashLabel = new JXLabel("drop here to delete:");
        trashLabel.setVisible(false);
        rightPanel.add(trashLabel);
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(createList());
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(expand);
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(quit);
        rightPanel.add(Box.createVerticalStrut(4));

        Box centerPanel = Box.createHorizontalBox();
        centerPanel.add(new JScrollPane(tree));
        centerPanel.add(Box.createHorizontalStrut(4));
        centerPanel.add(rightPanel);

    	treeSelector = new JXComboBox<String>(selectorData());
    	add(treeSelector, BorderLayout.NORTH);
    	treeSelector.addActionListener( ae -> {
    		Object o = treeSelector.getSelectedItem();
    		String t = (String)o;
    		selectTree(t);
    		if(UOM.equals(t)) {
    			tree.expandAll();
    		}
    	});
        add(centerPanel, BorderLayout.CENTER);
    }

    public static final String EMPTY = "empty tree";
    public static final String FILESYSTEM = "file root(.)";
    public static final String UOM = "Units of Measure";
    public String[] selectorData() {
    	return new String[] {FILESYSTEM, EMPTY, UOM};
    }

    private void selectTree(String treeName) {
    	if(EMPTY.equals(treeName)) {
    		editPane.setVisible(false);
    		trashLabel.setVisible(false);
    		list.setVisible(false);
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, GenericTreeNode.create(EMPTY));
    		tree.setEditable(false);
    		tree.setDragEnabled(false);
    		tree.updateUI();
    	} else if(FILESYSTEM.equals(treeName)) {
    		editPane.setVisible(false);
    		trashLabel.setVisible(false);
    		list.setVisible(false);
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, gtroot);
    		tree.setEditable(false);
    		tree.setDragEnabled(false);
    		tree.updateUI();
    	} else if(UOM.equals(treeName)) {
//    		editPane.setVisible(true);
//    		editPane.setVisible(false); // true in NodeElementContainer.add
    		list.setVisible(true);
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, UoMTreeNode.getUomModelRoot());
    		tree.addTreeSelectionListener( treeSelectionEvent -> {
    			UoMTreeNode tn = (UoMTreeNode)tree.getLastSelectedPathComponent();
    			LOG.info("treeSelectionEvent: tree.cellEditor="+tree // ==treeSelectionEvent.getSource()
    					.getCellEditor()
    					+"\n LastSelectedPathComponent="+tn.externalize()
    					+"\n NewLeadSelectionPath="+treeSelectionEvent.getNewLeadSelectionPath()+" "+treeSelectionEvent.getNewLeadSelectionPath()
    					);
    			// XXX editPane mit tn.getObject() befüllen
// editPane (UoMCellEditor auf der rechten Seite) ist befüllt, den Code braucht es nicht:		
//    			MyTreeCellEditor realEditor = (MyTreeCellEditor)tree.getCellEditor(); // cellEditor
//    			realEditor.updateUI();
//    			LOG.info("tn.getObject:"+tn.getObject().getClass()+"/"+tn.getObject()
//    			+"\n, realEditor:"+realEditor
//    			+", realEditor.getRenderer:"+realEditor.getRenderer()
//    			+"\n, realEditor.getCellEditorValue:"+realEditor.getCellEditorValue());
    		});
    		tree.setEditable(true);
    		// JTree.setCellEditor(TreeCellEditor cellEditor)
    		// DefaultXTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer, TreeCellEditor editor)
    		// class org.jdesktop.swingx.table.DatePickerCellEditor extends AbstractCellEditor implements TableCellEditor, TreeCellEditor
//    		tree.setCellEditor(new DefaultXTreeCellEditor(tree, null, new DatePickerCellEditor());
    		// default in init:
    		// setCellEditor(new DefaultXTreeCellEditor(this, (DefaultTreeCellRenderer) getWrappedCellRenderer()));
    		LOG.info("----WrappedCellRenderer:"+tree.getWrappedCellRenderer());   		
    		tree.setCellEditor(
    			new MyTreeCellEditor(tree // JTree
    					, (MyDefaultTreeCellRenderer)((MyXTree.MyDelegatingRenderer)tree.getWrappedCellRenderer()).getDelegateRenderer()
    					, new UoMCellEditor(editPane)) // TreeCellEditor mit JPanel
    			);
    		tree.setDragEnabled(true);
    		tree.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    		tree.setTransferHandler(transferHandler);
    		tree.setDropMode(DropMode.ON); // drop mode is only meaningful if this component has a TransferHandler that accepts drops
    		tree.updateUI();
    	}
    	// nur zu Info
    	TreeSelectionListener[] listeners = tree.getTreeSelectionListeners();
    	for(int i=0; i<listeners.length; i++) {
    		System.out.println("listener"+i+":"+listeners[i]);
    	}
    }

    public void expand() {
    	tree.expandAll();
    	this.updateUI();
    }

    public void quit() {
        System.exit(0);
    }

}
