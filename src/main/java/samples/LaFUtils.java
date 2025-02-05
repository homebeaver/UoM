package samples;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import org.jdesktop.swingx.SwingXUtilities;

public class LaFUtils {

    private static final Logger LOG = Logger.getLogger(LaFUtils.class.getName());

    private LaFUtils() {}
    
    public static boolean setLAF(String nameSnippet) {
    	String currentClassName = UIManager.getLookAndFeel().getClass().getName();
    	if(currentClassName.contains(nameSnippet)) {
    		LOG.config("current LaF is "+currentClassName);
    	}
    	UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();
    	for (LookAndFeelInfo info : lafInfo) {
    		String lafClassName = info.getClassName();
    		if(lafClassName.contains(nameSnippet)) {
    			try {
    	    		LOG.config("switch to LaF ClassName="+lafClassName + " from "+UIManager.getLookAndFeel());
					UIManager.setLookAndFeel(lafClassName);
					SwingXUtilities.updateAllComponentTreeUIs();
					return !lafClassName.equals(currentClassName);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	LOG.warning("Look and Feel not found: "+nameSnippet);
    	return false;
    }

    public static void setLAFandTheme(List<String> argList) {
    	LOG.config("args:"+argList);
		boolean lafChanged = setLAF(argList.get(0));
		if("metal".equalsIgnoreCase(argList.get(0)) 
			|| "javax.swing.plaf.metal.MetalLookAndFeel".equals(argList.get(0))) {
    		String themeClassName = argList.size()>1 ? argList.get(1) : null;
    		javax.swing.plaf.metal.MetalTheme currentTheme = javax.swing.plaf.metal.MetalLookAndFeel.getCurrentTheme();
    		String currentThemeClassName = currentTheme.getClass().getName();
    		if(currentThemeClassName.equals(themeClassName)) {
    			LOG.info("current theme:"+currentThemeClassName);
    		} else if(themeClassName!=null){
    			LOG.info("current theme:"+currentThemeClassName + " try set to "+themeClassName);
        		Class<?> themeClass = null;
				try {
					themeClass = Class.forName(themeClassName); // throws ClassNotFoundException
				} catch (Exception e) {
					LOG.severe("Error occurred loading class: " + themeClassName);
					e.printStackTrace();
				}
				try {
					javax.swing.plaf.metal.MetalTheme theme = (javax.swing.plaf.metal.MetalTheme)themeClass.getDeclaredConstructor().newInstance();
					javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme(theme);
					SwingXUtilities.updateAllComponentTreeUIs();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
		} else if(lafChanged) {
	    	LOG.config("Look and Feel changed.");
		}
    }

}
