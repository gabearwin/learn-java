package xyz.gabear.learn.springbootgirl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

// 开启异步任务
@EnableAsync
// 开启定时任务
@EnableScheduling
// 扫描mybatis mapper包路径
@MapperScan(basePackages = "xyz.gabear.learn.springbootgirl.domain.mapper")
@SpringBootApplication
public class SpringbootGirlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGirlApplication.class, args);
    }
}
