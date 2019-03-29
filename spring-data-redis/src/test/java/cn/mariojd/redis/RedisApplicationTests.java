package cn.mariojd.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testStudent() {
        final String key = "K1";
        stringRedisTemplate.opsForValue().set(key, "李四");
        String value = stringRedisTemplate.opsForValue().get(key);
        System.out.println("value = " + value);

        int id = new Random().nextInt(1000);
        final String key2 = "Student:" + id;
        redisTemplate.opsForHash().put(key2, "id", id);
        redisTemplate.opsForHash().put(key2, "name", "张三");
    }

}
