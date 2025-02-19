package samples;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXFrame.StartPosition;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.icon.JXIcon;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;

import io.homebeaver.GenericTreeNode;
import io.homebeaver.ListTransferHandler;
import io.homebeaver.NodeElementContainer;
import io.homebeaver.icon.KorelleRtrash_svgrepo_com;
import io.homebeaver.icon.KorellerRCircle_icons_power;
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

    protected static List<String> initialLaF = Arrays.asList("Nimbus");
    
    /**
     * 
     * @param args (optional) 
     *  1st : LookAndFeel (Metal, Nimbus, ...), 
     *  2nd : metal theme class name (f.i. javax.swing.plaf.metal.DefaultMetalTheme)
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        if(args.length>0) {
            initialLaF = Arrays.asList(args);
            LOG.info("args: "+initialLaF);
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
        //frame.setLocationByPlatform(true);

    	//Turn off metal's use of bold fonts
    	UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Create and set up the content pane.
        SimpleSplitPane newContentPane = new SimpleSplitPane(frame);
//        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        newContentPane.lafSelector.setSelectedValue(initialLaF.get(0), false); // no scroll
    }

    static final int W = 1; // BORDER width in pixels
    static final String STEEL = "javax.swing.plaf.metal.DefaultMetalTheme";
    static final String OCEAN = "javax.swing.plaf.metal.OceanTheme";
    
    private JXFrame xframe;
    private JXMultiSplitPane msp;
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
//        SetPlafAction action = new SetPlafAction(info.getName(), info.getClassName(), getLaFGroup(), this);
//        JMenuItem mi = new JRadioButtonMenuItem(action);
//        LOG.info(info.getName());
        JMenuItem mi = new JRadioButtonMenuItem(info.getName());
        if(info.getClassName().equals(UIManager.getLookAndFeel().getClass().getName())) {
            mi.setSelected(true);
        }
//        getLaFGroup().add(mi);
        lafMenuGroup.add(mi);
        return mi;
    }

    private JCheckBox editSelected;
    
    private JXButton quit;
    private JXButton remove;

    private Map<String, List<String>> lafInfoMap; // info -> [classname] | [classname,themeclassname]
    private String lastLaFandTheme = null;
    private void setLaFandTheme(String key) {
        if (key != null) lastLaFandTheme = key == null ? initialLaF.get(0) : key;
        if ("Metal".equals(key)) {
            LaFUtils.setLAFandTheme(Arrays.asList("metal", STEEL));
        } else {
            SwingUtilities.invokeLater( () -> {
                LaFUtils.setLAFandTheme(lafInfoMap.get(lastLaFandTheme));
            });
        }
//        lafSelector.setSelectedValue(lastLaFandTheme, false);
    }
    private void setSelectedIndexToCurrentLaF() {
        String currentClassName = UIManager.getLookAndFeel().getClass().getName();
        for (int i = 0; i < lafModel.getSize(); i++) {
            if(currentClassName.contains(lafModel.getElementAt(i))) {
                lafSelector.setSelectedIndex(i); 
                break;
            }
        }
    }
    private JComponent createLafSelector() {
        lafModel = new DefaultListModel<String>();
        lafModel.addAll(lafInfoMap.keySet());
        // autoCreateRowSorter:
        lafSelector = new JXList<String>(lafModel, true);
        lafSelector.setVisibleRowCount(3);
        lafSelector.setLayoutOrientation(JList.VERTICAL_WRAP);
        // setSelectedIndex to current LaF:
        setSelectedIndexToCurrentLaF();
        // default is UNSORTED:
        lafSelector.setSortOrder(SortOrder.DESCENDING);
        lafSelector.addListSelectionListener( listSelectionEvent -> {
            String lafKey = lafSelector.getSelectedValue();
            setLaFandTheme(lafKey); 
        });
        return lafSelector;
    }
  
    public SimpleSplitPane(JXFrame frame) throws HeadlessException {
        super(new BorderLayout());
        super.setPreferredSize(PREFERRED_SIZE);
        xframe = frame;
        
        JMenu plafMenu = createPlafMenu(xframe);
//        if(plafMenu != null) xframe.getJMenuBar().add(plafMenu);

        msp = new JXMultiSplitPane();
        String layoutDef 
        = "(COLUMN " 
        +        "(ROW weight=0.95 " 
        +             "(LEAF name=left.middle weight=0.40) "
        +             "(COLUMN weight=0.60 "
        +                 "(LEAF name=right.top weight=0.1) (LEAF name=editor weight=0.9) "
        +             ") "
        +        ") " 
        +        "(ROW weight=0.05 " 
        +             "(LEAF name=bottom.left weight=0.40) (LEAF name=bottom.middle weight=0.30) (LEAF name=bottom.right weight=0.30) "
        +        ") " 
        +    ")" ;
        MultiSplitLayout.Node modelRoot = MultiSplitLayout.parseModel( layoutDef );
        msp.getMultiSplitLayout().setModel( modelRoot );
        msp.setDividerSize(2);
        
        Box lafSelectorPane = Box.createVerticalBox();
        lafSelectorPane.setBorder(new TitledBorder("Look and Feel Selector"));
//        lafSelectorPane.add(Box.createVerticalGlue());
        lafSelectorPane.add(createLafSelector());
//        lafSelectorPane.add(Box.createVerticalGlue());
        msp.add(lafSelectorPane, "right.top");

//        msp.add( new JButton( "Left Middle" ), "left.middle" );
//        JXPanel listPane = new JXPanel(new BorderLayout());
//        listPane.add(createList(), BorderLayout.CENTER);
        Box listPane = Box.createHorizontalBox();
        listPane.setBorder(new TitledBorder("UoM List"));
        listPane.add(Box.createHorizontalGlue());
        listPane.add(Box.createHorizontalStrut(10));
        listPane.add(createUoMlist());
        listPane.add(Box.createHorizontalStrut(10));
        listPane.add(Box.createHorizontalGlue());
//        listPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        msp.add( listPane, "left.middle" );
        
        remove = new JXButton("Delete UoM (remove selected)", KorelleRtrash_svgrepo_com.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON));
        remove.setMnemonic('d'); // Alt-d
        remove.addActionListener(createRemoveListener());
        msp.add(remove, "bottom.left" );

        editSelected = new JCheckBox("Edit selected UoM");
        editPane = new NodeElementContainer(editSelected, uomList);
        editPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        msp.add( editPane, "editor" );
        
        uomList.setSelectedIndex(0);
        
        editSelected.addActionListener( ae -> {
            getUoMTreeNodeContainer().setEnabled(editSelected.isSelected());
        });
        msp.add(editSelected, "bottom.middle" );
        
        quit = new JXButton("Quit", KorellerRCircle_icons_power.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON));
        quit.setMnemonic('q'); // Alt-q
        quit.addActionListener((ActionListener) EventHandler.create(ActionListener.class, this, "quit"));
        msp.add(quit, "bottom.right" );
        
        // TODO up/down arrow icons

        // ADDING A BORDER TO THE MULTISPLITPANE CAUSES ALL SORTS OF ISSUES 
        msp.setBorder( BorderFactory.createEmptyBorder(W, W, W, W) );

        add( msp, BorderLayout.CENTER );
        
    }

    private JXList<UoMTreeNode> uomList;
    private DefaultListModel<UoMTreeNode> listModel;
    // recursively populate the DefaultListModel
    private void populateListModel(DefaultListModel<UoMTreeNode> listModel, GenericTreeNode<?> gtn) {
        for(int c=0; c<gtn.getChildCount(); c++) {
        	GenericTreeNode<?> tn = (GenericTreeNode<?>)gtn.getChildAt(c);
        	listModel.addElement((UoMTreeNode)tn);
        	if(!tn.isLeaf()) {
        		populateListModel(listModel, tn);
        	}
        }
    }
    private JComponent createUoMlist() {
        listModel = new DefaultListModel<UoMTreeNode>();
        GenericTreeNode<?> root = UoMTreeNode.getUomModelRoot();
        listModel.addElement((UoMTreeNode)root);
        populateListModel(listModel, root);
        
        //Create the list and put it in a scroll pane.
        uomList = new JXList<UoMTreeNode>(listModel);
//        list.setLayoutOrientation(JList.HORIZONTAL_WRAP); // default is VERTICAL
        uomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        uomList.setVisibleRowCount(5);
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
        uomList.setCellRenderer(new DefaultListRenderer<UoMTreeNode>(sv, iv));
        uomList.addListSelectionListener( listSelectionEvent -> {
            UoMTreeNode node = uomList.getSelectedValue();
            getUoMTreeNodeContainer().add(node);
        });
        
        uomList.addMouseListener(
            new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount()==2){
                    Point point = e.getPoint();
                    int i =uomList.locationToIndex(point);
                    if(i>=0) {
                        UoMTreeNode uomNode = uomList.getElementAt(i);
                        URI uri = uomNode.getObject().getURI();
                        if(uri!=null) try {
                            Desktop.getDesktop().browse(uri);
                        } catch (IOException ex) {
                            // TODO Auto-generated catch block
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        
        uomList.setTransferHandler(new ListTransferHandler(uomList));        
        uomList.setDropMode(DropMode.ON_OR_INSERT);
        uomList.setDragEnabled(true);
        
        InputMap inputMap = uomList.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        System.out.println("keys:"+inputMap.keys());
        System.out.println("allKeys:"+inputMap.allKeys());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), ListDeleteAction.REMOVE_SELECTED_ROW);
        ActionMap actionMap = uomList.getActionMap();
        System.out.println("actionMap.keys:"+Arrays.asList(actionMap.keys()));
      //System.out.println("actionMap.allKeys:"+Arrays.asList(actionMap.allKeys()));
      //many actions are defined in org.jdesktop.swingx.plaf.basic.BasicYListUI$Actions
        Arrays.asList(actionMap.allKeys()).forEach( key -> {
        	System.out.println("key:"+key+" -> " + actionMap.get(key));
        });
        actionMap.put(ListDeleteAction.REMOVE_SELECTED_ROW, new ListDeleteAction());

        return new JScrollPane(uomList);
    }

    public void quit() {
        System.exit(0);
    }

    private ActionListener createRemoveListener() {
    	// listenerMethodName is remove
    	return (ActionListener)EventHandler.create(ActionListener.class, this, "remove");
    }
    // remove listenerMethod, visibility is public to be called by EventHandler
    public void remove() {
    	int i = uomList.getSelectedIndex();
    	if(i==-1) return;
    	LOG.info("remove row# "+i);
        DefaultListModel<UoMTreeNode> listModel = (DefaultListModel<UoMTreeNode>)uomList.getModel();
        listModel.remove(i);
    }

    private class ListDeleteAction extends AbstractAction {
        protected static final String REMOVE_SELECTED_ROW = "removeSelectedRow";
		@Override
		public void actionPerformed(ActionEvent ae) {
			remove();		
		}
    }

}
