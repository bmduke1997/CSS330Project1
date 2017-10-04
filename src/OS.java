import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OS {

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

        CPU cpu;
        IOdevice io;
        boolean isCPUAvailable;
        ProcessTable process_Table = new ProcessTable(); //TODO what is the process table???
        ArrayList<Process> New_Queue = new ArrayList<Process>();
        ArrayList<Process> Ready_Queue = new ArrayList<Process>();
        ArrayList<Process> Wait_Queue = new ArrayList<Process>();
        ArrayList<Process> Terminated_Queue = new ArrayList<Process>();

        Scanner s2 = new Scanner(new File("processes.csv")), s = new Scanner(System.in);
        boolean done = false;
        while (!done) {
        System.out.println("OS Simulation!!!\n1. First Come First Serve\n2. Round Robbin\n3. Static Priority\n4. Exit");
        System.out.print("> ");

        int choice = Integer.parseInt(s.nextLine());

            while (s.hasNextLine()){
                Process p = new Process(s.nextLine());
                New_Queue.add(p);
                process_Table.add(p.getPCB());
            }

            Ready_Queue = New_Queue; //first up is a cpu burst so go ahead and move them all over
            //change the state
            for (int i = 0; i < Ready_Queue.size(); i++){
                Ready_Queue.get(i).updateBurst(State.Ready);
                int id = Ready_Queue.get(i).getPCB().getId();
                process_Table.update(id, State.Ready);
            }

            switch (choice) {
                case 1:
                    cpu = new CPU();
                    io = new IOdevice();

                    //lets start you genderless people, we don't assume here, what is gender anyway???
                    while(!Ready_Queue.isEmpty() &&  !Wait_Queue.isEmpty()){
                        Process p = Ready_Queue.get(0); //get the first in the queue and process in cpu
                        Ready_Queue.remove(0);

                    }

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    done = true;
                    break;
                default:
                    System.out.println("***k pleassese try again");
            }
        }













    }
}
