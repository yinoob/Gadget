package future;

import java.util.Random;
import java.util.concurrent.*;
//Future实践
public class OneFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service= Executors.newFixedThreadPool(10);
        Future<Integer> future=service.submit(new CallableTask());
        System.out.println(future.get());

    }

    static class CallableTask implements Callable<Integer>{
        @Override
        public Integer call() throws Exception{
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }
}
