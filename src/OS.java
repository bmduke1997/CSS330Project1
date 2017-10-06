import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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

        Scanner s = new Scanner(System.in);
        boolean done = false;
        while (!done) {
        System.out.println("OS Simulation!!!\n1. First Come First Serve\n2. Round Robbin\n3. Static Priority\n4. Exit");
        System.out.print("> ");

        double throughput = 0;

        int choice = Integer.parseInt(s.nextLine());
            Scanner s2 = new Scanner(new File("processes.csv"));
            while (s2.hasNextLine()){
                Process p = new Process(s2.nextLine());
                New_Queue.add(p);
                process_Table.add(p.getPCB());
            }

            int processNbr = New_Queue.size();
            Ready_Queue = New_Queue; //first up is a cpu burst so go ahead and move them all over
            //change the state
            for (int i = 0; i < Ready_Queue.size(); i++){
                Ready_Queue.get(i).getNewBurst();
                Ready_Queue.get(i).updateState(State.Ready);
                int id = Ready_Queue.get(i).getPCB().getId();
                process_Table.update(id, State.Ready);
            }

            switch (choice) {
                case 1:
                    System.out.println("Starting First Come First Serve");
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
                                    p.getNewBurst();
                                    p.updateState(State.Waiting);
                                    Wait_Queue.add(p);
                                }
                                catch (IndexOutOfBoundsException i){
                                    p.updateState(State.Terminated);
                                    p.getPCB().setEndTime();
                                    Terminated_Queue.add(p);
                                    process_Table.update(p.getPCB().getId(), State.Terminated);
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
                                    p.getNewBurst();
                                    p.updateState(State.Ready);
                                    Ready_Queue.add(p);
                                }
                                catch (IndexOutOfBoundsException i) {
                                    p.updateState(State.Terminated);
                                    p.getPCB().setEndTime();
                                    Terminated_Queue.add(p);
                                    process_Table.update(p.getPCB().getId(), State.Terminated);
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
                                io.run();
                            }
                        }
                    }
                    throughput = cpu.getThroughput();

                    break;

                case 2:
                    System.out.println("Starting Round Robbin");
                    cpu = new CPU(2);
                    io = new IOdevice();

                    //lets start you genderless people, we don't assume here, what is gender anyway???
                    while(Terminated_Queue.size() != processNbr){
                        if(!cpu.CPUisBusy()) {
                            //get just completed process from the cup
                            try {
                                Process p = cpu.getProcess();
                                cpu.addProcess(null);
                                if(p.getBurst().getValue() > 0){
                                    Ready_Queue.add(p);
                                }
                                else {
                                    process_Table.update(p.getPCB().getId(), State.Waiting);

                                    //see if the process being pulled off is terminated
                                    try {
                                        p.getNewBurst();
                                        p.updateState(State.Waiting);
                                        Wait_Queue.add(p);
                                    } catch (IndexOutOfBoundsException i) {
                                        p.updateState(State.Terminated);
                                        p.getPCB().setEndTime();
                                        Terminated_Queue.add(p);
                                        process_Table.update(p.getPCB().getId(), State.Terminated);
                                    }
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
                                    p.getNewBurst();
                                    p.updateState(State.Ready);
                                    Ready_Queue.add(p);
                                }
                                catch (IndexOutOfBoundsException i) {
                                    p.updateState(State.Terminated);
                                    p.getPCB().setEndTime();
                                    Terminated_Queue.add(p);
                                    process_Table.update(p.getPCB().getId(), State.Terminated);
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
                                io.run();
                            }
                        }
                    }
                    throughput = cpu.getThroughput();

                    break;
                case 3:
                    System.out.println("Starting Static Priority");
                    cpu = new CPU();
                    io = new IOdevice();

                    Collections.sort(Ready_Queue);
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
                                    p.getNewBurst();
                                    p.updateState(State.Waiting);
                                    Wait_Queue.add(p);
                                    Collections.sort(Wait_Queue);
                                }
                                catch (IndexOutOfBoundsException i){
                                    p.updateState(State.Terminated);
                                    p.getPCB().setEndTime();
                                    Terminated_Queue.add(p);
                                    process_Table.update(p.getPCB().getId(), State.Terminated);
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
                                    p.getNewBurst();
                                    p.updateState(State.Ready);
                                    Ready_Queue.add(p);
                                    Collections.sort(Ready_Queue);
                                }
                                catch (IndexOutOfBoundsException i) {
                                    p.updateState(State.Terminated);
                                    p.getPCB().setEndTime();
                                    Terminated_Queue.add(p);
                                    process_Table.update(p.getPCB().getId(), State.Terminated);
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
                                io.run();
                            }
                        }
                    }
                    throughput = cpu.getThroughput();
                    break;
                case 4:
                    done = true;
                    break;
                default:
                    System.out.println("Wrong input, try again");
                    break;

            }
            if (!Terminated_Queue.isEmpty()){ // if we have data in the terminated que
                ArrayList<Long> latency = new ArrayList<>();
                ArrayList<Long> responceTime = new ArrayList<>();

                for (Process x: Terminated_Queue){
                    latency.add(x.getPCB().getEndTime() - x.getPCB().getArrivalTime());
                    responceTime.add(x.getPCB().getFirstIO() - x.getPCB().getArrivalTime());
                }
                Toolset toolset = new Toolset(latency);
                double stdLatency = toolset.stdDeviation();
                double avgLatency = toolset.mean();
                double minLatency = toolset.getMin();
                double maxLatency = toolset.getMax();

                System.out.println("\nstdLat: " + stdLatency + " avgLat: " + avgLatency + " minLat: " + minLatency + " maxLat: " + maxLatency);
                toolset = new Toolset(responceTime);

                double stdres = toolset.stdDeviation();
                double avgres = toolset.mean();
                double minres = toolset.getMin();
                double maxres = toolset.getMax();

                System.out.println("stdRes: " + stdres + " avgRes: " + avgres + " minRes: " + minres + " maxRes: " + maxres);

                System.out.println("Throughput: " + throughput + "\n");
            }

            Terminated_Queue = new ArrayList<>(); // empty queues.
        }
    }
}
