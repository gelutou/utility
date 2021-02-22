package oliver.multithread.runnable;

import lombok.SneakyThrows;

/**
 * @author Oliver
 * @date 2021/2/22 14:01
 * @description 并发问题。多线程买票
 */
public class BuyTickets implements Runnable{

    /**
     * @param 票的剩余数量
     */
    private int ticketIndex = 100;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            if (ticketIndex <= 0) {
                System.out.println("票售罄");
                break;
            }else {
                System.out.println(Thread.currentThread().getName() + "买到了第" + ticketIndex-- +"张票");
            }
            Thread.sleep(200);
        }
    }

    public static void main(String[] args) {
        BuyTickets tickets = new BuyTickets();
        //开启三个线程同时抢票,会出现同时抢到某一张票情况
        /*  黄牛买到了第15张票
            大明买到了第15张票
            */
        //多个线程操作同一个资源，线程不安全，出现并发问题
        new Thread(tickets,"小明").start();
        new Thread(tickets,"大明").start();
        new Thread(tickets,"黄牛").start();
    }
}
