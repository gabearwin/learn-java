package xyz.gabear.learn.ssm.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtils {
    private final static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private JedisPool jedisPool;

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public <T> String setObj(String key, Object obj) {
        RuntimeSchema schema = RuntimeSchema.createFrom(obj.getClass());
        // set Object() -> 序列化 -> byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                byte[] bytes = ProtostuffIOUtil.toByteArray(obj, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                return jedis.set(key.getBytes(), bytes);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public <T> T getObj(String key, Class<T> clazz) {
        RuntimeSchema<T> schema = RuntimeSchema.createFrom(clazz);
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                // get-> byte[] -> 反序列化 -> Object()
                // 使用插件，自定义序列化。实体必须是POJO
                byte[] bytes = jedis.get(key.getBytes());
                // 从缓存中得到了
                if (bytes != null) {
                    // 空对象
                    T obj = schema.newMessage();
                    // 反序列化
                    ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
                    return obj;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
