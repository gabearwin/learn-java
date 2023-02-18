package xyz.gabear.learn.springboot;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class Springboot996ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testRateLimit() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new ThreadFactoryBuilder().setDaemon(false).setNameFormat("rest-%d").build(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                try {
                    latch.await();
                    String object = restTemplate.getForObject("http://localhost:8080/api/users/hello", String.class);
                    log.info(JSON.toJSONString(object, true));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        latch.countDown();
        Thread.sleep(1000 * 10000);
    }

}
