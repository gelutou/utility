package oliver.multithread.sync.unsafe;

import java.util.ArrayList;

/**
 * 描述 当两个线程同时向ArrayList内添加是，后一个就会把前一个覆盖，导致少插入
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/02/24 13:44:06
 */
public class UnsafeList {

    public static void main(String[] args) throws InterruptedException {

        ArrayList arrayList = new ArrayList<String>(10000);
        for (int i = 0; i < 100000; i++) {
            new Thread(() ->{
                arrayList.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("arrayList.size() = " + arrayList.size());
    }
}
