package oliver.multithread.runnable;

/**
 * 描述 礼让：让线程重新变为就绪状态，等待CPU调度；礼让不一定成功
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/23 16:29:27
 */
public class YieldRunnable implements Runnable{


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +" start ...");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() +" end ...");
    }

    public static void main(String[] args) {

        YieldRunnable yieldRunnable = new YieldRunnable();
        new Thread(yieldRunnable,"a").start();
        new Thread(yieldRunnable,"b").start();
    }
}
