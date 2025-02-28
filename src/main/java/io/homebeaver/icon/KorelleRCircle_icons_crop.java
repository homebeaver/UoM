package io.homebeaver.icon;

import java.awt.*;
import java.awt.geom.*;
import java.util.Stack;
import javax.swing.plaf.UIResource;

import org.jdesktop.swingx.icon.RadianceIcon;
import org.jdesktop.swingx.icon.RadianceIconUIResource;

/**
 * This class has been automatically generated using 
 * <a href="https://jdesktop.wordpress.com/2022/09/25/svg-icons/">Radiance SVG converter</a>.
 */
public class KorelleRCircle_icons_crop implements RadianceIcon {
    private Shape shape = null;
    private GeneralPath generalPath = null;
    private Paint paint = null;
    private Stroke stroke = null;
    private RadianceIcon.ColorFilter colorFilter = null;
    private Stack<AffineTransform> transformsStack = new Stack<>();

	// EUG https://github.com/homebeaver (rotation + point/axis reflection)
    private int rsfx = 1, rsfy = 1;
    public void setReflection(boolean horizontal, boolean vertical) {
    	this.rsfx = vertical ? -1 : 1;
    	this.rsfy = horizontal ? -1 : 1;
    }    
    public boolean isReflection() {
		return rsfx==-1 || rsfy==-1;
	}
	
    private double theta = 0;
    public void setRotation(double theta) {
    	this.theta = theta;
    }    
    public double getRotation() {
		return theta;
	}
	// EUG -- END

    

