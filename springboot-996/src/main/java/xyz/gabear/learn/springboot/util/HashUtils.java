package xyz.gabear.learn.springboot.util;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @date 2021/6/7
 */
public class HashUtils {
    public static void main(String[] args) {
        int i = Hashing.consistentHash("15858187391".hashCode(), 10);
    }

    // 这个使用是有问题的 https://stackoverflow.com/questions/12319560/how-should-i-use-guavas-hashingconsistenthash
    public static void testConsistentHash() {
        List<String> servers = Lists.newArrayList("server1", "server2", "server3", "server4", "server5");
        int bucket = Hashing.consistentHash(Hashing.md5().hashString("someId", StandardCharsets.UTF_8), servers.size());
        System.out.println("First time routed to :" + servers.get(bucket));

        // one of the back end servers is removed from the (middle of the) pool
        servers.remove(1);

        bucket = Hashing.consistentHash(Hashing.md5().hashString("someId", StandardCharsets.UTF_8), servers.size());
        System.out.println("Second time routed to :" + servers.get(bucket));
    }
}
