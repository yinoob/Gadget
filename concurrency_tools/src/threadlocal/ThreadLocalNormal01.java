package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
//十个线程打印日期
public class ThreadLocalNormal01 {

    public static void main(String[] args) throws InterruptedException {
        for(int i=1;i<30;i++){
            int finalI=i;
        new Thread(new Runnable() {
            @Override
            public void run() {
               String date= new ThreadLocalNormal01().date(finalI);
                System.out.println(date);
            }
        }).start();
        Thread.sleep(100);

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date= new ThreadLocalNormal01().date(1000);
                System.out.println(date);
            }
        }).start();
    }

    public String date(int seconds){
        Date date =new Date(1000*seconds);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }
}
