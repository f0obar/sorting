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

    }

    @FXML
    void algo2() {

    }

    @FXML
    void algo3() {

    }

    @FXML
    void algo4() {

    }

}

