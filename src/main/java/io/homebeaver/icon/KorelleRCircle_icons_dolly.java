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
public class KorelleRCircle_icons_dolly implements RadianceIcon {
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
generalPath.moveTo(48.0f, 42.4f);
generalPath.lineTo(45.0f, 32.9f);
generalPath.curveTo(44.8f, 32.4f, 44.3f, 32.100002f, 43.7f, 32.2f);
generalPath.lineTo(34.9f, 35.0f);
generalPath.lineTo(42.0f, 30.6f);
generalPath.curveTo(42.5f, 30.300001f, 42.6f, 29.7f, 42.3f, 29.2f);
generalPath.lineTo(35.399998f, 18.2f);
generalPath.curveTo(35.1f, 17.7f, 34.499996f, 17.6f, 33.999996f, 17.900002f);
generalPath.lineTo(25.299995f, 23.400002f);
generalPath.lineTo(23.9f, 19.0f);
generalPath.curveTo(23.199999f, 16.9f, 20.9f, 14.2f, 17.0f, 15.4f);
generalPath.curveTo(16.2f, 15.599999f, 15.8f, 16.5f, 16.0f, 17.3f);
generalPath.curveTo(16.2f, 18.099998f, 17.1f, 18.5f, 17.9f, 18.3f);
generalPath.curveTo(20.1f, 17.599998f, 20.8f, 19.4f, 21.0f, 19.9f);
generalPath.lineTo(29.0f, 45.4f);
generalPath.curveTo(27.0f, 46.7f, 26.0f, 49.2f, 26.8f, 51.600002f);
generalPath.curveTo(27.5f, 53.9f, 29.599998f, 55.500004f, 32.0f, 55.500004f);
generalPath.curveTo(32.6f, 55.500004f, 33.1f, 55.400005f, 33.6f, 55.200005f);
generalPath.curveTo(35.0f, 54.800003f, 36.1f, 53.800003f, 36.8f, 52.500004f);
generalPath.curveTo(37.2f, 51.700005f, 37.399998f, 50.700005f, 37.399998f, 49.800003f);
generalPath.lineTo(45.699997f, 47.200005f);
generalPath.curveTo(46.499996f, 47.000004f, 46.899998f, 46.100006f, 46.699997f, 45.300003f);
generalPath.curveTo(46.499996f, 44.500004f, 45.6f, 44.100002f, 44.799995f, 44.300003f);
generalPath.lineTo(36.6f, 47.0f);
generalPath.lineTo(36.6f, 47.0f);
generalPath.lineTo(47.3f, 43.7f);
generalPath.curveTo(47.6f, 43.600002f, 47.8f, 43.4f, 47.899998f, 43.2f);
generalPath.curveTo(47.999996f, 43.0f, 48.0f, 42.6f, 48.0f, 42.4f);
generalPath.closePath();
generalPath.moveTo(30.4f, 36.4f);
generalPath.curveTo(30.1f, 36.5f, 29.9f, 36.7f, 29.8f, 36.9f);
generalPath.curveTo(29.699999f, 37.100002f, 29.699999f, 37.4f, 29.699999f, 37.7f);
generalPath.lineTo(31.9f, 44.600002f);
generalPath.lineTo(31.9f, 44.600002f);
generalPath.lineTo(27.3f, 30.0f);
generalPath.lineTo(31.099998f, 36.1f);
generalPath.curveTo(31.099998f, 36.1f, 31.099998f, 36.1f, 31.099998f, 36.1f);
generalPath.lineTo(30.4f, 36.4f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_2
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(32.0f, 32.6f);
generalPath.lineTo(32.0f, 32.6f);
generalPath.lineTo(41.4f, 26.7f);
generalPath.lineTo(41.4f, 26.7f);
generalPath.lineTo(34.5f, 15.7f);
generalPath.lineTo(34.5f, 15.7f);
generalPath.lineTo(25.1f, 21.5f);
generalPath.lineTo(25.1f, 21.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(245, 207, 135, 255)) : new Color(245, 207, 135, 255);
g.setPaint(paint);
g.fill(shape);
paint = (colorFilter != null) ? colorFilter.filter(new Color(245, 207, 135, 255)) : new Color(245, 207, 135, 255);
stroke = new BasicStroke(2.0f,1,1,10.0f,null,0.0f);
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(32.0f, 32.6f);
generalPath.lineTo(32.0f, 32.6f);
generalPath.lineTo(41.4f, 26.7f);
generalPath.lineTo(41.4f, 26.7f);
generalPath.lineTo(34.5f, 15.7f);
generalPath.lineTo(34.5f, 15.7f);
generalPath.lineTo(25.1f, 21.5f);
generalPath.lineTo(25.1f, 21.5f);
generalPath.closePath();
shape = generalPath;
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3
paint = (colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 255)) : new Color(255, 255, 255, 255);
stroke = new BasicStroke(3.0f,1,1,10.0f,null,0.0f);
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(45.4f, 42.8f);
generalPath.lineTo(32.0f, 47.0f);
generalPath.lineTo(22.5f, 16.5f);
generalPath.curveTo(22.5f, 16.5f, 21.3f, 12.7f, 17.5f, 13.9f);
shape = generalPath;
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
generalPath.moveTo(33.6f, 43.9f);
generalPath.lineTo(33.6f, 43.9f);
generalPath.lineTo(47.0f, 39.7f);
generalPath.lineTo(47.0f, 39.7f);
generalPath.lineTo(44.0f, 30.1f);
generalPath.lineTo(44.0f, 30.1f);
generalPath.lineTo(30.7f, 34.3f);
generalPath.lineTo(30.7f, 34.3f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 153, 94, 255)) : new Color(224, 153, 94, 255);
g.setPaint(paint);
g.fill(shape);
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 153, 94, 255)) : new Color(224, 153, 94, 255);
stroke = new BasicStroke(2.0f,1,1,10.0f,null,0.0f);
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(33.6f, 43.9f);
generalPath.lineTo(33.6f, 43.9f);
generalPath.lineTo(47.0f, 39.7f);
generalPath.lineTo(47.0f, 39.7f);
generalPath.lineTo(44.0f, 30.1f);
generalPath.lineTo(44.0f, 30.1f);
generalPath.lineTo(30.7f, 34.3f);
generalPath.lineTo(30.7f, 34.3f);
generalPath.closePath();
shape = generalPath;
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5
shape = new Ellipse2D.Double(28.0, 43.0, 8.0, 8.0);
paint = (colorFilter != null) ? colorFilter.filter(new Color(245, 207, 135, 255)) : new Color(245, 207, 135, 255);
g.setPaint(paint);
g.fill(shape);
paint = (colorFilter != null) ? colorFilter.filter(new Color(79, 93, 115, 255)) : new Color(79, 93, 115, 255);
stroke = new BasicStroke(3.0f,1,1,10.0f,null,0.0f);
shape = new Ellipse2D.Double(28.0, 43.0, 8.0, 8.0);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
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
	private KorelleRCircle_icons_dolly() {
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
       KorelleRCircle_icons_dolly base = new KorelleRCircle_icons_dolly();
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
       KorelleRCircle_icons_dolly base = new KorelleRCircle_icons_dolly();
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
        return KorelleRCircle_icons_dolly::new;
    }
}

