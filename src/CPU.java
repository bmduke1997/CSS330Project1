
public class CPU implements Runnable{

    private boolean BusyOrNot;
    public int PC; //Your CPU only has one register PC
    private int timeSlice = -1;
    private Process p;
    private long processNumber = 0;
    private long time = 0;

    public CPU(int settimeslice) {
        this.timeSlice= settimeslice;
        this.BusyOrNot=false;
    }

    public CPU(){
        this.BusyOrNot = false;
    }

    public void addProcess(Process p){
        this.p = p;
    }

    public Process getProcess() {
        return p;
    }

    public double getThroughput(){
        return (double)processNumber/(double)time;
    }

    private Pair execute(Process P){
        long start = System.currentTimeMillis();
        BusyOrNot=true;
         /* read the CPU burst number, say #, from the position
        PositionOfNextInstructionToExecute of P.
         Repeat calling Bubble Sort() for # times and then continue.
         If the code runs out, return (PositionOfNextInstructionToExecute,
        “terminated”), then OS put it back to the terminated queue.

         If the slice of time (restricted number of calling Bubble sort() for a
        process each time) runs out, return (PositionOfNextInstructionToExecute+1,
        “ready”), then OS put it back to the ready queue.
         Otherwise, return (PositionOfNe//new Scanner(new File("processes.csv"));xtInstructionToExecute+1, “wait”)
        (namely, P has an I/O request and then OS remove it from the ready queue
        and sent it to I/O queue)
        */
        int runs = 0;
        if (timeSlice != -1) {
            runs = timeSlice;
            P.updateBurst(timeSlice);
        }
        else{
            runs = P.getBurst().getValue();
        }
        for (int k = 0; k <= runs; k++){
            BubbleSort bubbleSort = new BubbleSort();
            bubbleSort.run();
         }

         BusyOrNot = false;

         time += System.currentTimeMillis() - start;


         return new Pair(0,0);
    }

    public Boolean CPUisBusy() { return BusyOrNot;}


    public void run() {
        execute(p);
        processNumber ++;
    }
}
