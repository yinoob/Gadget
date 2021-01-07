package immutable;
//先演示线程争抢带来错误结果，然后把变量放到方法内
public class StackConfinement implements Runnable{
    int index=0;

    public void inThread(){
        int neverGoOut=0;
        synchronized (this) {
            for (int i = 0; i < 10000; i++) {
                neverGoOut++;
            }
        }
        System.out.println("栈内保护的数字是线程安全的"+neverGoOut);
    }
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            index++;
        }
        inThread();
    }

    public static void main(String[] args) throws InterruptedException
    {
        StackConfinement r=new StackConfinement();
        Thread thread1=new Thread(r);
        Thread thread2=new Thread(r);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(r.index);
    }
}
