package xyz.gabear.learn.concurrency.one.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import xyz.gabear.learn.concurrency.one.annotations.NotThreadSafe;

import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        // a = 2; // 直接报错。基本类型变量值不可改变
        // b = "3"; // 直接报错。基本类型变量值不可改变
        // map = Maps.newHashMap(); // 直接报错。引用类型变量初始化之后不可指向另外一个对象
        map.put(1, 3); // 但是可以改变变量的值
        log.info("{}", map.get(1));
    }

    // 常见的用法
    private void test(final int a) {
        // a = 1; // 直接报错。基本类型变量值不可改变
    }
}
