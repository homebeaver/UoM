package samples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXFrame.StartPosition;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.icon.JXIcon;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import org.jdesktop.swingx.renderer.IconValue;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;

import io.homebeaver.GenericTreeNode;
import io.homebeaver.NodeElementContainer;
import io.homebeaver.icon.KorelleRCircle_icons_arrow_down;
import io.homebeaver.icon.KorelleRCircle_icons_arrow_up;
import io.homebeaver.icon.KorelleRCircle_icons_power;
import io.homebeaver.icon.KorelleRtrash_svgrepo_com;
import io.homebeaver.uom.UoMTreeNode;
import io.homebeaver.uom.UoMTreeNodeContainer;

public class SimpleComboBoxPane extends JXPanel {
    
    private static final long serialVersionUID = -833123829892622625L;
    private static final Logger LOG = Logger.getLogger(SimpleComboBoxPane.class.getName());
    private static final String DESCRIPTION = "SimpleComboBoxPane";

    // The preferred size of the demo
    static int PREFERRED_WIDTH = 700;
    static int PREFERRED_HEIGHT = 300;
    public static final Dimension PREFERRED_SIZE = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    protected static final boolean exitOnClose = true; // used in JXFrame of the demo

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
        //frame.setLocationByPlatform(true);

    	//Turn off metal's use of bold fonts
    	UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Create and set up the content pane.
        SimpleComboBoxPane newContentPane = new SimpleComboBoxPane(frame);
//        newContentPane.setOpaque(true); //content panes must be opaque ?
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    static final int W = 1; // BORDER width in pixels
    
    private JXFrame xframe;
    private JXMultiSplitPane msp;
    private JXList<String> lafSelector;
    private DefaultListModel<String> lafModel;
    private JPanel editPane;
    
    UoMTreeNodeContainer getUoMTreeNodeContainer() {
        return (UoMTreeNodeContainer)editPane;
    }

    private JCheckBox editSelected;
    
    private JXButton quit;
    private JXButton remove;

    private JComponent createLafList() {
        lafModel = new DefaultListModel<String>();
        lafModel.addAll(LaFUtils.getLAFandThemeKeySet());
        // autoCreateRowSorter:
        lafSelector = new JXList<String>(lafModel, true);
        lafSelector.setVisibleRowCount(3);
        lafSelector.setLayoutOrientation(JList.VERTICAL_WRAP);
        // setSelectedIndex to current LaF:
//        LOG.info(">>>>>>>>>>>>>"+LaFUtils.getCurrentLAFandThemeString());
        lafSelector.setSelectedValue(LaFUtils.getCurrentLAFandThemeString(), false);
        // default is UNSORTED:
        lafSelector.setSortOrder(SortOrder.DESCENDING);
        lafSelector.addListSelectionListener( listSelectionEvent -> {
            String lafKey = lafSelector.getSelectedValue();
            LaFUtils.setLAFandTheme(lafKey);
        });
        return lafSelector;
    }
  
    public SimpleComboBoxPane(JXFrame frame) throws HeadlessException {
        super(new BorderLayout());
        super.setPreferredSize(PREFERRED_SIZE);
        xframe = frame;
        
        msp = new JXMultiSplitPane();
        String layoutDef 
        = "(COLUMN " 
        +        "(ROW weight=0.95 " 
        +             "(COLUMN weight=0.25 "
        +                 "(LEAF name=left.top weight=0.1) (LEAF name=left.middle weight=0.9) "
        +             ") "
        +             "(LEAF name=editor weight=0.75) "
        +        ") " 
//        +        "(LEAF name=bottom weight=0.2) " 
        +        "(ROW weight=0.05 " 
        +                 "(LEAF name=bottom.left weight=0.40) (LEAF name=bottom.middle weight=0.30) (LEAF name=bottom.right weight=0.30) "
        +        ") " 
        +    ")" ;
        MultiSplitLayout.Node modelRoot = MultiSplitLayout.parseModel( layoutDef );
        msp.getMultiSplitLayout().setModel( modelRoot );
        msp.setDividerSize(2);

        Box lafSelectorPane = Box.createVerticalBox();
        lafSelectorPane.setBorder(new TitledBorder("Look and Feel Selector"));
//        lafSelectorPane.add(Box.createVerticalGlue());
        lafSelectorPane.add(createLafList());
//        lafSelectorPane.add(Box.createVerticalGlue());
        msp.add(lafSelectorPane, "left.top");

//        msp.add( new JButton( "Left Middle" ), "left.middle" );
//        JXPanel listPane = new JXPanel(new BorderLayout());
//        listPane.add(createList(), BorderLayout.CENTER);
        Box listPane = Box.createVerticalBox();
        listPane.add(Box.createVerticalGlue());
//        listPane.add(Box.createHorizontalGlue());
//        listPane.add(Box.createHorizontalStrut(10));
        listPane.add(createComboBox());
//        listPane.add(Box.createHorizontalStrut(10));
//        listPane.add(Box.createHorizontalGlue());
        listPane.add(Box.createVerticalGlue());
        listPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        msp.add( listPane, "left.middle" );

        remove = new JXButton("Delete UoM (remove selected)", KorelleRtrash_svgrepo_com.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON));
        remove.setMnemonic('d'); // Alt-d
//        remove.addActionListener(createRemoveListener()); TODO
        msp.add(remove, "bottom.left" );

