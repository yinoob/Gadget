package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//1000个打印日期的任务,用线程池来执行
public class ThreadLocalNormal02 {
    public static ExecutorService threadPool=
            Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for(int i=1;i<1000;i++){
            int finalI=i;
            threadPool.submit(new Runnable() {
            @Override
            public void run() {
               String date= new ThreadLocalNormal02().date(finalI);
                System.out.println(date);
            }
        });

        }
        threadPool.shutdown();
    }

    public String date(int seconds){
        Date date =new Date(1000*seconds);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}