package xyz.gabear.salary.service;

import xyz.gabear.spring.beans.Bean;

@Bean
public class SalaryService {
    public Integer calSalary(Integer experience) {
        return experience * 5000;
    }
}
