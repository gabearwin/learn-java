package xyz.gabear.learn.springbootgirl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xyz.gabear.learn.springbootgirl.util.JsonDTO;
import xyz.gabear.learn.springbootgirl.domain.Girl;
import xyz.gabear.learn.springbootgirl.repository.GirlRepository;
import xyz.gabear.learn.springbootgirl.service.GirlService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class GirlController {
    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    // 查询所有记录
    @GetMapping("/girls")
    public List<Girl> girlList() {
        return girlRepository.findAll();
    }

    // 添加一条记录，返回添加之后该记录
    @PostMapping("/girls")
    public JsonDTO addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String err = bindingResult.getFieldError().getDefaultMessage();
            log.error(err);
            return JsonDTO.fail(1, err);
        }
        log.warn(girl.getName() + girl.getAge());
        return JsonDTO.success(girlRepository.save(girl));
    }

    // 通过id查询一条记录
    @GetMapping("/girls/{id}")
    public Optional<Girl> getOneGirl(@PathVariable("id") Integer id) {
        return girlRepository.findById(id);
    }

    // 传入一条记录，根据id更新该记录
    @PutMapping("/girls")
    public Girl girlUpdate(Girl girl) {
        return girlRepository.save(girl);// 默认根据id主键查找然后更新
    }

    // 通过id删除一条记录
    @DeleteMapping("/girls/{id}")
    public void deleteGirl(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

    // 通过age查询记录列表
    @GetMapping("/girls/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age) {
        return girlRepository.findByAge(age);
    }

    @GetMapping("/girls/two")
    public String addTwo() {
        return girlService.insertTwo();
    }

    @GetMapping("/girls/getage/{id}")
    public void getAge(@PathVariable("id") Integer id) {
        girlService.getAge(id);
    }

}
