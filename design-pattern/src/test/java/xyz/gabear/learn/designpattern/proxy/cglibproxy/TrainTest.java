package xyz.gabear.learn.designpattern.proxy.cglibproxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainTest {

    @Test
    public void move() {
        AccessProxy accessProxy = new AccessProxy();
        Train access = (Train) accessProxy.getProxy(Train.class);
        access.move();
        /*cglib访问控制代理开始
        火车在行驶中...
        cglib访问控制代理结束*/

        SecurityProxy securityProxy = new SecurityProxy();
        Train security = (Train) securityProxy.getProxy(Train.class);
        security.move();
        /*cglib安全控制代理开始
        火车在行驶中...
        cglib安全控制代理结束*/

        // 不能直接叠加功能
    }
}