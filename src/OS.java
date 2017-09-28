import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OS {

    public CPU cpu;
    public IOdevice io;
    public boolean isCPUAvailable;
    public ProcessTable process_Table; //TODO what is the process table???
    public ArrayList<Process> New_Queue;
    public ArrayList<Process> Ready_Queue;
    public ArrayList<Process> Wait_Queue;
    public ArrayList<Process> Terminated_Queue;
    /*
     //Read the txt input file, for each line, create a process and record its arrival
    time
     //Put each process in New_Q queue initially then put them in Ready_Q
     //Always check whether the CPU is idle or not; if yes, use your scheduler
    algorithm to select a process from the Ready_Queue for CPU execution\
     // According to the return value of CPU execute(), put the process into the
    corresponding queue.
     //Record the time of every operation for computing your latency and
    resp
     */

    public static void main (String[] args) throws FileNotFoundException{
        Scanner s = new Scanner(new File("processes.csv"));




    }
}
