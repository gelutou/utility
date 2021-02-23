package oliver.multithread.runnable;

/**
 * 描述 想象成插队，当join后，之前执行的线程处于阻塞状态
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/23 16:38:37
 */
public class JoinRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("i am vip" +i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinRunnable joinRunnable = new JoinRunnable();
        Thread thread = new Thread(joinRunnable);
        thread.start();
        //主线程执行到一半时进行插队
        for (int i = 0; i < 500; i++) {
            if (i == 200) {
                thread.join();
            }
            System.out.println("i am common" +i);
        }
    }
}
