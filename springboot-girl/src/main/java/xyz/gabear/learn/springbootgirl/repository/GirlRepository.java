package xyz.gabear.learn.springbootgirl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.gabear.learn.springbootgirl.domain.Girl;

import java.util.List;

public interface GirlRepository extends JpaRepository<Girl, Integer> {

    // 通过年龄来查询
    List<Girl> findByAge(Integer age);
}
