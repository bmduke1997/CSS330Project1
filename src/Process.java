import java.io.InputStream;
import java.io.OutputStream;

/**
 * ProcessTable Class
 *
 * @author Brandon Duke
 * @version 9/28/17
 */

public class Process extends java.lang.Process implements Comparable {
    private int processId, priority, order;
    private ProcessImage pi;
    private Pair pair;

    public Process(String process){
        this.pi = new ProcessImage(process);
        this.processId = this.pi.getPCB().getId();
        this.priority = this.pi.getPriority();
    }

    public void getNewBurst(){
        pair = pi.getBurst();
    }

    public void updateBurst(int timeslice){
        pair = pi.updateBurst(timeslice);

    }

    public void updateState(State s){
        pi.getPCB().setState(s);
    }

    public Pair getBurst(){
        return pair;
    }

    public PCB getPCB() {
        return pi.getPCB();
    }

    public OutputStream getOutputStream() {
        return null;
    }

    public InputStream getInputStream() {
        return null;
    }

    public InputStream getErrorStream() {
        return null;
    }

    public int waitFor() throws InterruptedException {
        return 0;
    }

    public int exitValue() {
        return 0;
    }

    public void destroy() {

    }

    public int compareTo(Object o) {
        if (o instanceof Process){
            if(this.priority < ((Process) o).priority) return 1;
            else if (this.priority == ((Process) o).priority) return 0;
            else return -1;
        }
        return 0;
    }
}
