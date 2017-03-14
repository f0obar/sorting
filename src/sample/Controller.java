package sample;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;

import java.util.Random;

public class Controller {

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
    void scramble() {
        if(!keepSeed.isSelected() || seedField.getText().equals("")){
            seedField.setText(""+System.currentTimeMillis());
        }
        Random generator = new Random(Long.parseLong(seedField.getText()));

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

