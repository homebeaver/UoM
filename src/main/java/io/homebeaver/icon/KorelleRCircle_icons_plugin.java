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
public class KorelleRCircle_icons_plugin implements RadianceIcon {
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
paint = (colorFilter != null) ? colorFilter.filter(new Color(118, 194, 175, 255)) : new Color(118, 194, 175, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(18.4f, 50.4f);
generalPath.curveTo(17.6f, 51.2f, 16.4f, 51.2f, 15.599999f, 50.4f);
generalPath.lineTo(15.599999f, 50.4f);
generalPath.curveTo(14.799999f, 49.600002f, 14.799999f, 48.4f, 15.599999f, 47.600002f);
generalPath.lineTo(25.5f, 37.700005f);
generalPath.curveTo(26.3f, 36.900005f, 27.5f, 36.900005f, 28.3f, 37.700005f);
generalPath.lineTo(28.3f, 37.700005f);
generalPath.curveTo(29.099998f, 38.500004f, 29.099998f, 39.700005f, 28.3f, 40.500004f);
generalPath.lineTo(18.4f, 50.4f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(31.1f, 26.4f);
generalPath.curveTo(30.300001f, 27.199999f, 29.1f, 27.199999f, 28.300001f, 26.4f);
generalPath.lineTo(28.300001f, 26.4f);
generalPath.curveTo(27.500002f, 25.6f, 27.500002f, 24.4f, 28.300001f, 23.6f);
generalPath.lineTo(38.2f, 13.700001f);
generalPath.curveTo(39.0f, 12.900001f, 40.2f, 12.900001f, 41.0f, 13.700001f);
generalPath.lineTo(41.0f, 13.700001f);
generalPath.curveTo(41.8f, 14.500001f, 41.8f, 15.700001f, 41.0f, 16.5f);
generalPath.lineTo(31.1f, 26.4f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(42.5f, 37.7f);
generalPath.curveTo(41.7f, 38.5f, 40.5f, 38.5f, 39.7f, 37.7f);
generalPath.lineTo(39.7f, 37.7f);
generalPath.curveTo(38.9f, 36.9f, 38.9f, 35.7f, 39.7f, 34.9f);
generalPath.lineTo(49.6f, 25.000002f);
generalPath.curveTo(50.399998f, 24.200003f, 51.6f, 24.200003f, 52.399998f, 25.000002f);
generalPath.lineTo(52.399998f, 25.000002f);
generalPath.curveTo(53.199997f, 25.800001f, 53.199997f, 27.000002f, 52.399998f, 27.800001f);
generalPath.lineTo(42.5f, 37.7f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_4
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(18.4f, 48.4f);
generalPath.curveTo(17.6f, 49.2f, 16.4f, 49.2f, 15.599999f, 48.4f);
generalPath.lineTo(15.599999f, 48.4f);
generalPath.curveTo(14.799999f, 47.600002f, 14.799999f, 46.4f, 15.599999f, 45.600002f);
generalPath.lineTo(25.5f, 35.700005f);
generalPath.curveTo(26.3f, 34.900005f, 27.5f, 34.900005f, 28.3f, 35.700005f);
generalPath.lineTo(28.3f, 35.700005f);
generalPath.curveTo(29.099998f, 36.500004f, 29.099998f, 37.700005f, 28.3f, 38.500004f);
generalPath.lineTo(18.4f, 48.4f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(79, 93, 115, 255)) : new Color(79, 93, 115, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(31.1f, 24.4f);
generalPath.curveTo(30.300001f, 25.199999f, 29.1f, 25.199999f, 28.300001f, 24.4f);
generalPath.lineTo(28.300001f, 24.4f);
generalPath.curveTo(27.500002f, 23.6f, 27.500002f, 22.4f, 28.300001f, 21.6f);
generalPath.lineTo(38.2f, 11.700001f);
generalPath.curveTo(39.0f, 10.900001f, 40.2f, 10.900001f, 41.0f, 11.700001f);
generalPath.lineTo(41.0f, 11.700001f);
generalPath.curveTo(41.8f, 12.500001f, 41.8f, 13.700001f, 41.0f, 14.500001f);
generalPath.lineTo(31.1f, 24.4f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_6
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(42.5f, 35.7f);
generalPath.curveTo(41.7f, 36.5f, 40.5f, 36.5f, 39.7f, 35.7f);
generalPath.lineTo(39.7f, 35.7f);
generalPath.curveTo(38.9f, 34.9f, 38.9f, 33.7f, 39.7f, 32.9f);
generalPath.lineTo(49.6f, 23.000002f);
generalPath.curveTo(50.399998f, 22.200003f, 51.6f, 22.200003f, 52.399998f, 23.000002f);
generalPath.lineTo(52.399998f, 23.000002f);
generalPath.curveTo(53.199997f, 23.800001f, 53.199997f, 25.000002f, 52.399998f, 25.800001f);
generalPath.lineTo(42.5f, 35.7f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(27.9f, 19.3f);
generalPath.lineTo(19.9f, 27.3f);
generalPath.curveTo(15.599999f, 31.599998f, 15.599999f, 38.6f, 19.9f, 42.9f);
generalPath.lineTo(22.699999f, 45.7f);
generalPath.curveTo(27.0f, 50.0f, 34.0f, 50.0f, 38.3f, 45.7f);
generalPath.lineTo(46.3f, 37.7f);
generalPath.lineTo(27.9f, 19.3f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_8
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(27.9f, 17.7f);
generalPath.lineTo(19.9f, 25.7f);
generalPath.curveTo(15.599999f, 30.0f, 15.599999f, 37.0f, 19.9f, 41.300003f);
generalPath.lineTo(22.699999f, 44.100002f);
generalPath.curveTo(27.0f, 48.4f, 34.0f, 48.4f, 38.3f, 44.100002f);
generalPath.lineTo(46.3f, 36.100002f);
generalPath.lineTo(27.9f, 17.7f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(79, 93, 115, 255)) : new Color(79, 93, 115, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_9
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(48.1f, 40.4f);
generalPath.curveTo(47.3f, 41.2f, 46.1f, 41.2f, 45.3f, 40.4f);
generalPath.lineTo(25.5f, 20.6f);
generalPath.curveTo(24.7f, 19.800001f, 24.7f, 18.6f, 25.5f, 17.800001f);
generalPath.lineTo(25.5f, 17.800001f);
generalPath.curveTo(26.3f, 17.000002f, 27.5f, 17.000002f, 28.3f, 17.800001f);
generalPath.lineTo(48.1f, 37.6f);
generalPath.curveTo(48.9f, 38.4f, 48.9f, 39.6f, 48.1f, 40.4f);
generalPath.lineTo(48.1f, 40.4f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_10
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(48.1f, 38.5f);
generalPath.curveTo(47.3f, 39.3f, 46.1f, 39.3f, 45.3f, 38.5f);
generalPath.lineTo(25.5f, 18.7f);
generalPath.curveTo(24.7f, 17.900002f, 24.7f, 16.7f, 25.5f, 15.900001f);
generalPath.lineTo(25.5f, 15.900001f);
generalPath.curveTo(26.3f, 15.1f, 27.5f, 15.1f, 28.3f, 15.900001f);
generalPath.lineTo(48.1f, 35.7f);
generalPath.curveTo(48.9f, 36.5f, 48.9f, 37.7f, 48.1f, 38.5f);
generalPath.lineTo(48.1f, 38.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(79, 93, 115, 255)) : new Color(79, 93, 115, 255);
g.setPaint(paint);
g.fill(shape);
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
	private KorelleRCircle_icons_plugin() {
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
       KorelleRCircle_icons_plugin base = new KorelleRCircle_icons_plugin();
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
       KorelleRCircle_icons_plugin base = new KorelleRCircle_icons_plugin();
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
        return KorelleRCircle_icons_plugin::new;
    }
}

