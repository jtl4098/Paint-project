package Drawing;

import javafx.scene.paint.Color;

/**
 * <pre>
 *     Program : GeoObject
 * @author : Taekyung.Kil
 * Date : 07/Aug/2019
 *
 * Purpose : To set the all of variables for drawing the shapes as abstract class
 * </pre>
 */
public abstract class GeoObject {
    private double sx,sy;
    private double ex,ey;
    private Color lineColor;
    private Color borderColor;
    private int lineWidth;

    public GeoObject(double sx, double sy, double ex, double ey,int lineWidth, Color lineColor, Color borderColor) {
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
        this.lineWidth = lineWidth;
        this.lineColor = lineColor;
        this.borderColor =borderColor;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public double getSx() {
        return sx;
    }

    public double getSy() {
        return sy;
    }

    public double getEx() {
        return ex;
    }

    public double getEy() {
        return ey;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }
}
