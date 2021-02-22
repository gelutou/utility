package oliver.multithread.thread;

/**
 * @author Oliver
 * @date 2021/2/22 11:48
 * @description
 */
//线程开启不一定立即执行，由CPU调度
public class MainAndThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("i am run() "+i);
        }
    }

    public static void main(String[] args) {

        //线程之间交替执行
        MainAndThread mainAndThread = new MainAndThread();
        mainAndThread.start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("i am main() " +i);
        }
    }
}
