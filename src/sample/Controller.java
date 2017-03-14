package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.Random;

public class Controller {

    private Line[] lines = new Line[800];
    private long timeAtStart;

    @FXML
    private Pane drawingPane;

    @FXML
    private Text seedField;

    @FXML
    private CheckBox keepSeed;

    @FXML
    private Text comparisons;

    @FXML
    private Text time;

    @FXML
    private Text accesses;

    @FXML
    private Pane pane;

    @FXML
    void scramble() {
        if(!keepSeed.isSelected() || seedField.getText().equals("")){
            seedField.setText(""+System.currentTimeMillis());
        }
        Random generator = new Random(Long.parseLong(seedField.getText()));



        for(int index = 0; index < 800; index ++){
            drawingPane.getChildren().remove(lines[index]);
            lines[index] = new Line(index, 800, index, 800- generator.nextInt(800));
            lines[index].setVisible(true);
            lines[index].setStroke(Color.BLACK);
        }

        for(Line line : lines){
            drawingPane.getChildren().add(line);
        }
    }

    @FXML
    void algo1() {
        reset();
        startTime();


        stopTime();
    }

    @FXML
    void algo2() {
        reset();
        startTime();


        stopTime();
    }

    @FXML
    void algo3() {
        reset();
        startTime();


        stopTime();
    }

    @FXML
    void algo4() {
        reset();
        startTime();



        stopTime();
    }

    /**
     * @return true when the line 1 is smaller than line 2
     */
    private synchronized boolean compare(int index1, int index2){
        comparisons.setText("" + Integer.parseInt(comparisons.getText()) + 1);
        return (lines[index1].getEndY() > lines[index2].getEndY());
    }

    private synchronized void swap(int index1, int index2){
        Line temp = lines[index1];
        lines[index1] = lines[index2];
        lines[index2] = temp;
        accesses.setText("" + Integer.parseInt(accesses.getText()) + 1);
    }

    private void reset(){
        comparisons.setText("0");
        accesses.setText("0");
        time.setText("0");
    }

    private void startTime(){
        timeAtStart = System.currentTimeMillis();
    }

    private void stopTime(){
        time.setText("" + (System.currentTimeMillis() - timeAtStart));
    }

}

