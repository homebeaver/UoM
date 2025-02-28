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
public class KorelleRMilk_ballonicon2 implements RadianceIcon {
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
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.0f, -0.0f));
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
generalPath.moveTo(50.0f, 0.0f);
generalPath.curveTo(77.614f, 0.0f, 100.0f, 22.386f, 100.0f, 50.0f);
generalPath.curveTo(100.0f, 77.614f, 77.614f, 100.0f, 50.0f, 100.0f);
generalPath.curveTo(22.386002f, 100.0f, 0.0f, 77.614f, 0.0f, 50.0f);
generalPath.curveTo(0.0f, 22.386002f, 22.386f, 0.0f, 50.0f, 0.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(50, 77, 91, 255)) : new Color(50, 77, 91, 255);
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
generalPath.moveTo(19.001f, 89.216f);
generalPath.curveTo(27.522999f, 95.961006f, 38.287f, 100.0f, 50.0f, 100.0f);
generalPath.curveTo(53.781f, 100.0f, 57.459f, 99.566f, 61.0f, 98.771f);
generalPath.lineTo(61.0f, 67.0f);
generalPath.lineTo(19.002998f, 46.996002f);
generalPath.lineTo(19.000998f, 89.216f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(238, 243, 243, 255)) : new Color(238, 243, 243, 255);
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
generalPath.moveTo(61.012f, 98.769f);
generalPath.curveTo(71.195f, 96.479f, 80.207f, 91.096f, 86.991f, 83.64f);
generalPath.lineTo(86.991f, 54.001f);
generalPath.lineTo(61.011997f, 67.073f);
generalPath.lineTo(61.011997f, 98.769f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(216, 216, 216, 255)) : new Color(216, 216, 216, 255);
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
generalPath.moveTo(60.0f, 67.0f);
generalPath.lineTo(73.0f, 47.0f);
generalPath.lineTo(87.0f, 54.0f);
generalPath.lineTo(87.0f, 55.0f);
generalPath.lineTo(61.0f, 68.0f);
generalPath.lineTo(60.0f, 67.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(192, 192, 192, 255)) : new Color(192, 192, 192, 255);
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
generalPath.moveTo(69.0f, 45.0f);
generalPath.lineTo(87.0f, 54.0f);
generalPath.lineTo(69.0f, 51.0f);
generalPath.lineTo(69.0f, 45.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(144, 144, 144, 255)) : new Color(144, 144, 144, 255);
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
generalPath.moveTo(32.0f, 28.0f);
generalPath.lineTo(32.833f, 26.583f);
generalPath.lineTo(73.563f, 45.75f);
generalPath.lineTo(73.792f, 47.417f);
generalPath.lineTo(61.0f, 67.0f);
generalPath.lineTo(61.0f, 68.0f);
generalPath.lineTo(19.0f, 48.0f);
generalPath.lineTo(19.0f, 47.0f);
generalPath.lineTo(32.0f, 28.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(61, 190, 233, 255)) : new Color(61, 190, 233, 255);
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
generalPath.moveTo(34.621f, 17.25f);
generalPath.lineTo(71.168f, 34.774002f);
generalPath.curveTo(72.617f, 35.488003f, 73.792f, 37.515003f, 73.792f, 39.301003f);
generalPath.lineTo(73.792f, 47.416f);
generalPath.lineTo(32.0f, 28.0f);
generalPath.lineTo(31.996f, 19.189999f);
generalPath.curveTo(31.996f, 17.404f, 33.171f, 16.534998f, 34.621002f, 17.249998f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(238, 243, 243, 255)) : new Color(238, 243, 243, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(49.068f, 41.381f);
generalPath.curveTo(50.982002f, 43.295002f, 51.674f, 45.999f, 49.429f, 49.104f);
generalPath.curveTo(47.183002f, 52.208f, 43.38f, 53.782f, 40.936f, 52.621f);
generalPath.curveTo(38.491f, 51.459f, 38.0f, 50.0f, 38.0f, 50.0f);
generalPath.curveTo(38.0f, 50.0f, 46.913002f, 40.096f, 48.0f, 40.0f);
generalPath.curveTo(48.029f, 39.998f, 48.262f, 40.573f, 49.068f, 41.381f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(214, 218, 218, 255)) : new Color(214, 218, 218, 255);
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
generalPath.moveTo(47.068f, 39.381f);
generalPath.curveTo(49.513f, 40.542f, 49.674f, 44.0f, 47.428f, 47.104f);
generalPath.curveTo(45.183002f, 50.208f, 41.38f, 51.782f, 38.936f, 50.621f);
generalPath.curveTo(36.491f, 49.46f, 36.33f, 46.002f, 38.575f, 42.898f);
generalPath.curveTo(40.821f, 39.794f, 44.624f, 38.219997f, 47.068f, 39.381f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(238, 243, 243, 255)) : new Color(238, 243, 243, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_9
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(43.895f, 27.081f);
generalPath.lineTo(60.106003f, 34.563f);
generalPath.curveTo(60.604004f, 34.824f, 61.007004f, 35.562f, 61.007004f, 36.211998f);
generalPath.curveTo(61.007004f, 36.861996f, 60.604004f, 37.178997f, 60.106003f, 36.919f);
generalPath.lineTo(43.895004f, 29.435999f);
generalPath.curveTo(43.397003f, 29.175999f, 42.994003f, 28.438f, 42.994003f, 27.786999f);
generalPath.curveTo(42.993004f, 27.137f, 43.396004f, 26.82f, 43.895004f, 27.081f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(55, 171, 209, 255)) : new Color(55, 171, 209, 255);
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
generalPath.moveTo(67.795f, 74.455f);
generalPath.lineTo(82.205f, 67.179f);
generalPath.curveTo(83.200005f, 66.676f, 84.007f, 67.286f, 84.007f, 68.542f);
generalPath.lineTo(84.007f, 75.363f);
generalPath.curveTo(84.007f, 76.618f, 83.200005f, 78.044f, 82.205f, 78.546f);
generalPath.lineTo(67.795f, 85.822f);
generalPath.curveTo(66.799995f, 86.324f, 65.993f, 85.714f, 65.993f, 84.458f);
generalPath.lineTo(65.993f, 77.638f);
generalPath.curveTo(65.993f, 76.382f, 66.799995f, 74.957f, 67.795f, 74.455f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(64, 168, 202, 255)) : new Color(64, 168, 202, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_11
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_11_0
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(19.006f, 66.34f);
generalPath.lineTo(60.994f, 86.006996f);
generalPath.lineTo(60.994f, 81.660995f);
generalPath.lineTo(19.006f, 61.992996f);
generalPath.closePath();
generalPath.moveTo(19.006f, 89.22f);
generalPath.curveTo(27.527f, 95.963f, 38.289f, 100.0f, 50.0f, 100.0f);
generalPath.curveTo(53.779f, 100.0f, 57.455f, 99.567f, 60.994f, 98.772f);
generalPath.lineTo(60.994f, 89.661f);
generalPath.lineTo(19.006f, 69.993004f);
generalPath.lineTo(19.006f, 89.22f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(55, 171, 209, 255)) : new Color(55, 171, 209, 255);
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
		return 100.0;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static double getOrigHeight() {
		return 100.0;
	}

	/** The current width of this icon. */
	private int width;

    /** The current height of this icon. */
	private int height;

	/**
	 * Creates a new transcoded SVG image. This is marked as private to indicate that app
	 * code should be using the {@link #of(int, int)} method to obtain a pre-configured instance.
	 */
	private KorelleRMilk_ballonicon2() {
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
       KorelleRMilk_ballonicon2 base = new KorelleRMilk_ballonicon2();
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
       KorelleRMilk_ballonicon2 base = new KorelleRMilk_ballonicon2();
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
        return KorelleRMilk_ballonicon2::new;
    }
}

