package xyz.gabear.learn.concurrent.two.chapter03;

// 重排序对多线程的影响
class ReorderExample {
    int     a    = 0;
    boolean flag = false;

    public void writer() {
        a = 1; //1
        flag = true; //2
    }

    public void reader() {
        if (flag) { //3
            int i = a * a; //4
            // ......
        }
    }
}
