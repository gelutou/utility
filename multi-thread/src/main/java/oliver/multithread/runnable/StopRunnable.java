package oliver.multithread.runnable;

/**
 * 线程的关闭
 * 1.使线程正常关闭，利用次数，不建议死循环
 * 2.建议使用标志位来停止
 * 3.禁止使用JDK自带stop()或destroy()
 * 4.或让线程自动停下来
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/23 15:14:36
 */
public class StopRunnable implements Runnable{

    private boolean flag = true;

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("running ... "+i);
            i++;
        }
    }
    /**
     * @description: 转换标志位，停止线程
     * @return void
     * @date: 2021-02-23 15:31
     * @author Oliver
    **/
    public void stopRun(){
        this.flag = false;
        System.out.println("running is stopped");
    }

    public static void main(String[] args) throws InterruptedException {
        StopRunnable stop = new StopRunnable();
        new Thread(stop).start();
        for (int i = 0; i < 2000; i++) {
            System.out.println("main running ... "+i);
            Thread.sleep(1);
            if (i == 1500) {
                stop.stopRun();
            }
        }
    }
}
