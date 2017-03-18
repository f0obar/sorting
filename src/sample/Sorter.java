package sample;

import com.sun.javafx.tk.Toolkit;
import javafx.concurrent.Task;
import javafx.scene.shape.Line;

import java.util.Random;

/**
 * Created by Dominic on 16.03.2017.
 */
public class Sorter extends Task{


    public static int[] values = new int[800];
    public static int comparisons = 0;
    public static int accesses = 0;
    public static long time = 0;
    public static Controller controller;
    public static String kek = "";

    public Sorter(Controller controller){
        Sorter.controller = controller;
    }

    @Override
    public void run(){
        comparisons = 0;
        controller.updateComparisons(0);
        accesses = 0;
        controller.updateAccesses(0);
        time = 0;
        controller.updateTime(0);


        switch (kek){
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
    }

    private void bubbleSort1(){
        for(int n = values.length; n>1; n--){
            for(int i=0; i<n-1;i++){
                if(needToSwap(i,i+1)){
                    swap(i,i+1);
                }
            }
        }
    }

    private void bubbleSort2(){
        int n = values.length;
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
        int n = values.length;
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
            for(int n = 0; n<values.length -1; n++){
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


    private boolean needToSwap(int index1, int index2){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        comparisons += 1;
        //controller.updateComparisons(comparisons);
        return (values[index1] < values[index2]);
    }

    private void swap(int index1, int index2){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        accesses+= 1;
        //controller.updateAccesses(accesses);

        int tmp = values[index1];
        values[index1] = values[index2];
        values[index2] = values[tmp];

        controller.lines[index1].endYProperty().set(800 - values[index1] + 0.5);
        controller.lines[index2].endYProperty().set(800 - values[index2] + 0.5);

        //controller.updateLine(index1, values[index1]);
        //controller.updateLine(index2, values[index2]);
    }

    @Override
    protected Object call() throws Exception {
        return null;
    }
}
