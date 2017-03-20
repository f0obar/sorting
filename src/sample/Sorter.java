package sample;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.shape.Line;

import java.util.Random;

/**
 * Created by Dominic on 16.03.2017.
 */
public class Sorter extends Task{


    public int[] values = new int[800];
    public int comparisons = 0;
    public int accesses = 0;
    public long time = 0;
    public Controller controller;

    public Sorter(Controller controller){
        this.controller = controller;
    }

    @Override
    public void run(){
        Platform.runLater(() -> controller.updateComparisons(0));
        Platform.runLater(() -> controller.updateTime(0));
        Platform.runLater(() -> controller.updateAccesses(0));

        time = System.currentTimeMillis();

        switch ((String)controller.choiceBox.getValue()){
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
                quickSortLomuto(0, values.length -1);
                break;
            }
            case "Quick Sort Hoare": {
                quickSortHoare(0, values.length -1);
                break;
            }
            case "Bogo Sort": {
                bogoSort();
                break;
            }
            case "Insertion Sort":{
                insertionSort();
                break;
            }
            case "Smooth Sort":{
                insertionSort();
                break;
            }
            case "Heap Sort":{
                insertionSort();
                break;
            }
            case "Merge Sort":{
                insertionSort();
                break;
            }
        }
        Platform.runLater(() -> controller.updateTime(System.currentTimeMillis() - time));
    }

    private void bubbleSort1(){
        for(int n = values.length; n>1; n--){
            for(int i=0; i<n-1;i++){
                comparison();
                if(values[i] > values[i + 1]){
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
                comparison();
                if(values[i] > values[i+1]){
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
                comparison();
                if (values[i] > values[i+1]){
                    swap(i,i+1);
                    newN = i+1;
                }
            }
            n = newN;
        }while (n>1);
    }

    private void quickSortHoare(int lo, int hi){
        comparison();
        if(lo < hi){
            int p = partitionHoare(lo, hi);
            quickSortHoare(lo, p);
            quickSortHoare(p+1,hi);
        }
    }

    private int partitionHoare(int lo, int hi){
        int pivot = values[lo];
        int i = lo -1;
        int j = hi + 1;

        while (true){
            do{
                i+=1;
                comparison();
            } while (values[i] < pivot);

            do{
                j-=1;
                comparison();
            } while (values[j] > pivot);

            comparison();
            if(i >= j)
                return j;

            swap(i,j);
        }
    }

    private void quickSortLomuto(int lo, int hi){
        comparison();
        if(lo < hi){
            int p = partitionHoare(lo, hi);
            quickSortHoare(lo, p - 1);
            quickSortHoare(p+1,hi);
        }
    }

    private int partitionLomuto(int lo, int hi){
        int pivot = values[hi];
        int i = lo -1;
        for(int j = lo; j <= hi-1;j++){
            comparison();
            if(values[j] < pivot){
                i+=1;
                swap(i,j);
            }
        }
        swap(i+1,hi);
        return i+1;
    }

    private void bogoSort(){
        boolean sorted;

        do{
            sorted = true;
            //Compare
            for(int i = 0; i<values.length -1; i++){
                comparison();
                if(values[i] < values[i+1]) {
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

    private void insertionSort(){
        for(int i = 1; i< values.length; i++){
            int j = i;
            while(j > 0){
                comparison();
                if(values[j-1] > values[j]) {
                    swap(j - 1, j);
                    j -= 1;
                } else {
                    break;
                }
            }
        }
    }


    private void comparison(){
        if(this.isCancelled()){
            this.done();
        }
        comparisons += 1;
        Platform.runLater(() -> controller.updateComparisons(comparisons));

        long delay = System.nanoTime();
        //active waiting because of windows scheduler reasons.
        while (System.nanoTime() < (delay + controller.delaySlider.getValue()*10000)){}
    }

    private void swap(int index1, int index2){
        long delay = System.nanoTime();
        //active waiting because of windows scheduler reasons.
        while (System.nanoTime() < (delay + controller.delaySlider.getValue()*10000)){}

        accesses+= 1;
        Platform.runLater(() -> controller.updateAccesses(accesses));

        int tmp = values[index1];
        values[index1] = values[index2];
        values[index2] = tmp;

        Platform.runLater(() -> controller.updateLine(index1, values[index1]));
        Platform.runLater(() -> controller.updateLine(index2, values[index2]));


    }


    @Override
    protected Object call() throws Exception {
       return null;
    }
}
