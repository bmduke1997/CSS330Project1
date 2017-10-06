public class ProcessImage {

    //Process image class of a process including PCB, code, data and stack
    //snapshot before goes to cpu

    private PCB Pcb_data;
    private int[] code ;// this will be the io/cpu burst in the text file
    private int counter = 0;                    //to do: other variables help you computing the latency, response

    public ProcessImage(String process) {

        // split the process string into usable data
        String[] data = process.split(",");

        // extract data
        int id = Integer.parseInt(data[0]);
        int arrivalOrder = Integer.parseInt(data[1]);
        int priority = Integer.parseInt(data[2]);

        // get bursts
        char[] burstData = data[3].toCharArray();
        this.code = new int[burstData.length];
        for (int i=0; i < this.code.length; i ++) this.code[i] = Character.getNumericValue(burstData[i]);

        this.Pcb_data = new PCB(id, priority, State.New, 0, arrivalOrder);


        //set PCB data, code and others
        //set state as "NEW";
    }

    public Pair getBurst(){
        int burstType = counter%2;
        Pair p = new Pair(burstType, code[counter]);
        counter++;

        if (counter == 2) Pcb_data.setFirstIO();

        return p;
    }

    public Pair updateBurst(int timeSlice){
        code[counter - 1] = code[counter - 1] - timeSlice;
        System.out.println(code[counter - 1]);
        return new Pair(0, code[counter - 1]);

    }
    public PCB getPCB() {
        return Pcb_data;
    }
}
