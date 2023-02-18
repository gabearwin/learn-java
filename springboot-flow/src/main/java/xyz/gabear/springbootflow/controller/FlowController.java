package xyz.gabear.springbootflow.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @date 2021/7/16
 */
@RestController
@Slf4j
public class FlowController {
    @GetMapping("/hello")
    public String hello() {
        long start = System.currentTimeMillis();
        String helloStr = getHelloStr();
        // 普通接口耗时2003
        log.info("普通接口耗时：" + (System.currentTimeMillis() - start));
        return helloStr;
    }

    @GetMapping("/mono")
    public Mono<String> hello2() {
        long start = System.currentTimeMillis();
        Mono<String> helloStr = Mono.fromSupplier(this::getHelloStr);
        // WebFlux 接口耗时：1
        log.info("WebFlux 接口耗时：" + (System.currentTimeMillis() - start));
        // 但是前端网页还是过大约2秒才看到数据
        return helloStr;
    }

    private String getHelloStr() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

    /**
     * 这里返回的 Content-Type 是 MediaType.TEXT_EVENT_STREAM_VALUE，即 text/event-stream。
     * 前端网页每2秒输出一行数据，总共8秒全部输出完
     *
     * @see <a href="https://mp.weixin.qq.com/s?__biz=MzI1NDY0MTkzNQ==&mid=2247493740&idx=1&sn=92a876859c7222faf1bd8b0514c791a8&scene=21#wechat_redirect">参考文章</a>
     */
    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        Flux<String> flux = Flux.fromArray(new String[]{"javaboy", "itboyhub", "www.javaboy.org", "itboyhub.com"})
                .map(s -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "my->data->" + s;
                });
        return flux;
    }
}
