package xyz.gabear.learn.concurrent.two.chapter04;

import java.util.concurrent.TimeUnit;

public class Profiler {
    // 在调用get()之前，如果set方法没有调用，会进行默认初始化
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "默认的";
        }
    };

    public static final void begin() {
        THREAD_LOCAL.set("自定义的");
    }

    public static final String end() {
        return "结束了，初始值是：" + THREAD_LOCAL.get();
    }

    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Profiler.end());
    }
}
// 结束了，初始值是：自定义的

// ThreadLocal以自己为键，以任意对象为值。就是说线程变量这个东西自带一个变量值