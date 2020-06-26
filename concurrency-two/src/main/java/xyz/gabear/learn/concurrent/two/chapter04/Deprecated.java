package xyz.gabear.learn.concurrent.two.chapter04;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deprecated {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread暂停
        printThread.suspend();
        System.out.println("main suspend PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(20);
        // 将PrintThread恢复
        printThread.resume();
        System.out.println("main resume PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread终止
        printThread.stop();
        System.out.println("main stop PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }

    static class Runner implements Runnable {
        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println(Thread.currentThread().getName() + " Run at " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
/*
PrintThread Run at 21:27:08
PrintThread Run at 21:27:09
PrintThread Run at 21:27:10
main suspend PrintThread at 21:27:11
main resume PrintThread at 21:27:31
PrintThread Run at 21:27:31
PrintThread Run at 21:27:32
PrintThread Run at 21:27:33
PrintThread Run at 21:27:34
PrintThread Run at 21:27:34
main stop PrintThread at 21:27:34
*/
