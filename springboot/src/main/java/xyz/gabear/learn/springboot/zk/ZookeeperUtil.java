package xyz.gabear.learn.springboot.zk;

import org.apache.curator.framework.CuratorFramework;

public class ZookeeperUtil {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = ZookeeperConfig.curatorFramework();
        byte[] s = client.getData().forPath("/");
        System.out.println(new String(s)+", hello");
    }
}
