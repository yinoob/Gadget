package cas;

public class SimulatdCAS implements Runnable{
    public volatile int value;

    public synchronized int compAndSwap(int expectedValue,int newValue) {
        int oldValue= value;
        if(oldValue==expectedValue){
            value=newValue;
        }
        return oldValue;
    }

    public static void main(String[] args){


    }

    @Override
    public void run(){
        compAndSwap(0,1);
    }
}
