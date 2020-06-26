package xyz.gabear.learn.springboot.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

// 使用curator操作zookeeper http://www.throwable.club/2018/12/16/zookeeper-curator-usage/

// @Configuration
public class ZookeeperConfig {

    // @Bean
    public static CuratorFramework curatorFramework() {
        CuratorFramework framework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        framework.start();
        return framework;
    }
}
