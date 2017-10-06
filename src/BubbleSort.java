public class BubbleSort {
    public  BubbleSort(){
        double[] unsorted = new double[5000000];
        for(int i = 0;i<50000; i++) unsorted[i] = Math.random();
        for(int i = 0; i < unsorted.length; i++) {
            for(int j = 0; j < unsorted.length; j++) {
                if(unsorted[i] < unsorted[j]) {
                    double temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }
    }
}
