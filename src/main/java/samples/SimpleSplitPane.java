package samples;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXFrame.StartPosition;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;

import io.homebeaver.NodeElementContainer;
import io.homebeaver.uom.UoM;
import io.homebeaver.uom.UoMTreeNode;
import io.homebeaver.uom.UoMTreeNodeContainer;

public class SimpleSplitPane extends JXPanel {
	
	private static final long serialVersionUID = -833123829892622625L;
	private static final Logger LOG = Logger.getLogger(SimpleSplitPane.class.getName());
	private static final String DESCRIPTION = "SimpleSplitPane";

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
        SimpleSplitPane newContentPane = new SimpleSplitPane(frame);
//        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    static final int W = 1; // BORDER width in pixels
    static final String STEEL = "javax.swing.plaf.metal.DefaultMetalTheme";   
    static final String OCEAN = "javax.swing.plaf.metal.OceanTheme";
    
    private JXFrame xframe;
    private JXMultiSplitPane msp;
    private JXComboBox<String> lafComboSelector; // BUG #1
    private JXList<String> lafSelector;
    private DefaultListModel<String> lafModel;
    private ButtonGroup lafMenuGroup;
    private JPanel editPane;
    UoMTreeNodeContainer getUoMTreeNodeContainer() {
    	return (UoMTreeNodeContainer)editPane;
    }

    protected JMenu createPlafMenu(Window target) {
    	UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
    	lafInfoMap = new Hashtable<String, List<String>>();
        JMenu menu = new JMenu("Set L&F");
        lafMenuGroup = new ButtonGroup(); // wg. mi.setSelected
        for (LookAndFeelInfo info : lafInfo) {
        	if ("Metal".equals(info.getName())) {
            	lafInfoMap.putIfAbsent("Metal STEEL"
            			, Arrays.asList(info.getClassName(), STEEL));
            	lafInfoMap.putIfAbsent("Metal OCEAN"
            			, Arrays.asList(info.getClassName(), OCEAN));
                JMenuItem mi = createLafMenuItem(info);
                lafMenuGroup.add(mi);
                menu.add(mi);
        	} else {
            	lafInfoMap.putIfAbsent(info.getName(), Arrays.asList(info.getClassName()));
                JMenuItem mi = createLafMenuItem(info);
                LOG.fine("JMenuItem mi.Action:"+mi.getAction() + " ClassName="+info.getClassName());
                lafMenuGroup.add(mi);
                if(info.getClassName().equals(UIManager.getLookAndFeel().getClass().getName())) {
                	mi.setSelected(true);
                }
                menu.add(mi);
        	}
        }
        return menu;
    }
    protected JMenuItem createLafMenuItem(UIManager.LookAndFeelInfo info) {
//    	SetPlafAction action = new SetPlafAction(info.getName(), info.getClassName(), getLaFGroup(), this);
//    	JMenuItem mi = new JRadioButtonMenuItem(action);
    	LOG.info(info.getName());
    	JMenuItem mi = new JRadioButtonMenuItem(info.getName());
    	if(info.getClassName().equals(UIManager.getLookAndFeel().getClass().getName())) {
    		mi.setSelected(true);
    	}
//    	getLaFGroup().add(mi);
    	lafMenuGroup.add(mi);
    	return mi;
    }

    private JXButton quit;

	private Map<String, List<String>> lafInfoMap; // info -> [classname] | [classname,themeclassname]
	private String lastLaFandTheme;
	private void setLaFandTheme(String key) {
		if (key != null) lastLaFandTheme = key;
		if ("Metal".equals(key)) {
			LaFUtils.setLAFandTheme(Arrays.asList("metal", STEEL));
		} else {
	        SwingUtilities.invokeLater( () -> {
				LaFUtils.setLAFandTheme(lafInfoMap.get(lastLaFandTheme));
	        });
		}
	}
  
