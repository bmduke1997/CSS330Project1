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

            while (s2.hasNextLine()){
                Process p = new Process(s2.nextLine());
                New_Queue.add(p);
                process_Table.add(p.getPCB());
            }

            int processNbr = New_Queue.size();
            Ready_Queue = New_Queue; //first up is a cpu burst so go ahead and move them all over
            //change the state
            for (int i = 0; i < Ready_Queue.size(); i++){
                Ready_Queue.get(i).updateBurst();
                Ready_Queue.get(i).updateState(State.Ready);
                int id = Ready_Queue.get(i).getPCB().getId();
                process_Table.update(id, State.Ready);
            }

            switch (choice) {
                case 1:
                    cpu = new CPU();
                    io = new IOdevice();

                    //lets start you genderless people, we don't assume here, what is gender anyway???
                    while(Terminated_Queue.size() != processNbr){
                        if(!cpu.CPUisBusy()) {
                            //get just completed process from the cup
                            try {
                                Process p = cpu.getProcess();
                                cpu.addProcess(null);
                                process_Table.update(p.getPCB().getId(), State.Waiting);

                                //see if the process being pulled off is terminated
                                try{
                                    p.updateBurst();
                                    p.updateState(State.Waiting);
                                    System.out.println("got off of cup" + p.getPCB().getId());
                                    Wait_Queue.add(p);
                                }
                                catch (IndexOutOfBoundsException i){
                                    p.updateState(State.Terminated);
                                    p.getPCB().setEndTime();
                                    Terminated_Queue.add(p);
                                    process_Table.update(p.getPCB().getId(), State.Terminated);
                                    System.out.println("done in cpu if stmt " +  p.getPCB().getId());
                                }
                            }
                            catch (NullPointerException n){}

                            if(!Ready_Queue.isEmpty()) {
                                //putting process on the cpu
                                Process p = Ready_Queue.get(0); //get the first in the queue and process in cpu
                                Ready_Queue.remove(0);

                                //run process
                                p.updateState(State.Running);
                                process_Table.update(p.getPCB().getId(), State.Running);
                                cpu.addProcess(p);
                                System.out.println("New Process on cpu " + p.getPCB().getId());
                                cpu.run();
                            }
                        }

                        if (!io.IOisBusy()){

                            //get just completed process from the io
                            try {
                                Process p = io.getProcess();
                                io.addProcess(null);
                                process_Table.update(p.getPCB().getId(), State.Ready);

                                try {
                                    p.updateBurst();
                                    p.updateState(State.Ready);
                                    System.out.println("got off of io " + p.getPCB().getId());
                                    Ready_Queue.add(p);
                                }
                                catch (IndexOutOfBoundsException i) {
                                    p.updateState(State.Terminated);
                                    p.getPCB().setEndTime();
                                    Terminated_Queue.add(p);
                                    process_Table.update(p.getPCB().getId(), State.Terminated);
                                    System.out.println("done in io if stmt " + p.getPCB().getId());
                                }
                            }
                            catch (NullPointerException n){}

                            if (!Wait_Queue.isEmpty()) {
                                //put process on the io
                                Process p = Wait_Queue.get(0);
                                Wait_Queue.remove(0); //get first in queue

                                //run
                                p.updateState(State.Running);
                                process_Table.update(p.getPCB().getId(), State.Running);
                                io.addProcess(p);
                                System.out.println("New Process on io " + p.getPCB().getId());
                                io.run();
                            }
                        }
                    }

                    String vals = "";
                    for (Process process : Terminated_Queue){
                        vals += String.valueOf(process.getPCB().getId()) + ", ";
                    }

                    System.out.println("ready queue " + Ready_Queue.toString());
                    System.out.println("waiting queue " + Wait_Queue.toString());
                    System.out.println("Terminated " + vals);
                    System.out.println("DOME");

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    done = true;
                    break;
                default:
                    System.out.println("Wrong input, try again");
            }
        }
    }
}
