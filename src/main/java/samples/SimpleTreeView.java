package samples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXFrame.StartPosition;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JYList;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.icon.JXIcon;
import org.jdesktop.swingx.rollover.RolloverProducer;

import io.homebeaver.GenericTreeModel;
import io.homebeaver.GenericTreeNode;
import io.homebeaver.MyDefaultTreeCellRenderer;
import io.homebeaver.MyTreeCellEditor;
import io.homebeaver.MyTreeRolloverProducer;
import io.homebeaver.MyXTree;
import io.homebeaver.TreeTransferHandler;
import io.homebeaver.icon.KorelleRtrash_svgrepo_com;
import io.homebeaver.uom.UoM;
import io.homebeaver.uom.UoMCellEditor;
import io.homebeaver.uom.UoMTreeNode;

// TODO search
// I18N
// XXX welche  HighlightPredicate ???
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
    private MyXTree tree;

    private JXComboBox<String> treeSelector;
    private TransferHandler transferHandler;
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
        return list;
    }
    private JPanel editPane;
    private JComponent createEditPane() {
    	editPane = new UoMCellEditor.UoMComponent(new SpringLayout());
    	return editPane;
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
			return "drop here to delete";
		}

    }
    private JXButton expand;
    private JXButton quit;

    public SimpleTreeView() throws HeadlessException {
    	super(new BorderLayout());
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
        tree = new MyXTree(treeModel);
        Highlighter redText = new ColorHighlighter(HighlightPredicate.ROLLOVER_CELL, null, Color.RED);
        tree.addHighlighter(redText);
        tree.setRolloverEnabled(true); // to show the rollover Highlighter
        tree.setOverwriteRendererIcons(true);
//        tree.setCellRenderer(tree.getCellRenderer()); // nicht notwendig!
        
        /*
         * addPropertyChangeListener(String propertyName, PropertyChangeListener listener) is defined in
         * java.awt.Component and called in java.awt.Container
         */
//        tree.addPropertyChangeListener(RolloverProducer.CLICKED_KEY, propertyChangeEvent -> {
//        	// Code for PropertyChange: auch für FILESYSTEM tree!!!
//        	MyXTree source = (MyXTree)propertyChangeEvent.getSource();
//        	if(!source.getDragEnabled()) return; // because I set DragEnabled only for UOM
//        	
//        	
//        	Point newPoint = (Point)propertyChangeEvent.getNewValue();
//        	if(newPoint!=null && newPoint.y>-1) {
//        		TreePath treePath = source.getPathForRow(newPoint.y);
//        		MyTreeRolloverProducer mtrop = (MyTreeRolloverProducer)source.getRolloverProducer();
//        		LOG.info("CLICKED_KEY isDragging="+source.getDragEnabled()+mtrop.isDragging()
//        		+" >>>>>>>>>>>> tree newPoint:"+newPoint + " TreePath:"+treePath);
//            	if(treePath.getLastPathComponent() instanceof UoMTreeNode uomTN) {
//                	LOG.info("TODO editor for uomTN:"+uomTN);
//            	};
//        	}
//        });
//        tree.addPropertyChangeListener(RolloverProducer.ROLLOVER_KEY, propertyChangeEvent -> {
//        	// Code for propertyChange:
//        	MyXTree source = (MyXTree)propertyChangeEvent.getSource();
//        	if(!source.getDragEnabled()) return; // because I set DragEnabled only for UOM
//        	
//        	// es gibt zwei flags tree.getDragEnabled() und isDragging in RolloverProducer
//        	Point newPoint = (Point)propertyChangeEvent.getNewValue();
//        	if(newPoint!=null && newPoint.y>-1) {
//        		TreePath treePath = source.getPathForRow(newPoint.y);
//        		MyTreeRolloverProducer mtrop = (MyTreeRolloverProducer)source.getRolloverProducer();
//        		// mtrop.isDragging() ist zwar true, aber der Listener kommt erst dran nach TreeTransferHandler cleanup
//            	LOG.info("ROLLOVER isDragging="+source.getDragEnabled()+mtrop.isDragging()
//            		+" >>>>>>>>>>>> tree newPoint:"+newPoint + " TreePath:"+treePath);
//        	}
//        });
        
        transferHandler = new TreeTransferHandler();
        
        quit = new JXButton("Quit");
        expand = new JXButton("Expand");

        Box rightPanel = Box.createVerticalBox();
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(createEditPane());
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
    		list.setVisible(false);
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, GenericTreeNode.create(EMPTY));
    		tree.setEditable(false);
    		tree.setDragEnabled(false);
    		tree.updateUI();
    	} else if(FILESYSTEM.equals(treeName)) {
    		list.setVisible(false);
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, gtroot);
    		tree.setEditable(false);
    		tree.setDragEnabled(false);
    		tree.updateUI();
    	} else if(UOM.equals(treeName)) {
    		list.setVisible(true);
    		TreePath tp = new TreePath(new Object[] {treeModel.getRoot()});
    		treeModel.valueForPathChanged(tp, getUomModelRoot());
    		tree.addTreeSelectionListener( treeSelectionEvent -> {
    			UoMTreeNode tn = (UoMTreeNode)tree.getLastSelectedPathComponent();
    			LOG.info("???????? event:"+tree // ==treeSelectionEvent.getSource()
    					.getCellEditor()
    					+"\n"+tn.externalize()+"??????????"+treeSelectionEvent.getNewLeadSelectionPath()+" "+treeSelectionEvent.getNewLeadSelectionPath());
    			// TODO editPane mit tn.getObject() befüllen
    			MyTreeCellEditor realEditor = (MyTreeCellEditor)tree.getCellEditor(); // cellEditor
    			realEditor.updateUI();
    			//tree.getWrappedCellRenderer();
//    			realEditor.getCellEditorValue();
//    			tn.getObject();
    			LOG.info("tn.getObject:"+tn.getObject().getClass()+"/"+tn.getObject()
    			+", realEditor.getRenderer:"+realEditor.getRenderer()
    			+"\n, realEditor.getCellEditorValue:"+realEditor.getCellEditorValue());
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
    			new MyTreeCellEditor(tree, (MyDefaultTreeCellRenderer)((MyXTree.MyDelegatingRenderer)tree.getWrappedCellRenderer()).getDelegateRenderer()
    					, new UoMCellEditor(editPane))
    			);
    		tree.setDragEnabled(true);
    		tree.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    		tree.setTransferHandler(transferHandler);
    		tree.setDropMode(DropMode.ON); // drop mode is only meaningful if this component has a TransferHandler that accepts drops
    		tree.updateUI();
    	}
    	TreeSelectionListener[] listeners = tree.getTreeSelectionListeners();
    	for(int i=0; i<listeners.length; i++) {
    		System.out.println("listener"+i+":"+listeners[i]);
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
    
    public void expand() {
    	tree.expandAll();
    }

    public void quit() {
        System.exit(0);
    }

}
