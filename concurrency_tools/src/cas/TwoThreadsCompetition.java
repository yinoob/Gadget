package cas;

public class TwoThreadsCompetition implements Runnable{
    public volatile int value;

    public synchronized int compAndSwap(int expectedValue,int newValue) {
        int oldValue= value;
        if(oldValue==expectedValue){
            value=newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException{
        TwoThreadsCompetition r=new TwoThreadsCompetition();
        r.value=0;
        Runnable target;
        Thread t1=new Thread(r);
        Thread t2=new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);

    }

    @Override
    public void run(){
        compAndSwap(0,1);
    }
}
