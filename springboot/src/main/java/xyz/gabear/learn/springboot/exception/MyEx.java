package xyz.gabear.learn.springboot.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyEx {
    public static void main(String[] args) {
        try {
            throw new BusinessException(1, "hahaha");
        } catch (BusinessException e) {
            System.out.println("11111111");
            log.error("error", e);
            System.out.println("222222222");
        }
    }
}
