package oliver.multithread.deadline;

import lombok.SneakyThrows;

/**
 * 描述 模拟一个死锁
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/25 15:58:37
 */
public class GirlMakeUp extends Thread{

    private String name;
    private int choice;

    //只有一份资源
    private static LipStick lipStick;

    //只有一份资源
    private static Mirror mirror;

    public GirlMakeUp(String name,int choice){
        this.name = name;
        this.choice = choice;
    }

    @SneakyThrows
    @Override
    public void run() {
        doMake();
    }

    //正确的方法
    public void doMake() throws InterruptedException {
        lipStick = new LipStick();
        mirror = new Mirror();
        if (choice == 0) {
            synchronized (lipStick) {
                System.out.println(this.name + "获取到口红的锁");
                Thread.sleep(1000);
            }
            synchronized (mirror) {
                System.out.println(this.name + "获取到镜子的锁");
            }
        }else{
            synchronized (mirror) {
                System.out.println(this.name + "获取到镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipStick) {
                System.out.println(this.name + "获取到口红的锁");
            }
        }
    }

    //造成死锁的化妆方法
    public void doMakeDeadLine() throws InterruptedException {
        lipStick = new LipStick();
        mirror = new Mirror();
        if (choice == 0) {
            synchronized (lipStick) {
                System.out.println(this.name + "获取到口红的锁");
                Thread.sleep(1000);
                synchronized (mirror) {
                    System.out.println(this.name + "获取到镜子的锁");
                }
            }
        }else{
            synchronized (mirror) {
                System.out.println(this.name + "获取到镜子的锁");
                Thread.sleep(2000);
                synchronized (lipStick) {
                    System.out.println(this.name + "获取到口红的锁");
                }
            }
        }
    }

    public static void main(String[] args) {
        GirlMakeUp g01 = new GirlMakeUp("女孩-01",0);
        GirlMakeUp g02 = new GirlMakeUp("女孩-02",1);

        g01.start();
        g02.start();
    }
}

class LipStick{

}

class Mirror{

}