    public SimpleSplitPane(JXFrame frame) throws HeadlessException {
    	super(new BorderLayout());
    	super.setPreferredSize(PREFERRED_SIZE);
    	xframe = frame;
    	
		JMenu plafMenu = createPlafMenu(xframe);
//		if(plafMenu != null) xframe.getJMenuBar().add(plafMenu);

    	lafModel = new DefaultListModel<String>();
    	lafModel.addAll(lafInfoMap.keySet());
    	// autoCreateRowSorter:
    	lafSelector = new JXList<String>(lafModel, true);
    	// setSelectedIndex to current LaF:
    	String currentClassName = UIManager.getLookAndFeel().getClass().getName();
    	for (int i = 0; i < lafModel.getSize(); i++) {
    		if(currentClassName.contains(lafModel.getElementAt(i))) {
    			lafSelector.setSelectedIndex(i); 
    			break;
    		}
    	}
    	// default is UNSORTED:
    	lafSelector.setSortOrder(SortOrder.DESCENDING);
    	lafSelector.addListSelectionListener( listSelectionEvent -> {
        	String lafKey = lafSelector.getSelectedValue();
    		setLaFandTheme(lafKey); 		
    	});
/* buggy:
    	String[] toArray = new String[9];
    	// mit autoCreateRowSorter:
    	//lafSelector = new JXComboBox<String>(lafInfoMap.keySet().toArray(toArray), true);
    	lafSelector = new JXComboBox<String>(lafInfoMap.keySet().toArray(toArray));
    	lafSelector.setSelectedIndex(4);
    	lafSelector.addActionListener( ae -> {
    		Object o = lafSelector.getSelectedItem();
    		String k = (String)o;
    		setLaFandTheme(k);
//    		ae.getSource(); == lafSelector
    		msp.updateUI();
    	});
*/
    	msp = new JXMultiSplitPane();
        String layoutDef 
        = "(COLUMN " 
        +		"(ROW weight=0.8 " 
        + 		"(COLUMN weight=0.25 "
        + 			"(LEAF name=left.top weight=0.5) (LEAF name=left.middle weight=0.5) "
        + 		") "
        + 		"(LEAF name=editor weight=0.75) "
        +		") " 
        +		"(LEAF name=bottom weight=0.2) " 
        +	")" ;
        MultiSplitLayout.Node modelRoot = MultiSplitLayout.parseModel( layoutDef );
        msp.getMultiSplitLayout().setModel( modelRoot );
        msp.setDividerSize(2);
        
//        msp.add( new JButton( "Left Top" ), "left.top" );
//        JXPanel lafSelectorPane = new JXPanel(new GridLayout(1, 0)); //new BorderLayout());
//        lafSelectorPane.add(lafSelector, BorderLayout.CENTER);
        Box lafSelectorPane = Box.createVerticalBox();
        lafSelectorPane.add(Box.createVerticalGlue());
//        lafSelectorPane.add(Box.createVerticalStrut(10));
        lafSelectorPane.add(lafSelector);
//        lafSelectorPane.add(Box.createVerticalStrut(80));
        lafSelectorPane.add(Box.createVerticalGlue());
    	msp.add(lafSelectorPane, "left.top");
    	
//        msp.add( new JButton( "Left Middle" ), "left.middle" );
//        JXPanel listPane = new JXPanel(new BorderLayout());
//        listPane.add(createList(), BorderLayout.CENTER);
        Box listPane = Box.createHorizontalBox();
        listPane.add(Box.createHorizontalGlue());
        listPane.add(Box.createHorizontalStrut(10));
        listPane.add(createList());
        listPane.add(Box.createHorizontalStrut(10));
        listPane.add(Box.createHorizontalGlue());
        listPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        msp.add( listPane, "left.middle" );
        
        editPane = new NodeElementContainer();
        editPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        msp.add( editPane, "editor" );
        
        quit = new JXButton("Quit");
        quit.setMnemonic('q'); // Alt-q
        quit.addActionListener((ActionListener) EventHandler.create(ActionListener.class, this, "quit"));
        msp.add(quit, "bottom" );

        // ADDING A BORDER TO THE MULTISPLITPANE CAUSES ALL SORTS OF ISSUES 
        msp.setBorder( BorderFactory.createEmptyBorder(W, W, W, W) );

        add( msp, BorderLayout.CENTER );
        
    }

