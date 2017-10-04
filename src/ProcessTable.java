import com.sun.xml.internal.bind.v2.TODO;


import java.util.ArrayList;

/**
 * ProcessTable Class
 *
 * @author Patrick Shinn
 * @version 9/28/17
 */

/*
    take in a array of PCB and
 */
public class ProcessTable {
    private ArrayList<PCB> PCBTable = new ArrayList<PCB>();

    public ProcessTable(){

    }

    public void add(PCB pcb) {
        PCBTable.add(pcb);
    }

    public PCB search(int id){
        PCB newPCB = PCBTable.get(0);
        for(int i = 0; i < PCBTable.size();i++)
        {
            if(id == PCBTable.get(i).getId())
            {
                newPCB = PCBTable.get(i);
            }
        }
        return newPCB;
    }

    public void update(int id, State s){
        for(int i = 0; i < PCBTable.size();i++)
        {
            if(id == PCBTable.get(i).getId())
            {
                PCBTable.get(i).setState(s);
            }
        }
    }
}
