package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//利用ThreadLocal,给每个线程分配自己的ThreadLocal对象
public class ThreadLocalNormal05 {
    public static ExecutorService threadPool=
            Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<1000;i++){
            int finalI=i;
            threadPool.submit(new Runnable() {
            @Override
            public void run() {
               String date= new ThreadLocalNormal05().date(finalI);
                System.out.println(date);
            }
        });

        }
        threadPool.shutdown();
    }

    public String date(int seconds){
        Date date =new Date(1000*seconds);
        //SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat dateFormat=ThreadSafeFormatter.dateFormatThreadLocal.get();
        return dateFormat.format(date);
    }
}
class ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat>
    dateFormatThreadLocal=new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue(){
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
    };

}