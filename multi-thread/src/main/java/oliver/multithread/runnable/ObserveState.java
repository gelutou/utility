package oliver.multithread.runnable;

/**
 * 描述
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/23 16:56:14
 */
public class ObserveState {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 200; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i am thread "+i);
            }
        });
        Thread.State state = thread.getState();
        System.out.println("state_1 = " + state);

        thread.start();
        state =  thread.getState();
        System.out.println("state_2 = " + state);
        while (true) {
            state =  thread.getState();
            Thread.sleep(100);
            if (state != Thread.State.TERMINATED) {
                System.out.println("state_3 = " + state);
            }else {
                System.out.println("state_4 = " + state);
                break;
            }
        }
        //死亡线程不能启动，报错
        thread.start();
    }
}