    private JXList<UoMTreeNode> list;
    private DefaultListModel<UoMTreeNode> listModel;
    private JComponent createList() {
		UoMTreeNode uom = UoMTreeNode.create(new UoM("Maßeinheit", "https://de.wikipedia.org/wiki/Ma%C3%9Feinheit"), null);
		UoMTreeNode SI = UoMTreeNode.create(new UoM("SI-Basisgrößen", null), null);
		UoMTreeNode len = UoMTreeNode.create(new UoM("Länge", "https://de.wikipedia.org/wiki/L%C3%A4nge_%28Physik%29"), null);
		UoMTreeNode volumen = UoMTreeNode.create(new UoM("Volumen", "https://de.wikipedia.org/wiki/Volumen"), null);
		UoMTreeNode WE = UoMTreeNode.create(new UoM("Masse", "https://de.wikipedia.org/wiki/Masse_(Physik)"), null);
		UoMTreeNode time = UoMTreeNode.create(new UoM("Zeit", "https://de.wikipedia.org/wiki/Zeit"), null);
		UoMTreeNode I = UoMTreeNode.create(new UoM("Elektrische Stromstärke", "https://de.wikipedia.org/wiki/Elektrische_Stromst%C3%A4rke"), null);
		UoMTreeNode ml = UoMTreeNode.create(UoM.create_ml(), null);
		UoMTreeNode L = UoMTreeNode.create(UoM.create_L(), null);
		UoMTreeNode Kg = UoMTreeNode.create(UoM.create_Kg(), null);
		UoMTreeNode mg = UoMTreeNode.create(UoM.create_mg(), null);
		UoMTreeNode t = UoMTreeNode.create(UoM.create_t(), null);
		UoMTreeNode h = UoMTreeNode.create(UoM.create_h(), null);
		UoMTreeNode m = UoMTreeNode.create(UoM.create_m(), null);
		UoMTreeNode A = UoMTreeNode.create(UoM.create_A(), null);
        listModel = new DefaultListModel<UoMTreeNode>();
        listModel.addElement(uom);
        listModel.addElement(SI);
        listModel.addElement(len);
        listModel.addElement(m);
        listModel.addElement(volumen);
        listModel.addElement(ml);
        listModel.addElement(L);
        listModel.addElement(WE);
        listModel.addElement(Kg);
        listModel.addElement(mg);
        listModel.addElement(t);
        listModel.addElement(time);
        listModel.addElement(h);
        listModel.addElement(I);
        listModel.addElement(A);
        //Create the list and put it in a scroll pane.
        list = new JXList<UoMTreeNode>(listModel);
//        list.setLayoutOrientation(JList.HORIZONTAL_WRAP); // default is VERTICAL
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
//        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
//        JScrollPane listScrollPane = new JScrollPane(list);
		IconValue iv = (Object value) -> {
			if (value instanceof UoMTreeNode c) {
				return UoMTreeNode.SI_ICON.getIcon(c);
			}
			return IconValue.NULL_ICON;
		};

        // custom String representation: concat various element fields
        StringValue sv = (Object value) -> {
            if (value instanceof UoMTreeNode c) {
            	return c.toString();
            }
            return StringValues.TO_STRING.getString(value);
        };
        list.setCellRenderer(new DefaultListRenderer<UoMTreeNode>(sv, iv));
        list.addListSelectionListener( listSelectionEvent -> {
        	UoMTreeNode node = list.getSelectedValue();
			LOG.info("listSelectionEvent: list.cellRenderer="+list // ==listSelectionEvent.getSource()
					.getCellRenderer()
					+"\n externalized node="+node.externalize()
					);
			getUoMTreeNodeContainer().add(node);
        });
        return list;
    }

    public void quit() {
        System.exit(0);
    }

}
