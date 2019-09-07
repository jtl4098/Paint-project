package Drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <pre>
 *     Program : Line
 * @author : Taekyung.Kil
 * Date : 07/Aug/2019
 *
 * Purpose : To draw the line as sub class
 * </pre>
 */
public class Line extends GeoObject implements Draw{

    public Line(double sx, double sy, double ex, double ey,int lineWidth, Color lineColor, Color borderColor){
        super(sx,sy,ex,ey,lineWidth,lineColor,borderColor);
    }


    /**
     * It is necessary because it has to be drawn in all directions
     */
    public void draw(GraphicsContext gc){
        gc.setStroke(getLineColor());
        gc.setLineWidth(getLineWidth());
        gc.strokeLine(getSx(), getSy(), getEx(), getEy());
    }
}