	private void _paint0(Graphics2D g,float origAlpha) {
transformsStack.push(g.getTransform());
// 
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(0.015625f, 0.0f, 0.0f, 0.015625f, -0.0f, -0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0
shape = new Ellipse2D.Double(0.0, 0.0, 64.0, 64.0);
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_0
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(50.5f, 45.0f);
generalPath.lineTo(50.0f, 45.0f);
generalPath.lineTo(38.0f, 45.0f);
generalPath.lineTo(25.0f, 45.0f);
generalPath.lineTo(43.0f, 27.0f);
generalPath.lineTo(43.0f, 42.0f);
generalPath.lineTo(48.0f, 42.0f);
generalPath.lineTo(48.0f, 22.0f);
generalPath.lineTo(50.8f, 19.2f);
generalPath.curveTo(51.3f, 18.7f, 51.5f, 18.1f, 51.5f, 17.400002f);
generalPath.curveTo(51.5f, 16.700003f, 51.2f, 16.100002f, 50.8f, 15.600001f);
generalPath.curveTo(50.3f, 15.3f, 49.7f, 15.0f, 49.0f, 15.0f);
generalPath.curveTo(48.3f, 15.0f, 47.7f, 15.3f, 47.2f, 15.7f);
generalPath.lineTo(43.4f, 19.5f);
generalPath.lineTo(24.0f, 19.5f);
generalPath.lineTo(24.0f, 24.0f);
generalPath.lineTo(38.5f, 24.0f);
generalPath.lineTo(21.0f, 42.0f);
generalPath.lineTo(21.0f, 29.0f);
generalPath.lineTo(21.0f, 17.0f);
generalPath.curveTo(21.0f, 15.6f, 19.9f, 14.5f, 18.5f, 14.5f);
generalPath.curveTo(17.1f, 14.5f, 16.0f, 15.6f, 16.0f, 17.0f);
generalPath.lineTo(16.0f, 19.0f);
generalPath.lineTo(13.5f, 19.0f);
generalPath.curveTo(12.1f, 19.0f, 11.0f, 20.1f, 11.0f, 21.5f);
generalPath.curveTo(11.0f, 22.9f, 12.1f, 24.0f, 13.5f, 24.0f);
generalPath.lineTo(16.0f, 24.0f);
generalPath.lineTo(16.0f, 47.5f);
generalPath.curveTo(16.0f, 48.9f, 17.1f, 50.0f, 18.5f, 50.0f);
generalPath.lineTo(43.0f, 50.0f);
generalPath.lineTo(43.0f, 52.5f);
generalPath.curveTo(43.0f, 53.9f, 44.1f, 55.0f, 45.5f, 55.0f);
generalPath.curveTo(46.9f, 55.0f, 48.0f, 53.9f, 48.0f, 52.5f);
generalPath.lineTo(48.0f, 50.0f);
generalPath.lineTo(50.5f, 50.0f);
generalPath.curveTo(51.9f, 50.0f, 53.0f, 48.9f, 53.0f, 47.5f);
generalPath.curveTo(53.0f, 46.1f, 51.9f, 45.0f, 50.5f, 45.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2_0
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(50.5f, 43.0f);
generalPath.lineTo(50.0f, 43.0f);
generalPath.lineTo(38.0f, 43.0f);
generalPath.lineTo(25.0f, 43.0f);
generalPath.lineTo(43.0f, 25.0f);
generalPath.lineTo(43.0f, 40.0f);
generalPath.lineTo(48.0f, 40.0f);
generalPath.lineTo(48.0f, 20.0f);
generalPath.lineTo(50.8f, 17.2f);
generalPath.curveTo(51.3f, 16.7f, 51.5f, 16.1f, 51.5f, 15.400001f);
generalPath.curveTo(51.5f, 14.700001f, 51.2f, 14.1f, 50.8f, 13.6f);
generalPath.curveTo(50.3f, 13.3f, 49.7f, 13.0f, 49.0f, 13.0f);
generalPath.curveTo(48.3f, 13.0f, 47.7f, 13.3f, 47.2f, 13.7f);
generalPath.lineTo(44.0f, 17.0f);
generalPath.lineTo(24.0f, 17.0f);
generalPath.lineTo(24.0f, 22.0f);
generalPath.lineTo(39.0f, 22.0f);
generalPath.lineTo(21.0f, 40.0f);
generalPath.lineTo(21.0f, 27.0f);
generalPath.lineTo(21.0f, 15.0f);
generalPath.curveTo(21.0f, 13.6f, 19.9f, 12.5f, 18.5f, 12.5f);
generalPath.curveTo(17.1f, 12.5f, 16.0f, 13.6f, 16.0f, 15.0f);
generalPath.lineTo(16.0f, 17.0f);
generalPath.lineTo(13.5f, 17.0f);
generalPath.curveTo(12.1f, 17.0f, 11.0f, 18.1f, 11.0f, 19.5f);
generalPath.curveTo(11.0f, 20.9f, 12.1f, 22.0f, 13.5f, 22.0f);
generalPath.lineTo(16.0f, 22.0f);
generalPath.lineTo(16.0f, 45.5f);
generalPath.curveTo(16.0f, 46.9f, 17.1f, 48.0f, 18.5f, 48.0f);
generalPath.lineTo(43.0f, 48.0f);
generalPath.lineTo(43.0f, 50.5f);
generalPath.curveTo(43.0f, 51.9f, 44.1f, 53.0f, 45.5f, 53.0f);
generalPath.curveTo(46.9f, 53.0f, 48.0f, 51.9f, 48.0f, 50.5f);
generalPath.lineTo(48.0f, 48.0f);
generalPath.lineTo(50.5f, 48.0f);
generalPath.curveTo(51.9f, 48.0f, 53.0f, 46.9f, 53.0f, 45.5f);
generalPath.curveTo(53.0f, 44.1f, 51.9f, 43.0f, 50.5f, 43.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(79, 93, 115, 255)) : new Color(79, 93, 115, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setTransform(transformsStack.pop());
g.setTransform(transformsStack.pop());
g.setTransform(transformsStack.pop());

}



	private void innerPaint(Graphics2D g) {
        float origAlpha = 1.0f;
        Composite origComposite = g.getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = 
                (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }
        
	    _paint0(g, origAlpha);


	    shape = null;
	    generalPath = null;
	    paint = null;
	    stroke = null;
        transformsStack.clear();
	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static double getOrigX() {
        return 0.0;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static double getOrigY() {
        return 0.0;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static double getOrigWidth() {
		return 1.0;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static double getOrigHeight() {
		return 1.0;
	}

	/** The current width of this icon. */
	private int width;

    /** The current height of this icon. */
	private int height;

	/**
	 * Creates a new transcoded SVG image. This is marked as private to indicate that app
	 * code should be using the {@link #of(int, int)} method to obtain a pre-configured instance.
	 */
	private KorelleRCircle_icons_crop() {
        this.width = (int) getOrigWidth();
        this.height = (int) getOrigHeight();
	}

    @Override
	public int getIconHeight() {
		return height;
	}

    @Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public synchronized void setDimension(Dimension newDimension) {
		this.width = newDimension.width;
		this.height = newDimension.height;
	}

    @Override
    public boolean supportsColorFilter() {
        return true;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    @Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        if(getRotation()!=0) {
            g2d.rotate(getRotation(), x+width/2, y+height/2);
        }
        if(isReflection()) {
        	g2d.translate(x+width/2, y+height/2);
        	g2d.scale(this.rsfx, this.rsfy);
        	g2d.translate(-x-width/2, -y-height/2);
        }
		g2d.translate(x, y);

        double coef1 = (double) this.width / getOrigWidth();
        double coef2 = (double) this.height / getOrigHeight();
        double coef = Math.min(coef1, coef2);
        g2d.clipRect(0, 0, this.width, this.height);
        g2d.scale(coef, coef);
        g2d.translate(-getOrigX(), -getOrigY());
        if (coef1 != coef2) {
            if (coef1 < coef2) {
               int extraDy = (int) ((getOrigWidth() - getOrigHeight()) / 2.0);
               g2d.translate(0, extraDy);
            } else {
               int extraDx = (int) ((getOrigHeight() - getOrigWidth()) / 2.0);
               g2d.translate(extraDx, 0);
            }
        }
        Graphics2D g2ForInner = (Graphics2D) g2d.create();
        innerPaint(g2ForInner);
        g2ForInner.dispose();
        g2d.dispose();
	}
    
    /**
     * Returns a new instance of this icon with specified dimensions.
     *
     * @param width Required width of the icon
     * @param height Required height of the icon
     * @return A new instance of this icon with specified dimensions.
     */
    public static RadianceIcon of(int width, int height) {
       KorelleRCircle_icons_crop base = new KorelleRCircle_icons_crop();
       base.width = width;
       base.height = height;
       return base;
    }

    /**
     * Returns a new {@link UIResource} instance of this icon with specified dimensions.
     *
     * @param width Required width of the icon
     * @param height Required height of the icon
     * @return A new {@link UIResource} instance of this icon with specified dimensions.
     */
    public static RadianceIconUIResource uiResourceOf(int width, int height) {
       KorelleRCircle_icons_crop base = new KorelleRCircle_icons_crop();
       base.width = width;
       base.height = height;
       return new RadianceIconUIResource(base);
    }

    /**
     * Returns a factory that returns instances of this icon on demand.
     *
     * @return Factory that returns instances of this icon on demand.
     */
    public static Factory factory() {
        return KorelleRCircle_icons_crop::new;
    }
}

