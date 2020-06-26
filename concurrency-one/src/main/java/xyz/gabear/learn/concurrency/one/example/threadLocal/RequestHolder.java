package xyz.gabear.learn.concurrency.one.example.threadLocal;

public class RequestHolder {
    private static final ThreadLocal<Long> request = new ThreadLocal<>();

    public static void add(Long id) {
        request.set(id);
    }

    public static Long getId() {
        return request.get();
    }

    public static void remove() {
        request.remove();
    }
}
