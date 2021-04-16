package oliver.multithread.sync.safe;

import lombok.SneakyThrows;

import java.math.BigDecimal;

/**
 * 描述 安全的取钱，这里给run()增加 synchronized是不正确的，因为synchronized默认锁的是this，这里操作的是Account，应该使用同步代码块
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/24 11:13:12
 */
public class DrawMoney implements Runnable{

    //取的钱
    private BigDecimal drawMoney;
    private final Account account;
    //取出的人
    private String name;

    public DrawMoney(BigDecimal drawMoney,Account account,String name) {
        this.drawMoney = drawMoney;
        this.name = name;
        this.account =account;
    }

    @SneakyThrows
    @Override
    public void run() {
        synchronized (account) {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            if (account.balance.subtract(drawMoney).compareTo(new BigDecimal(0)) < 0) {
                System.out.println(Thread.currentThread().getName()+"余额不足");
                return;
            }
            Thread.sleep(2000);
            account.balance = account.balance.subtract(drawMoney);
            System.out.println(name+"取出了"+drawMoney);
            System.out.println("取出后余额："+account.balance);
        }
    }

    public static void main(String[] args) {
        Account account01 = new Account(new BigDecimal(100));
        DrawMoney drawMoney = new DrawMoney(new BigDecimal(50),account01,"张");
        DrawMoney drawMoney02 = new DrawMoney(new BigDecimal(100),account01,"王");
        new Thread(drawMoney).start();
        new Thread(drawMoney02).start();

        System.out.println("最后余额为:"+account01.balance);
    }
}

/**
 * @description: 银行账户类
 * @date: 2021-02-24 11:13
 * @author Oliver
**/
class Account{

    //余额
    BigDecimal balance;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }
}
