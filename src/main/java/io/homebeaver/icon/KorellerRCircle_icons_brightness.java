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
public class KorellerRCircle_icons_brightness implements RadianceIcon {
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
paint = (colorFilter != null) ? colorFilter.filter(new Color(199, 92, 92, 255)) : new Color(199, 92, 92, 255);
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
generalPath.moveTo(25.0f, 43.3f);
generalPath.lineTo(25.0f, 51.2f);
generalPath.curveTo(25.0f, 51.7f, 25.6f, 52.5f, 26.0f, 52.7f);
generalPath.lineTo(31.3f, 55.7f);
generalPath.curveTo(31.699999f, 55.9f, 32.399998f, 55.9f, 32.8f, 55.7f);
generalPath.lineTo(38.1f, 52.7f);
generalPath.curveTo(38.5f, 52.5f, 39.1f, 51.600002f, 39.1f, 51.2f);
generalPath.lineTo(39.1f, 43.3f);
generalPath.curveTo(37.0f, 44.399998f, 34.6f, 45.0f, 32.1f, 45.0f);
generalPath.curveTo(29.5f, 45.0f, 27.1f, 44.4f, 25.0f, 43.3f);
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
generalPath.moveTo(25.0f, 41.3f);
generalPath.lineTo(25.0f, 49.2f);
generalPath.curveTo(25.0f, 49.7f, 25.6f, 50.5f, 26.0f, 50.7f);
generalPath.lineTo(31.3f, 53.7f);
generalPath.curveTo(31.699999f, 53.9f, 32.399998f, 53.9f, 32.8f, 53.7f);
generalPath.lineTo(38.1f, 50.7f);
generalPath.curveTo(38.5f, 50.5f, 39.1f, 49.600002f, 39.1f, 49.2f);
generalPath.lineTo(39.1f, 41.3f);
generalPath.curveTo(37.0f, 42.399998f, 34.6f, 43.0f, 32.1f, 43.0f);
generalPath.curveTo(29.5f, 43.0f, 27.1f, 42.4f, 25.0f, 41.3f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_3_0
shape = new Ellipse2D.Double(17.600000381469727, 20.19999885559082, 28.799999237060547, 28.799999237060547);
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_4
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_4_0
shape = new Ellipse2D.Double(17.600000381469727, 17.600000381469727, 28.799999237060547, 28.799999237060547);
paint = (colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 255)) : new Color(255, 255, 255, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_0
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(33.5f, 15.5f);
generalPath.curveTo(33.5f, 16.3f, 32.8f, 17.0f, 32.0f, 17.0f);
generalPath.lineTo(32.0f, 17.0f);
generalPath.curveTo(31.2f, 17.0f, 30.5f, 16.3f, 30.5f, 15.5f);
generalPath.lineTo(30.5f, 13.5f);
generalPath.curveTo(30.5f, 12.7f, 31.2f, 12.0f, 32.0f, 12.0f);
generalPath.lineTo(32.0f, 12.0f);
generalPath.curveTo(32.8f, 12.0f, 33.5f, 12.7f, 33.5f, 13.5f);
generalPath.lineTo(33.5f, 15.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_1
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(50.5f, 35.5f);
generalPath.curveTo(49.7f, 35.5f, 49.0f, 34.8f, 49.0f, 34.0f);
generalPath.lineTo(49.0f, 34.0f);
generalPath.curveTo(49.0f, 33.2f, 49.7f, 32.5f, 50.5f, 32.5f);
generalPath.lineTo(52.5f, 32.5f);
generalPath.curveTo(53.3f, 32.5f, 54.0f, 33.2f, 54.0f, 34.0f);
generalPath.lineTo(54.0f, 34.0f);
generalPath.curveTo(54.0f, 34.8f, 53.3f, 35.5f, 52.5f, 35.5f);
generalPath.lineTo(50.5f, 35.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_2
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(13.5f, 32.5f);
generalPath.curveTo(14.3f, 32.5f, 15.0f, 33.2f, 15.0f, 34.0f);
generalPath.lineTo(15.0f, 34.0f);
generalPath.curveTo(15.0f, 34.8f, 14.3f, 35.5f, 13.5f, 35.5f);
generalPath.lineTo(11.5f, 35.5f);
generalPath.curveTo(10.7f, 35.5f, 10.0f, 34.8f, 10.0f, 34.0f);
generalPath.lineTo(10.0f, 34.0f);
generalPath.curveTo(10.0f, 33.2f, 10.7f, 32.5f, 11.5f, 32.5f);
generalPath.lineTo(13.5f, 32.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_3
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(24.0f, 17.2f);
generalPath.curveTo(24.4f, 17.900002f, 24.2f, 18.800001f, 23.5f, 19.2f);
generalPath.lineTo(23.5f, 19.2f);
generalPath.curveTo(22.8f, 19.6f, 21.9f, 19.400002f, 21.5f, 18.7f);
generalPath.lineTo(20.5f, 17.0f);
generalPath.curveTo(20.1f, 16.3f, 20.3f, 15.4f, 21.0f, 15.0f);
generalPath.lineTo(21.0f, 15.0f);
generalPath.curveTo(21.7f, 14.6f, 22.6f, 14.8f, 23.0f, 15.5f);
generalPath.lineTo(24.0f, 17.2f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_4
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(48.8f, 26.0f);
generalPath.curveTo(48.1f, 26.4f, 47.2f, 26.2f, 46.8f, 25.5f);
generalPath.lineTo(46.8f, 25.5f);
generalPath.curveTo(46.399998f, 24.8f, 46.6f, 23.9f, 47.3f, 23.5f);
generalPath.lineTo(49.0f, 22.5f);
generalPath.curveTo(49.7f, 22.1f, 50.6f, 22.3f, 51.0f, 23.0f);
generalPath.lineTo(51.0f, 23.0f);
generalPath.curveTo(51.4f, 23.7f, 51.2f, 24.6f, 50.5f, 25.0f);
generalPath.lineTo(48.8f, 26.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_5
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(15.2f, 42.0f);
generalPath.curveTo(15.9f, 41.6f, 16.8f, 41.8f, 17.2f, 42.5f);
generalPath.lineTo(17.2f, 42.5f);
generalPath.curveTo(17.6f, 43.2f, 17.400002f, 44.1f, 16.7f, 44.5f);
generalPath.lineTo(15.000001f, 45.5f);
generalPath.curveTo(14.300001f, 45.9f, 13.400001f, 45.7f, 13.000001f, 45.0f);
generalPath.lineTo(13.000001f, 45.0f);
generalPath.curveTo(12.600001f, 44.3f, 12.800001f, 43.4f, 13.500001f, 43.0f);
generalPath.lineTo(15.2f, 42.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_6
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(16.7f, 23.5f);
generalPath.curveTo(17.400002f, 23.9f, 17.7f, 24.8f, 17.2f, 25.5f);
generalPath.lineTo(17.2f, 25.5f);
generalPath.curveTo(16.800001f, 26.2f, 15.900001f, 26.5f, 15.200001f, 26.0f);
generalPath.lineTo(13.500001f, 25.0f);
generalPath.curveTo(12.800001f, 24.6f, 12.500001f, 23.7f, 13.000001f, 23.0f);
generalPath.lineTo(13.000001f, 23.0f);
generalPath.curveTo(13.400001f, 22.3f, 14.300001f, 22.0f, 15.000001f, 22.5f);
generalPath.lineTo(16.7f, 23.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_7
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(47.3f, 44.5f);
generalPath.curveTo(46.6f, 44.1f, 46.3f, 43.2f, 46.8f, 42.5f);
generalPath.lineTo(46.8f, 42.5f);
generalPath.curveTo(47.2f, 41.8f, 48.1f, 41.5f, 48.8f, 42.0f);
generalPath.lineTo(50.5f, 43.0f);
generalPath.curveTo(51.2f, 43.4f, 51.5f, 44.3f, 51.0f, 45.0f);
generalPath.lineTo(51.0f, 45.0f);
generalPath.curveTo(50.6f, 45.7f, 49.7f, 46.0f, 49.0f, 45.5f);
generalPath.lineTo(47.3f, 44.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(35, 31, 32, 255)) : new Color(35, 31, 32, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 0.2f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_5_8
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(42.5f, 18.7f);
generalPath.curveTo(42.1f, 19.400002f, 41.2f, 19.7f, 40.5f, 19.2f);
generalPath.lineTo(40.5f, 19.2f);
generalPath.curveTo(39.8f, 18.800001f, 39.5f, 17.900002f, 40.0f, 17.2f);
generalPath.lineTo(41.0f, 15.500001f);
generalPath.curveTo(41.4f, 14.800001f, 42.3f, 14.500001f, 43.0f, 15.000001f);
generalPath.lineTo(43.0f, 15.000001f);
generalPath.curveTo(43.7f, 15.400001f, 44.0f, 16.300001f, 43.5f, 17.0f);
generalPath.lineTo(42.5f, 18.7f);
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
// _0_6
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_6_0
shape = new Ellipse2D.Double(17.600000381469727, 17.600000381469727, 28.799999237060547, 28.799999237060547);
paint = (colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 255)) : new Color(255, 255, 255, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_0
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(33.5f, 13.5f);
generalPath.curveTo(33.5f, 14.3f, 32.8f, 15.0f, 32.0f, 15.0f);
generalPath.lineTo(32.0f, 15.0f);
generalPath.curveTo(31.2f, 15.0f, 30.5f, 14.3f, 30.5f, 13.5f);
generalPath.lineTo(30.5f, 11.5f);
generalPath.curveTo(30.5f, 10.7f, 31.2f, 10.0f, 32.0f, 10.0f);
generalPath.lineTo(32.0f, 10.0f);
generalPath.curveTo(32.8f, 10.0f, 33.5f, 10.7f, 33.5f, 11.5f);
generalPath.lineTo(33.5f, 13.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_1
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(50.5f, 33.5f);
generalPath.curveTo(49.7f, 33.5f, 49.0f, 32.8f, 49.0f, 32.0f);
generalPath.lineTo(49.0f, 32.0f);
generalPath.curveTo(49.0f, 31.2f, 49.7f, 30.5f, 50.5f, 30.5f);
generalPath.lineTo(52.5f, 30.5f);
generalPath.curveTo(53.3f, 30.5f, 54.0f, 31.2f, 54.0f, 32.0f);
generalPath.lineTo(54.0f, 32.0f);
generalPath.curveTo(54.0f, 32.8f, 53.3f, 33.5f, 52.5f, 33.5f);
generalPath.lineTo(50.5f, 33.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_2
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(13.5f, 30.5f);
generalPath.curveTo(14.3f, 30.5f, 15.0f, 31.2f, 15.0f, 32.0f);
generalPath.lineTo(15.0f, 32.0f);
generalPath.curveTo(15.0f, 32.8f, 14.3f, 33.5f, 13.5f, 33.5f);
generalPath.lineTo(11.5f, 33.5f);
generalPath.curveTo(10.7f, 33.5f, 10.0f, 32.8f, 10.0f, 32.0f);
generalPath.lineTo(10.0f, 32.0f);
generalPath.curveTo(10.0f, 31.2f, 10.7f, 30.5f, 11.5f, 30.5f);
generalPath.lineTo(13.5f, 30.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_3
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(24.0f, 15.2f);
generalPath.curveTo(24.4f, 15.9f, 24.2f, 16.8f, 23.5f, 17.2f);
generalPath.lineTo(23.5f, 17.2f);
generalPath.curveTo(22.8f, 17.6f, 21.9f, 17.400002f, 21.5f, 16.7f);
generalPath.lineTo(20.5f, 15.000001f);
generalPath.curveTo(20.1f, 14.300001f, 20.3f, 13.400001f, 21.0f, 13.000001f);
generalPath.lineTo(21.0f, 13.000001f);
generalPath.curveTo(21.7f, 12.600001f, 22.6f, 12.800001f, 23.0f, 13.500001f);
generalPath.lineTo(24.0f, 15.2f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_4
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(48.8f, 24.0f);
generalPath.curveTo(48.1f, 24.4f, 47.2f, 24.2f, 46.8f, 23.5f);
generalPath.lineTo(46.8f, 23.5f);
generalPath.curveTo(46.399998f, 22.8f, 46.6f, 21.9f, 47.3f, 21.5f);
generalPath.lineTo(49.0f, 20.5f);
generalPath.curveTo(49.7f, 20.1f, 50.6f, 20.3f, 51.0f, 21.0f);
generalPath.lineTo(51.0f, 21.0f);
generalPath.curveTo(51.4f, 21.7f, 51.2f, 22.6f, 50.5f, 23.0f);
generalPath.lineTo(48.8f, 24.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_5
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(15.2f, 40.0f);
generalPath.curveTo(15.9f, 39.6f, 16.8f, 39.8f, 17.2f, 40.5f);
generalPath.lineTo(17.2f, 40.5f);
generalPath.curveTo(17.6f, 41.2f, 17.400002f, 42.1f, 16.7f, 42.5f);
generalPath.lineTo(15.000001f, 43.5f);
generalPath.curveTo(14.300001f, 43.9f, 13.400001f, 43.7f, 13.000001f, 43.0f);
generalPath.lineTo(13.000001f, 43.0f);
generalPath.curveTo(12.600001f, 42.3f, 12.800001f, 41.4f, 13.500001f, 41.0f);
generalPath.lineTo(15.2f, 40.0f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_6
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(16.7f, 21.5f);
generalPath.curveTo(17.400002f, 21.9f, 17.7f, 22.8f, 17.2f, 23.5f);
generalPath.lineTo(17.2f, 23.5f);
generalPath.curveTo(16.800001f, 24.2f, 15.900001f, 24.5f, 15.200001f, 24.0f);
generalPath.lineTo(13.500001f, 23.0f);
generalPath.curveTo(12.800001f, 22.6f, 12.500001f, 21.7f, 13.000001f, 21.0f);
generalPath.lineTo(13.000001f, 21.0f);
generalPath.curveTo(13.400001f, 20.3f, 14.300001f, 20.0f, 15.000001f, 20.5f);
generalPath.lineTo(16.7f, 21.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_7
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(47.3f, 42.5f);
generalPath.curveTo(46.6f, 42.1f, 46.3f, 41.2f, 46.8f, 40.5f);
generalPath.lineTo(46.8f, 40.5f);
generalPath.curveTo(47.2f, 39.8f, 48.1f, 39.5f, 48.8f, 40.0f);
generalPath.lineTo(50.5f, 41.0f);
generalPath.curveTo(51.2f, 41.4f, 51.5f, 42.3f, 51.0f, 43.0f);
generalPath.lineTo(51.0f, 43.0f);
generalPath.curveTo(50.6f, 43.7f, 49.7f, 44.0f, 49.0f, 43.5f);
generalPath.lineTo(47.3f, 42.5f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
g.setPaint(paint);
g.fill(shape);
g.setTransform(transformsStack.pop());
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
transformsStack.push(g.getTransform());
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_7_8
if (generalPath == null) {
   generalPath = new GeneralPath();
} else {
   generalPath.reset();
}
generalPath.moveTo(42.5f, 16.7f);
generalPath.curveTo(42.1f, 17.400002f, 41.2f, 17.7f, 40.5f, 17.2f);
generalPath.lineTo(40.5f, 17.2f);
generalPath.curveTo(39.8f, 16.800001f, 39.5f, 15.900001f, 40.0f, 15.200001f);
generalPath.lineTo(41.0f, 13.500001f);
generalPath.curveTo(41.4f, 12.800001f, 42.3f, 12.500001f, 43.0f, 13.000001f);
generalPath.lineTo(43.0f, 13.000001f);
generalPath.curveTo(43.7f, 13.400001f, 44.0f, 14.300001f, 43.5f, 15.000001f);
generalPath.lineTo(42.5f, 16.7f);
generalPath.closePath();
shape = generalPath;
paint = (colorFilter != null) ? colorFilter.filter(new Color(224, 224, 209, 255)) : new Color(224, 224, 209, 255);
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
	private KorellerRCircle_icons_brightness() {
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
       KorellerRCircle_icons_brightness base = new KorellerRCircle_icons_brightness();
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
       KorellerRCircle_icons_brightness base = new KorellerRCircle_icons_brightness();
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
        return KorellerRCircle_icons_brightness::new;
    }
}

