package chapter12;

import java.util.concurrent.TimeUnit;

/**
 * Created by zjb on 2019/7/25.
 * volatile变量自增测试
 */
public class VolatileTest {
    public static volatile int race = 0;

    public static void increace() {
        race++;
    }

    private static final int THREADS_COUNT = 3000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increace();
                }
            }, "increase" + i);
            threads[i].start();
        }
        while (Thread.activeCount() > 2) {//除main线程还有一个Monitor Ctrl-Break线程活动
            Thread.yield();
        }

        System.out.println(race);
    }
}
