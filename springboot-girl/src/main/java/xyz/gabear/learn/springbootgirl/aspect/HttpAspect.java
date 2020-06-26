package xyz.gabear.learn.springbootgirl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class HttpAspect {
    // @Pointcut("within(xyz.gabear.boot.service.GirlService)") // 匹配GirlService类里面的所有方法
    // @Pointcut("within(xyz.gabear.boot.service.*)") // 匹配Service包下所有类里面的所有方法
    // @Pointcut("within(xyz.gabear.boot.service..*)") // 匹配service包及其所有子包下的所有类的所有方法
    //
    // 对象匹配
    // public class DemoDao implements BaseDao{}
    // @Pointcut("this(xyz.gabear.DemoDao)") // 匹配AOP对象的目标对象为指定类型的方法，即DemoDao的AOP代理对象的方法---代理对象
    // @Pointcut("target(xyz.gabear.BaseDao)") // 匹配实现BaseDao接口的目标对象(而不是AOP代理后的对象)的方法，这里即DemoDao的方法---原始对象
    // @Pointcut("bean(*Service)") // 匹配所有以Service结尾的Bean里头的方法
    //
    // 参数匹配
    // @Pointcut("execution(**..find*(Long))") // 匹配任何以find开头而且只有一个Long参数的方法
    // @Pointcut("execution(**..find*(Long,..))") // 匹配任何以find开头而且第一个参数为Long型的方法
    // @Pointcut("args(Long)") // 匹配任何只有一个Long参数的方法
    // @Pointcut("args(Long,..)") // 匹配第一个参数为Long型的方法

    // execution(public * xyz.gabear.boot.controller.GirlController.girlList(..)) // 该切面只包含GirlController下girlList方法
    @Pointcut("execution(public * xyz.gabear.learn.springbootgirl.controller.GirlController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("URL is {}", request.getRequestURL());
        log.info("Method is {}", request.getMethod());
        log.info("IP is {}", request.getRemoteAddr());
        log.info("ClassAndMethod is {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("args is {}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        log.info("doAfter........");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        log.info("response is {}", object);
    }
}
