package Drawing;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;



/**
 * <pre>
 *     Program : ControlDemo
 * @author : Taekyung.Kil
 * Date : 07/Aug/2019
 *
 * Purpose : To set the view and model
 * </pre>
 */
public class ControlDemo extends Application {

    // Layout sizes
    final double SCREEN_WIDTH = 1000;
    final double SCREEN_HEIGHT = 800;
    final double CONTROL_HEIGHT = 100;

    // variables
    private GraphicsContext gc, transgc;
    private double sx, sy;
    private double ex, ey;

    private Color lineColor = Color.BLUE;
    private ColorPicker lineColorPicker;
    private TextField tf;
    private Color borderColor = Color.BLACK;
    private ColorPicker borderPicker;
    private int lineWidth = 5;

    //To check currant shape
    private int shapeNum = 0;

    // Create buttons to use in this class
    Button circleButton = new Button("Circle");
    Button rectangleButton = new Button("Rectangle");
    Button lineButton = new Button("Line");
    //Create the arrayList of GeoObject including circle, line,  rectangle object
    private ArrayList<GeoObject> geo;


    // TODO: Private Event Handlers and Helper Methods
    private void pressHandler(MouseEvent me) {
        sx = me.getX();
        sy = me.getY();
    }
    private void releaseHandler(MouseEvent me) {
        ex = me.getX();
        ey = me.getY();

        // when user click the button , shape(object) is created and then the object is added in geo array
        if(shapeNum == 0) {
            Line l = new Line(sx, sy, ex, ey, lineWidth, lineColorPicker.getValue(),borderPicker.getValue());
            geo.add(l);
            gc.setFill(Color.LIGHTYELLOW);
            gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        }
        else if(shapeNum == 1) {
            Circle c = new Circle(sx, sy, ex, ey, lineWidth, lineColorPicker.getValue(), borderPicker.getValue());
            gc.setFill(Color.LIGHTYELLOW);
            gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            geo.add(c);
        }
        else if(shapeNum ==2){
            Rectangle r = new Rectangle(sx,sy,ex,ey, lineWidth,lineColorPicker.getValue(),borderPicker.getValue());
            gc.setFill(Color.LIGHTYELLOW);
            gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            geo.add(r);
        }
        //Draw each object
        for(int i =0 ; i<geo.size(); i++){
            if (geo.get(i) instanceof Line)
                ((Line) geo.get(i)).draw(gc);

            else if (geo.get(i) instanceof Circle)
                ((Circle) geo.get(i)).draw(gc);
            else if (geo.get(i) instanceof Rectangle)
                ((Rectangle) geo.get(i)).draw(gc);
        }
        transgc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }
    /**
     * This will draw a "Rubberband" line on the transparent surface above the drawing.
     *
     * @param me The mouse drag event - not used in method
     */
    private void dragHandler(MouseEvent me) {
        transgc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        ex = me.getX();
        ey = me.getY();

        //draw object in trangsgc
        if(shapeNum == 0) {
            Line tempLine = new Line(sx, sy, ex, ey, lineWidth,lineColorPicker.getValue(),borderPicker.getValue());
            tempLine.draw(transgc);
        }

        else if(shapeNum == 1) {
            Circle tempCircle = new Circle(sx, sy, ex, ey,lineWidth ,lineColorPicker.getValue(),borderPicker.getValue());
            tempCircle.draw(transgc);
        }
        else if(shapeNum == 2){
            Rectangle tempRectangle = new Rectangle(sx,sy,ex,ey, lineWidth,lineColorPicker.getValue(),borderPicker.getValue());
            tempRectangle.draw(transgc);
        }
    }
    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.DARKGRAY); // set the size here
        stage.setTitle("Drawing and Java FX Controls"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model - No model yet
        geo = new ArrayList<>();

        // 2. Create the GUI components - Just a CANVAS for now
        Canvas c = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT - CONTROL_HEIGHT);
        c.relocate(0, CONTROL_HEIGHT);
        Canvas transc = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        transc.relocate(0, CONTROL_HEIGHT);

        // Add JavaFX controls to top pane ...
        // Line Color picker
        Label colorLabel = new Label("Line Colour");
        colorLabel.relocate(20, 68);
        lineColorPicker = new ColorPicker(lineColor);
        lineColorPicker.relocate(115, 65);

        // border color picker
        Label borderLabel = new Label("Border Line Color");
        borderLabel.relocate(6,23);
        borderPicker = new ColorPicker(borderColor);
        borderPicker.relocate(115,20);

        // Create reset button to use the reset button
        Button resetButton = new Button("Reset Line Colors");
        resetButton.relocate(250, 65);
        resetButton.setOnAction(this::resetHandler);

        // Create new Textfield. It will be border line width
        tf = new TextField("5");
        tf.relocate(600, 22);

        // To draw circles. circle button is already created at the start of the class
        circleButton.relocate(510, 65);
        circleButton.setOnAction(this::circleHandler);

        // To draw lines. line button is already created at the start of the class
        lineButton.relocate(450,65);
        lineButton.setOnAction(this::lineButtonHandler);
        lineButton.setTextFill(Color.RED);

        // To draw rectangles. rectangle button is already created at the start of the class
        rectangleButton.relocate(580,65);
        rectangleButton.setOnAction(this::rectangleButtonHandler);

        // Create new Clear button. It will be used to remove all of the object in the arrayList.
        Button ClearButton = new Button("Clear");
        ClearButton.relocate(850,50);
        ClearButton.setOnAction(this::ClearButtonHandler);

        // Create new Line width button , it will be used to set the line width
        Button lineWidthButton = new Button("Set Line Width");
        lineWidthButton.relocate(500,22);
        lineWidthButton.setOnAction(this::lineWidthHandler);

        // Create new undo button, it will be used to remove the last of the arrayList
        Button undoButton = new Button("Undo");
        undoButton.relocate(850,25);
        undoButton.setOnAction(this::undoButtonHandler);
        // 3. Add components to the root
        root.getChildren().addAll(c, transc, colorLabel, lineColorPicker,
                resetButton, tf, circleButton,lineButton, ClearButton,
                rectangleButton, borderPicker, borderLabel,lineWidthButton, undoButton);

        // Create the two graphics contexts

        gc = c.getGraphicsContext2D();
        transgc = transc.getGraphicsContext2D();

        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        // 5. Add Event Handlers and do final setup
        transc.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        transc.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
        transc.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);
        // 6. Show the stage
        stage.show();
    }

    private void undoButtonHandler(ActionEvent actionEvent) {
        /**
         * When there is nothing in the arrayList, the error message pop up
         * To remove the end of the arrayList. it means the last of user drawing
         */
        try {
            geo.remove(geo.size() - 1);
            gc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            gc.setFill(Color.LIGHTYELLOW);
            gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            for (int i = 0; i < geo.size(); i++) {
                if (geo.get(i) instanceof Line)
                    ((Line) geo.get(i)).draw(gc);
                else if (geo.get(i) instanceof Circle)
                    ((Circle) geo.get(i)).draw(gc);
                else if (geo.get(i) instanceof Rectangle)
                    ((Rectangle) geo.get(i)).draw(gc);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            new Alert(Alert.AlertType.ERROR,
                    "Can not undo anymore").showAndWait();
        }

    }

    /**
     * when the user click this button, lineWidth will be changed as users input
     * @param actionEvent set the lineWidth
     */
    private void lineWidthHandler(ActionEvent actionEvent) {
        // To get the error message. when the user type string value.
        try {
            lineWidth = Integer.parseInt(tf.getText());

        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.ERROR,
                    "Could not convert " + tf.getText() + " to an integer").showAndWait();

        }
    }

    /**
     * To Remove all of shape in the canvas
     * @param actionEvent Remove all of objects in the arrayList
     */
    private void ClearButtonHandler(ActionEvent actionEvent) {
        geo.clear();
        gc.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     * when the user click this button, the line button text color will be changed to show current shape
     * To draw Line
     * @param actionEvent Change the color and shapeNum
     */
    private void lineButtonHandler(ActionEvent actionEvent) {
        lineButton.setTextFill(Color.RED);
        circleButton.setTextFill(Color.BLACK);
        rectangleButton.setTextFill(Color.BLACK);
        shapeNum = 0;
    }

    /**
     * The circle button text color will be changed to show current shape
     * @param actionEvent Change the text color and shapeNum
     */
    private void circleHandler(ActionEvent actionEvent) {
        asdas
                d
                asd
                        asd
                        ad
                                sad
                                a
                                        dsa
                                        das

                                                da
                                                sd
                                                        ad
                                                        sad
                                                                asd
                                                                sa
                                                                        dsa

        circleButton.setTextFill(Color.RED);
        lineButton.setTextFill(Color.BLACK);
        rectangleButton.setTextFill(Color.BLACK);
        shapeNum = 1;
    }

    /**
     * The rectangle button text color will be changed to show current shape
     * @param actionEvent Change the text color and shapeNum
     */
    private void rectangleButtonHandler(ActionEvent actionEvent) {
        lineButton.setTextFill(Color.BLACK);
        circleButton.setTextFill(Color.BLACK);
        rectangleButton.setTextFill(Color.RED);
        shapeNum = 2;
    }

    /**
     * When the user click this button, the color of the shapes will be changed as users picked color
     * this method will re-draw the shapes to change the color
     * @param actionEvent Change the color of the shapes
     */
    private void resetHandler(ActionEvent actionEvent) {
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        for(int i =0 ; i<geo.size(); i++){
            if (geo.get(i) instanceof Line) {
                geo.get(i).setBorderColor(borderPicker.getValue());
                geo.get(i).setLineColor(lineColorPicker.getValue());
                ((Line) geo.get(i)).draw(gc);
            }
            else if (geo.get(i) instanceof Circle) {
                geo.get(i).setBorderColor(borderPicker.getValue());
                geo.get(i).setLineColor(lineColorPicker.getValue());
                ((Circle) geo.get(i)).draw(gc);
            }
            else if(geo.get(i) instanceof Rectangle){
                geo.get(i).setBorderColor(borderPicker.getValue());
                geo.get(i).setLineColor(lineColorPicker.getValue());
                ((Rectangle) geo.get(i)).draw(gc);
            }
        }
    }
    /**
     * Make no changes here.
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
