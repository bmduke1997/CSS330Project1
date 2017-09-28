public class PCB {

    private int id;
    private long arrivalTime;
    private State state;
    private int pcVal;

    public PCB(int id, long arrivalTime, State state, int pcVal){
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.state = state;
        this.pcVal = pcVal;
    }

    public int getId() {
        return id;
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
