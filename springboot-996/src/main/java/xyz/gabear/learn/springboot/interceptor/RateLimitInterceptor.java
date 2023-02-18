package xyz.gabear.learn.springboot.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.gabear.learn.springboot.exception.BusinessException;
import xyz.gabear.learn.springboot.exception.ErrorCodeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类名称：RateLimitInterceptor
 * ********************************
 * <p>
 * 类描述：全局限流拦截器
 *
 * @author
 * @date 下午9:28
 */
@Slf4j
@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    /**
     * 限流器实例(QPS限制为1)
     */
    private static final RateLimiter rateLimiter = RateLimiter.create(1);


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 尝试获取令牌
        if (!rateLimiter.tryAcquire()) {
            log.error("系统已被限流...");

            throw new BusinessException(ErrorCodeEnum.RATE_LIMIT_ERROR);
        }

        return true;
    }
}
