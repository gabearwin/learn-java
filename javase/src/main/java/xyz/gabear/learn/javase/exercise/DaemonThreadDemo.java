package xyz.gabear.learn.javase.exercise;

public class DaemonThreadDemo {

    public static void main(String[] args) {
        new WorkerThread().start();

        try {
            Thread.sleep(7500);
        } catch (InterruptedException e) {
            // handle here exception
        }

        System.out.println("Main Thread ending");
    }

}

class WorkerThread extends Thread {

    public WorkerThread() {
        // When false, (i.e. when it's a user thread),
        // the Worker thread continues to run.
        //
        // When true, (i.e. when it's a daemon thread),
        // the Worker thread terminates when the main thread terminates.
        setDaemon(true);
    }

    @Override
    public void run() {
        int count = 0;

        while (true) {
            System.out.println("Hello from Worker " + count++);

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                // handle exception here
            }
        }
    }
}

/*
默认是false
当设置为false时运行的结果，主线程结束了但是子线程一直在运行。
Hello from Worker 0
Hello from Worker 1
Main Thread ending
Hello from Worker 2
Hello from Worker 3
Hello from Worker 4





当设置为true时运行的结果，主线程结束了子线程会立即结束。
Hello from Worker 0
Hello from Worker 1
Main Thread ending

Process finished with exit code 0




耗时可以用Stopwatch stopwatch = Stopwatch.createStarted();
*/
