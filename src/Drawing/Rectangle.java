package Drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * <pre>
 *     Program : Rectangle
 * @author : Taekyung.Kil
 * Date : 07/Aug/2019
 *
 * Purpose : To draw the rectangle as sub class
 * </pre>
 */
public class Rectangle extends GeoObject implements Draw{

    public Rectangle(double sx, double sy, double ex, double ey,int lineWidth, Color lineColor, Color borderColor){
        super(sx, sy, ex, ey, lineWidth,lineColor,borderColor);
    }

    public void draw(GraphicsContext gc){
        gc.setFill(getLineColor());
        gc.setLineWidth(getLineWidth());
        gc.setStroke(getBorderColor());

        /**
         * It is necessary because it has to be drawn in all directions
         */
        if(getSx()<getEx() && getSy() < getEy()) {
            gc.strokeRect(getSx(), getSy(), getEx() - getSx(), getEy() - getSy());
            gc.fillRect(getSx(), getSy(), getEx() - getSx(), getEy() - getSy());
        }
        else if(getSx()<getEx() && getSy() > getEy()) {
            gc.strokeRect(getSx(), getEy(), getEx() - getSx(), getSy() - getEy());
            gc.fillRect(getSx(), getEy(), getEx() - getSx(), getSy() - getEy());
        }
        else if(getSx() > getEx() && getSy() > getEy()) {
            gc.strokeRect(getEx(), getEy(), getSx() - getEx(), getSy() - getEy());
            gc.fillRect(getEx(), getEy(), getSx() - getEx(), getSy() - getEy());
        }
        else if(getSx() > getEx() && getSy() < getEy()) {
            gc.strokeRect(getEx(), getSy(), getSx() - getEx(), getEy() - getSy());
            gc.fillRect(getEx(), getSy(), getSx() - getEx(), getEy() - getSy());
        }

    }
}
