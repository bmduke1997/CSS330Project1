
public class CPU implements Runnable{

    private boolean BusyOrNot;
    public int PC; //Your CPU only has one register PC
    private int timeSlice;
    private Process p;

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

    private Pair execute(Process P){
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

         for (int i = 0; i == P.getBurst().getValue(); i++) BubbleSort((Integer)P.getBurst().getValue());

         BusyOrNot = false;


         return new Pair(0,0); //TODO Make actual return
    }

    public String BubbleSort(int IO_burst){
        double[] unsorted = new double[50000];
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

    public Boolean CPUisBusy() { return BusyOrNot;}


    public void run() {
        execute(p);
    }
}
