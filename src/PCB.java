public class PCB {

    private int id, priority, arrivalOrder;
    private long arrivalTime;
    private State state;
    private int pcVal;

    public PCB(int id, int priority, State state, int pcVal, int arrivalOrder){
        this.id = id;
        this.priority = priority;
        this.arrivalTime = System.currentTimeMillis();
        this.state = state;
        this.pcVal = pcVal;
        this.arrivalOrder = arrivalOrder;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public int getPcVal() {
        return pcVal;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    //To do: PCB data structure of a process
    //for example: Process_id, Arrive_time, state,
    //PositionOfNextInstructionToExecute(PC value)
    //and so on



}
