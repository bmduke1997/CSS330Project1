import java.util.ArrayList;

public class ProcessImage {

    //Process image class of a process including PCB, code, data and stack
    //snapshot before goes to cpu

    public PCB Pcb_data;
    public String code ;// this will be the io/cpu burst in the text file
                        //to do: other variables help you computing the latency, response

    public ProcessImage(String process) {

        // split the process string into usable data
        String[] data = process.split(",");

        // extract data
        int id = Integer.parseInt(data[0]);
        int arrivalOrder = Integer.parseInt(data[1]);
        int priority = Integer.parseInt(data[2]);

        // get bursts
        char[] burstData = data[3].toCharArray();
        int[] bursts = new int[burstData.length];
        for (int i=0; i < bursts.length; i ++) bursts[i] = Character.getNumericValue(burstData[i]);




        //set PCB data, code and others
        //set state as "NEW";
    }
}
