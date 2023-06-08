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
// https://www.svgrepo.com/svg/313168/trash
public class KorelleRtrash_svgrepo_com implements RadianceIcon {
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
g.transform(new AffineTransform(16.66666603088379f, 0.0f, 0.0f, 16.66666603088379f, -0.0f, -0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(7.0f, 11.59f);
generalPath.lineTo(41.0f, 11.59f);
generalPath.lineTo(41.0f, 11.59f);
generalPath.lineTo(41.0f, 42.5f);
generalPath.curveTo(41.0f, 44.70914f, 39.20914f, 46.5f, 37.0f, 46.5f);
generalPath.lineTo(11.0f, 46.5f);
generalPath.curveTo(8.790861f, 46.5f, 7.0f, 44.70914f, 7.0f, 42.5f);
generalPath.lineTo(7.0f, 11.59f);
generalPath.lineTo(7.0f, 11.59f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(200, 200, 200, 255)) : new Color(200, 200, 200, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(43.0f, 13.0f);
generalPath.lineTo(5.0f, 13.0f);
generalPath.curveTo(3.8989217f, 12.995076f, 3.0089242f, 12.1010895f, 3.0089242f, 11.0f);
generalPath.curveTo(3.0089242f, 9.8989105f, 3.8989217f, 9.004924f, 5.0f, 9.0f);
generalPath.lineTo(43.0f, 9.0f);
generalPath.curveTo(44.101078f, 9.004924f, 44.991077f, 9.8989105f, 44.991077f, 11.0f);
generalPath.curveTo(44.991077f, 12.1010895f, 44.101078f, 12.995076f, 43.0f, 13.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(156, 163, 167, 255)) : new Color(156, 163, 167, 255);
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
generalPath.moveTo(16.0f, 38.5f);
generalPath.curveTo(14.895431f, 38.5f, 14.0f, 37.60457f, 14.0f, 36.5f);
generalPath.lineTo(14.0f, 22.5f);
generalPath.curveTo(14.004924f, 21.398922f, 14.8989105f, 20.508924f, 16.0f, 20.508924f);
generalPath.curveTo(17.10109f, 20.508924f, 17.995075f, 21.398922f, 18.0f, 22.5f);
generalPath.lineTo(18.0f, 36.5f);
generalPath.curveTo(18.0f, 37.60457f, 17.10457f, 38.5f, 16.0f, 38.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(156, 163, 167, 255)) : new Color(156, 163, 167, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(24.0f, 38.5f);
generalPath.curveTo(22.89543f, 38.5f, 22.0f, 37.60457f, 22.0f, 36.5f);
generalPath.lineTo(22.0f, 22.5f);
generalPath.curveTo(22.004925f, 21.398922f, 22.89891f, 20.508924f, 24.0f, 20.508924f);
generalPath.curveTo(25.10109f, 20.508924f, 25.995075f, 21.398922f, 26.0f, 22.5f);
generalPath.lineTo(26.0f, 36.5f);
generalPath.curveTo(26.0f, 37.60457f, 25.10457f, 38.5f, 24.0f, 38.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(156, 163, 167, 255)) : new Color(156, 163, 167, 255);
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
generalPath.moveTo(32.0f, 38.5f);
generalPath.curveTo(30.89543f, 38.5f, 30.0f, 37.60457f, 30.0f, 36.5f);
generalPath.lineTo(30.0f, 22.5f);
generalPath.curveTo(30.004925f, 21.398922f, 30.89891f, 20.508924f, 32.0f, 20.508924f);
generalPath.curveTo(33.10109f, 20.508924f, 33.995075f, 21.398922f, 34.0f, 22.5f);
generalPath.lineTo(34.0f, 36.5f);
generalPath.curveTo(34.0f, 37.60457f, 33.10457f, 38.5f, 32.0f, 38.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(156, 163, 167, 255)) : new Color(156, 163, 167, 255);
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
generalPath.moveTo(33.0f, 9.0f);
generalPath.lineTo(29.0f, 9.0f);
generalPath.lineTo(29.0f, 5.0f);
generalPath.lineTo(19.0f, 5.0f);
generalPath.lineTo(19.0f, 9.0f);
generalPath.lineTo(15.0f, 9.0f);
generalPath.lineTo(15.0f, 5.0f);
generalPath.curveTo(15.0f, 2.790861f, 16.790861f, 1.0f, 19.0f, 1.0f);
generalPath.lineTo(29.0f, 1.0f);
generalPath.curveTo(31.209139f, 1.0f, 33.0f, 2.790861f, 33.0f, 5.0f);
generalPath.lineTo(33.0f, 9.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(156, 163, 167, 255)) : new Color(156, 163, 167, 255);
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
        return 50.14873504638672;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static double getOrigY() {
        return 16.66666603088379;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static double getOrigWidth() {
		return 699.7025756835938;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static double getOrigHeight() {
		return 758.3333129882812;
	}

	/** The current width of this icon. */
	private int width;

    /** The current height of this icon. */
	private int height;

	/**
	 * Creates a new transcoded SVG image. This is marked as private to indicate that app
	 * code should be using the {@link #of(int, int)} method to obtain a pre-configured instance.
	 */
	private KorelleRtrash_svgrepo_com() {
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
       KorelleRtrash_svgrepo_com base = new KorelleRtrash_svgrepo_com();
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
       KorelleRtrash_svgrepo_com base = new KorelleRtrash_svgrepo_com();
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
        return KorelleRtrash_svgrepo_com::new;
    }
}