        editSelected = new JCheckBox("Edit selected UoM");
        editPane = new NodeElementContainer(editSelected, uomComboBox); //TODO
        editPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        msp.add( editPane, "editor" );

        editSelected.addActionListener( ae -> {
            getUoMTreeNodeContainer().setEnabled(editSelected.isSelected());
        });
        msp.add(editSelected, "bottom.middle" );

        quit = new JXButton("Quit", KorelleRCircle_icons_power.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON));
        quit.setMnemonic('q'); // Alt-q
        quit.addActionListener((ActionListener) EventHandler.create(ActionListener.class, this, "quit"));
        msp.add(quit, "bottom.right" );
        
        // ADDING A BORDER TO THE MULTISPLITPANE CAUSES ALL SORTS OF ISSUES 
        msp.setBorder( BorderFactory.createEmptyBorder(W, W, W, W) );

        add( msp, BorderLayout.CENTER );     
    }

    private JXComboBox<UoMTreeNode> uomComboBox;

    // recursively populate the DefaultListModel
    private void populateListModel(DefaultListModel<UoMTreeNode> uomModel, GenericTreeNode<?> gtn) {
        for(int c=0; c<gtn.getChildCount(); c++) {
            GenericTreeNode<?> tn = (GenericTreeNode<?>)gtn.getChildAt(c);
            uomModel.addElement((UoMTreeNode)tn);
            if(!tn.isLeaf()) {
                populateListModel(uomModel, tn);
            }
        }
    }
    private JComponent createComboBox() {
        DefaultListModel<UoMTreeNode> uomModel = new DefaultListModel<UoMTreeNode>();
        GenericTreeNode<?> root = UoMTreeNode.getUomModelRoot();
        uomModel.addElement((UoMTreeNode)root);
        populateListModel(uomModel, root);
        
        //Create the combobox with items from uomModel
        UoMTreeNode[] items = new UoMTreeNode[uomModel.size()];
        uomModel.copyInto(items);
        uomComboBox = new JXComboBox<UoMTreeNode>(items);
        
        // custom String representation: concat various element fields
        StringValue sv = (Object value) -> {
            if (value instanceof UoMTreeNode c) {
                return c.toString();
            }
            return StringValues.TO_STRING.getString(value);
        };
        IconValue iv = (Object value) -> {
            if (value instanceof UoMTreeNode c) {
                return UoMTreeNode.SI_ICON.getIcon(c);
            }
            return IconValue.NULL_ICON;
        };
        uomComboBox.setRenderer(new DefaultListRenderer<UoMTreeNode>(sv, iv));
        uomComboBox.addHighlighter(HighlighterFactory.createSimpleStriping(HighlighterFactory.NOTEPAD));
        uomComboBox.addHighlighter(new ColorHighlighter(HighlightPredicate.ROLLOVER_ROW, null, Color.RED));
        /*
         * nach einem LaF Wechsel die Highlighter nicht angezeigt
         * ... auch die SI_ICON nicht ===> TODO
         */
        uomComboBox.setComboBoxIcon(KorelleRCircle_icons_arrow_down.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON)
            , KorelleRCircle_icons_arrow_up.of(JXIcon.BUTTON_ICON, JXIcon.BUTTON_ICON)
            );

        uomComboBox.setSelectedIndex(0);
        uomComboBox.addActionListener( ae -> {
            Object o = uomComboBox.getSelectedItem();
            UoMTreeNode node = (UoMTreeNode)o;
            getUoMTreeNodeContainer().add(node);
        });

        return uomComboBox;     
    }

    public void quit() {
        System.exit(0);
    }

}
