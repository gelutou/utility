package oliver.multithread.sync.safe;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 描述 安全的插入ArrayList
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/24 13:44:06
 */
public class SafeList {

    public static void main(String[] args) throws InterruptedException {

        ArrayList arrayList = new ArrayList<String>(10000);
        for (int i = 0; i < 100000; i++) {
            new Thread(() ->{
                synchronized (arrayList){
                    arrayList.add(Thread.currentThread().getName());
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("arrayList.size() = " + arrayList.size());

        //补充安全的List
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                copyOnWriteArrayList.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("copyOnWriteArrayList.size() = " + copyOnWriteArrayList.size());
    }
}
