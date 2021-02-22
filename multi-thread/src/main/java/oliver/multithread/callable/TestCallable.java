package oliver.multithread.callable;

import java.util.concurrent.*;

/**
 * @author Oliver
 * @date 2021/2/22 14:23
 * @description 可以定义返回值，可以抛出异常
 */
public class TestCallable implements Callable<Boolean> {

    @Override
    public Boolean call() {
        System.out.println("i am in");
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable testCallable_01 = new TestCallable();
        TestCallable testCallable_02 = new TestCallable();
        TestCallable testCallable_03 = new TestCallable();

        //创建个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<Boolean> submit = executorService.submit(testCallable_01);
        Future<Boolean> submit1 = executorService.submit(testCallable_02);
        Future<Boolean> submit2 = executorService.submit(testCallable_03);

        System.out.println("submit.get() = " + submit.get());
        System.out.println("submit1.get() = " + submit1.get());
        System.out.println("submit2.get() = " + submit2.get());

    }
}
