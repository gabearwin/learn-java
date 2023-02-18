package xyz.gabear.learn.springboot.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 类名称：CommonMetaObjectHandler
 * ********************************
 * <p>
 * 类描述：公共元数据处理器
 *
 * @author
 * @date 下午2:57
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("新建时，开始填充系统字段！");

        this.strictInsertFill(metaObject, "created", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "modified", LocalDateTime.class, LocalDateTime.now());

        this.strictInsertFill(metaObject, "creator", String.class, "TODO 从上下文获取当前人");
        this.strictInsertFill(metaObject, "operator", String.class, "TODO 从上下文获取当前人");

        this.strictInsertFill(metaObject, "status", Integer.class, 0);
        this.strictInsertFill(metaObject, "version", Long.class, 1L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新时，开始填充系统字段！");

        this.strictUpdateFill(metaObject, "modified", LocalDateTime.class, LocalDateTime.now());

        this.strictUpdateFill(metaObject, "operator", String.class, "TODO 从上下文获取修改人");
    }
}
