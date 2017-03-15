package sample;

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

public class Controller {

    private Line[] lines = new Line[800];
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
    private ChoiceBox<?> choiceBox;

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
    }

    @FXML
    void sort() {
        reset();
        startTime();
        switch ((String)choiceBox.getValue()){
            case "Bubble Sort1": {
                bubbleSort1();
                break;
            }
            case "Bubble Sort2": {
                bubbleSort2();
                break;
            }
            case "Bubble Sort3": {
                bubbleSort3();
                break;
            }
            case "Quick Sort Lomuto": {
                quickSortLomuto();
                break;
            }
            case "Quick Sort Hoare": {
                quickSortHoare();
                break;
            }
            case "Bogo Sort": {
                bogoSort();
                break;
            }
        }
        stopTime();
    }

    private void bubbleSort1(){
        for(int n = lines.length; n>1; n--){
            for(int i=0; i<n-1;i++){
                if(needToSwap(i,i+1)){
                    swap(i,i+1);
                }
            }
        }
    }

    private void bubbleSort2(){
        int n = lines.length;
        boolean swapped;
        do{
            swapped = false;
            for(int i = 0; i<n-1; i++){
                if(needToSwap(i,i+1)){
                    swap(i,i+1);
                    swapped = true;
                }
            }
        }while (swapped);
    }

    private void bubbleSort3(){
        int n = lines.length;

        do{
            int newN = 1;
            for(int i = 0; i<n-1; i++){
                if (needToSwap(i,i+1)){
                    swap(i,i+1);
                    newN = i+1;
                }
            }
            n = newN;
        }while (n>1);
    }

    private void quickSortLomuto(){

    }

    private void quickSortHoare(){

    }

    private void bogoSort(){
        boolean sorted;

        do{
            sorted = true;
            //Compare
            for(int n = 0; n<lines.length -1; n++){
                if(needToSwap(n,n+1)) {
                    sorted = false;
                    break;
                }
            }

            if(!sorted){
                //Scramble
                Random rnd = new Random(System.currentTimeMillis());
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
                swap(rnd.nextInt(800), rnd.nextInt(800));
            }
        }while (!sorted);
    }



    /**
     * @return true when the line 1 is smaller than line 2
     */
    private boolean needToSwap(int index1, int index2){
        comparisons.setText(Integer.toString(Integer.parseInt(comparisons.getText()) + 1));
        return (lines[index1].getEndY() < lines[index2].getEndY());
    }

    private void swap(int index1, int index2){
        Line temp = lines[index1];
        lines[index1] = lines[index2];
        lines[index2] = temp;

        lines[index1].setStartX(index1 + 0.5);
        lines[index1].setEndX(index1 + 0.5);

        lines[index2].setStartX(index2 + 0.5);
        lines[index2].setEndX(index2 + 0.5);

        accesses.setText(Integer.toString(Integer.parseInt(accesses.getText()) + 1));
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

