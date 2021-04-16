package oliver.multithread.sync.safe;

import lombok.SneakyThrows;

/**
 * 描述 安全的买票
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/24 10:37:50
 */
public class BuyTicket implements Runnable{

    private static int ticketIndex = 10;
    private boolean flag = true;

    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"张").start();
        new Thread(buyTicket,"王").start();
        new Thread(buyTicket,"李").start();
    }

    @SneakyThrows
    @Override
    public void run() {
        //买票
        while (flag) {
            buy();
        }
    }
    //目前synchronized锁的是this
    private synchronized void buy() throws InterruptedException {
        if (ticketIndex <= 0) {
            flag = false;
            return;
        }
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName()+"买到了票"+ticketIndex--);
    }
}
