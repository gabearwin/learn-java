package xyz.gabear.learn.concurrency.one.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import xyz.gabear.learn.concurrency.one.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map); // 将其变成不可修改的map
    }

    public static void main(String[] args) {
        map.put(1, 3); // 运行时抛出异常。不支持的操作java.lang.UnsupportedOperationException
        log.info("{}", map.get(1));
    }

}
