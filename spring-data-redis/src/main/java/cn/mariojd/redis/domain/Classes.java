package cn.mariojd.redis.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

/**
 * @author Jared
 * @date 2019/3/22 9:12
 */
@Data
@RedisHash("class")
public class Classes {

    @Id
    private Integer id;

    private String name;

    private List<Student> students;

}
