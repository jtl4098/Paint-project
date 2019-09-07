package Drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * <pre>
 *     Program : Circle
 * @author : Taekyung.Kil
 * Date : 07/Aug/2019
 *
 * Purpose : To draw the circle as sub class
 * </pre>
 */
public class Circle extends GeoObject implements Draw{
    public Circle(double sx, double sy, double ex, double ey,int lineWidth, Color lineColor, Color borderColor) {
        super(sx, sy, ex, ey, lineWidth,lineColor, borderColor);
    }

    public void draw(GraphicsContext gc){
        gc.setFill(getLineColor());
        gc.setLineWidth(getLineWidth());
        gc.setStroke(getBorderColor());

        /**
         * It is necessary because it has to be drawn in all directions
         */
        if(getSx()<getEx() && getSy() < getEy()) {
            gc.strokeOval(getSx(), getSy(), getEx() - getSx(), getEy() - getSy());
            gc.fillOval(getSx(), getSy(), getEx() - getSx(), getEy() - getSy());
        }
        else if(getSx() < getEx() && getSy() > getEy()){
            gc.strokeOval(getSx(), getEy(), getEx() - getSx(), getSy() - getEy());
            gc.fillOval(getSx(), getEy(), getEx() - getSx(), getSy() - getEy());
        }
        else if(getSx() > getEx() && getSy()> getEy()){
            gc.strokeOval(getEx(), getEy(), getSx() - getEx(), getSy() - getEy());
            gc.fillOval(getEx(), getEy(), getSx() - getEx(), getSy() - getEy());
        }
        else if(getSx() > getEx() && getSy()< getEy()){
            gc.strokeOval(getEx(), getSy(), getSx() - getEx(), getEy() - getSy());
            gc.fillOval(getEx(), getSy(), getSx() - getEx(), getEy() - getSy());
        }
    }
}
