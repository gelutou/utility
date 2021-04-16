package oliver.multithread.lock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述 可重入锁买票
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/25 16:19:56
 */
public class TicketLock{

    public static void main(String[] args) {
        BuyTicket buyTicket01 = new BuyTicket();
        new Thread(buyTicket01).start();
        new Thread(buyTicket01).start();
        new Thread(buyTicket01).start();
    }
}
class BuyTicket implements Runnable{

    private int ticketIndex = 10;
    private final ReentrantLock reentrantLock = new ReentrantLock();

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
            try {
                if (ticketIndex > 0) {
                    System.out.println(ticketIndex--);
                }else {
                    System.out.println("票售罄");
                    break;
                }
            }finally {
                reentrantLock.unlock();
            }
        }
    }

}
