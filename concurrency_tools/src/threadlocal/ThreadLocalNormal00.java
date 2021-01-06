package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
//两个线程打印日期
public class ThreadLocalNormal00 {

    public String date(int seconds){
        Date date =new Date(1000*seconds);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(date);
    }

    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
               String date= new ThreadLocalNormal00().date(10);
                System.out.println(date);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String date= new ThreadLocalNormal00().date(1000);
                System.out.println(date);
            }
        }).start();
    }
}
