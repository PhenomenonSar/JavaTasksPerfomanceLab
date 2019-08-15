package dmitriev.tasksForPerfomance;

public class PeriodAndQueue {
    String period;
    double queue;
    public PeriodAndQueue(String curPeriod, double curQueue){
        period = curPeriod;
        queue = curQueue;
    }
    public double getCurQueue(){
        return queue;
    }
}
