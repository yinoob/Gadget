package cache.computable;

import java.util.Random;

public class ExpensiveFunction implements Computable<String,Integer>{

    @Override
    public Integer compute(String arg) throws Exception{
        Thread.sleep(5000);
        return Integer.valueOf(arg);
    }
}
