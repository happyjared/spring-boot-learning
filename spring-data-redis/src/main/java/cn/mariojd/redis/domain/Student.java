package cn.mariojd.redis.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author Jared
 * @date 2019/3/22 9:14
 */
@Data
@RedisHash("student")
public class Student {

    @Indexed
    private Integer id;

    private String name;

}
