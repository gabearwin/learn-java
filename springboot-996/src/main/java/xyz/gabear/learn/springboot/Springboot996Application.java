package xyz.gabear.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Springboot996Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot996Application.class, args);
    }

}
