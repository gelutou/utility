package oliver.multithread.cooperation;

import lombok.SneakyThrows;

/**
 * 描述 生产-消费者模型 管程法（利用缓冲区）
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/25 16:42:41
 */
public class Container {

    public static void main(String[] args) {
        Container container = new Container();
        new Thread(new Producter(container)).start();
        new Thread(new Customer(container)).start();
    }

    //需要一个容器大小
    Chicken[] chickens = new Chicken[10];
    //容器计数器
    int counter = 0;

    //生产者放入产品
    public synchronized void push (Chicken chicken) throws InterruptedException {
        //如果容器满了，等待消费者消费
        if (counter == chickens.length) {
            //通知消费者消费，生产者等待
            this.wait();
        }else {
            //如果没有就生产
            chickens[counter] = chicken;
            counter++;
            System.out.println("生产了第"+chicken.getId()+"只鸡");
            //告诉消费者消费
            this.notifyAll();
        }
    }

    //消费者消费产品
    public synchronized void pop(Chicken chicken) throws InterruptedException {
        //判断能否消费
        if (counter == 0) {
            //等待生产者生产
            this.wait();
        }else {
            counter--;
            System.out.println("消费了第"+chicken.getId()+"只鸡");
            this.notifyAll();
        }
    }
}

//生产者
class Producter implements Runnable{

    Container container;

    public Producter(Container container) {
        this.container = container;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
        }
    }
}

//消费者
class Customer implements Runnable{

    Container container;

    public Customer(Container container) {
        this.container = container;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.pop(new Chicken(i));
        }
    }
}

//产品
class Chicken {
    private int id;

    public Chicken(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
