package com.ljh.redis;

import com.ljh.redis.config.DateUtils;
import com.ljh.redis.pojo.SysMenu;
import com.ljh.redis.serivce.SysMenuService;
import com.ljh.redis.util.RedisUtil;
import io.lettuce.core.dynamic.domain.Timeout;
import lombok.AllArgsConstructor;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    }

    @Test
    void test() {
        List<SysMenu> list = sysMenuService.list();
        redisTemplate.getConnectionFactory().getConnection();
        list.forEach(i -> {
            redisTemplate.opsForValue().set(UUID.randomUUID().toString(), i);
        });
    }
    @Test
    void test2() {
        new RedisAtomicLong(key("CHECK_OUT_RECORD_KEY_"), redisTemplate.getConnectionFactory(), 1);
        redisTemplate.expire(key("CHECK_OUT_RECORD_KEY_"), DateUtils.toEpochMilli(DateUtils.endOfDay()) - DateUtils.toEpochMilli(), TimeUnit.MICROSECONDS);
    }

    @Test
    void test1() {
        RedisAtomicLong redisAtomicLong1 = new RedisAtomicLong(key("CHECK_OUT_RECORD_KEY_"), redisTemplate.getConnectionFactory());
        String str = code("CHECK_OUT_RECORD_KEY_", redisAtomicLong1.getAndIncrement());
        System.out.println(str);
    }

    private String code(String type, Long number) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(new Date());
        String replace = String.format("%5d", number).replace(" ", "0");
        String code = type + "-" + format + replace;
        return code;
    }

    private String key(String key) {
        return key + DateUtils.today();
    }

}
