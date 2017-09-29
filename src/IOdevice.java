import java.util.ArrayList;

public class IOdevice {

    private double[] unsorted = new double[50000];
    public IOdevice(ArrayList<Process> Wait_Queue){

    }

    public boolean BusyOrNot;

    //Always pick one process from Wait_Queue to exeute
    public String execute(int IO_burst) {
        BusyOrNot = true;
        return "TODO actual return"; //TODO
    }
    //Call Bubble Sort() for IO_burst times and then return “ready”;

    public String BubbleSort(int IO_burst){
        for(int i = 0;i<50000; i++) unsorted[i] = Math.random();
        for(int i = 0; i < IO_burst; i++) {
            for(int j = 0; j < IO_burst; j++) {
                if(unsorted[i] < unsorted[j]) {
                    double temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }

        return "ready";
    }

}
