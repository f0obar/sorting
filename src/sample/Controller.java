package sample;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

import java.util.Random;
import java.util.concurrent.ExecutorService;

public class Controller {

    public Line[] lines = new Line[800];
    private long timeAtStart;


    @FXML
    private Text seedField;

    @FXML
    private Slider delaySlider;

    @FXML
    private CheckBox keepSeed;

    @FXML
    private Text comparisons;

    @FXML
    public ChoiceBox<?> choiceBox;

    @FXML
    private ChoiceBox<?> choiceBoxMode;

    @FXML
    private Text time;

    @FXML
    private Pane pane;

    @FXML
    private Text accesses;

    @FXML
    private Pane drawingPane;

    @FXML
    void scramble() {
        if(!keepSeed.isSelected() || seedField.getText().equals("")){
            seedField.setText(""+System.currentTimeMillis());
        }
        Random generator = new Random(Long.parseLong(seedField.getText()));

        for(Line line : lines){
            drawingPane.getChildren().remove(line);
        }

        switch ((String)choiceBoxMode.getValue()) {
            case "Random" : {
                for (int index = 0; index < 800; index++) {
                    lines[index] = new Line(index + 0.5, 800 + 0.5, index + 0.5, 800 - generator.nextInt(800) + 0.5);
                }
                break;
            }
            case "Solved" : {
                for (int index = 0; index < 800; index++) {
                    lines[index] = new Line(index + 0.5, 800 + 0.5, index + 0.5, 800 - index + 0.5);
                }
                break;
            }
            case "Inverted" : {
                for (int index = 0; index < 800; index++) {
                    lines[index] = new Line(index + 0.5, 800 + 0.5, index + 0.5, index + 0.5);
                }
                break;
            }
            case "Stair Segments" : {
                for (int index = 0; index < 800; index+=10) {
                    int value = generator.nextInt(790);
                    for(int i = 0; i <10; i++){
                        lines[index+i] = new Line(index+i + 0.5, 800 + 0.5, index+i + 0.5, 800 - value - i + 0.5);
                    }
                }
                break;
            }
            case "Inverted Stair Segments" : {
                for (int index = 0; index < 800; index+=10) {
                    int value = generator.nextInt(790) + 10;
                    for(int i = 0; i <10; i++){
                        lines[index+i] = new Line(index+i + 0.5, 800 + 0.5, index+i + 0.5, 800 - value + i + 0.5);
                    }
                }
                break;
            }
            case "Big Stair Segments" : {
                for (int index = 0; index < 800; index+=100) {
                    int value = generator.nextInt(700);
                    for(int i = 0; i <100; i++){
                        lines[index+i] = new Line(index+i + 0.5, 800 + 0.5, index+i + 0.5, 800 - value - i + 0.5);
                    }
                }
                break;
            }
            case "Big Inverted Stair Segments" : {
                for (int index = 0; index < 800; index+=100) {
                    int value = generator.nextInt(700) + 100;
                    for(int i = 0; i <100; i++){
                        lines[index+i] = new Line(index+i + 0.5, 800 + 0.5, index+i + 0.5, 800 - value + i + 0.5);
                    }
                }
                break;
            }
            case "Chunks" : {
                for (int index = 0; index < 800; index+=10) {
                    int value = generator.nextInt(800);
                    for(int i = 0; i <10; i++){
                        lines[index+i] = new Line(index+i + 0.5, 800 + 0.5, index+i + 0.5, 800 - value + 0.5);
                    }
                }
                break;
            }
            case "Big Chunks" : {
                for (int index = 0; index < 800; index+=100) {
                    int value = generator.nextInt(800);
                    for(int i = 0; i <100; i++){
                        lines[index+i] = new Line(index+i + 0.5, 800 + 0.5, index+i + 0.5, 800 - value + 0.5);
                    }
                }
                break;
            }
        }

        for(Line line : lines){
            line.setVisible(true);
            line.setFill(Color.BLACK);
            line.setStrokeWidth(1);
            drawingPane.getChildren().add(line);
        }

        for(int x = 0; x<800; x++){
            Sorter.values[x] = (int) (800 - lines[x].getEndY() - 0.5);
        }
    }

    @FXML
    void sort() {
        Sorter.kek = (String)choiceBox.getValue();
        Sorter sorter = new Sorter(this);
        new Thread(sorter).start();
    }

    public void updateLine(int index, int value){
        lines[index].setEndY(800 - value + 0.5);
    }

    public void updateTime(long value){
        time.setText(Long.toString(value));
    }
    public void updateComparisons(int value){
        comparisons.setText(Integer.toString(value));
    }
    public void updateAccesses(int value){
        accesses.setText(Integer.toString(value));
    }
}

