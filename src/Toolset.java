import java.util.ArrayList;
import java.util.Arrays;

/**
 * Toolset Class
 *
 * @author Patrick Shinn
 * @version 8/29/17
 */
public class Toolset {

    private ArrayList<Long> data = new ArrayList<>();

    public Toolset(ArrayList<Long> Data) {
        this.data = Data;
    }



    public double stdDeviation(){
        double mean = mean(); // get the mean
        double sd = 0;
        for (double i : data) sd += Math.pow((i - mean), 2); // sum the values in accordance with standard dev
        sd = sd * (1.0/data.size());
        
        return Math.sqrt(sd); // return final standard dev
    }

    private double stdDeviation(ArrayList<Double> DATA){
        double mean = mean(); // get the mean
        double sd = 0;
        for (double i : DATA) sd += Math.pow((i - mean), 2); // sum the values in accordance with standard dev
        sd = sd * (1.0/DATA.size());

        return Math.sqrt(sd); // return final standard dev
    }


    public double mean(){ // basic mean, add all and divide by number of items added.
        double sum = 0;
        for (double i: data){
            sum += i;
        }
        return sum/data.size();
    }

    public double getMax(){
        double max = data.get(0);
        for (double i: data){
            if (i > max) max = i;
        }
        return max;
    }

    public double getMin(){
        double min = data.get(0);
        for (double i: data){
            if (i < min) min = i;
        }
        return min;
    }

    private double mean(ArrayList<Double> DATA){ // basic mean, add all and divide by number of items added.
        double sum = 0;
        for (double i: DATA){
            sum += i;
        }
        return sum/DATA.size();
    }

    public double time(){
        double time = System.currentTimeMillis(); // gets the current time before running merge sort
        Arrays.sort(data.toArray()); // merge sort the data
        time = System.currentTimeMillis() - time; // get the time it took to run sort in milliseconds
        return time;
    }
}
