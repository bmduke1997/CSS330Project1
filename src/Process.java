import java.io.InputStream;
import java.io.OutputStream;

/**
 * ProcessTable Class
 *
 * @author Brandon Duke
 * @version 9/28/17
 */

public class Process extends java.lang.Process {
    private int processId, priority, order;
    private ProcessImage pi;
    private Pair pair;

    public Process(String process){
        this.pi = new ProcessImage(process);
    }

    public void updateBurst(){
        pair = pi.getBurst();
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
}
