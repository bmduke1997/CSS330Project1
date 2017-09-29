import java.io.InputStream;
import java.io.OutputStream;

/**
 * ProcessTable Class
 *
 * @author Brandon Duke
 * @version 9/28/17
 */

public class Process extends java.lang.Process {
    int processId, priority, order;

    public Process(int processId, int priority, int order){
        this.processId = processId;
        this.priority = priority;
        this.order = order;
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
