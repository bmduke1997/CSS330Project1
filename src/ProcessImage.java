import java.util.ArrayList;

public class ProcessImage {

    //Process image class of a process including PCB, code, data and stack

    public PCB Pcb_data;
    public String code ;// this will be the io/cpu burst in the text file
                        //to do: other variables help you computing the latency, response

    public ProcessImage(String process) {

        String[] data = process.split(",");
        int id = Integer.parseInt(data[0]);
        int arrivalOrder = Integer.parseInt(data[1]);
        int priority = Integer.parseInt(data[2]);



        //set PCB data, code and others
        //set state as "NEW";
    }
}
