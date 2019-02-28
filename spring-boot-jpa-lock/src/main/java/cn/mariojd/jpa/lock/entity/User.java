package cn.mariojd.jpa.lock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 乐观锁示例
 *
 * @author Jared
 * @date 2019/1/14 13:39
 */
@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /**
     * 乐观锁实现方式①：直接添加@Version
     */
    @Version
    private Integer version = 0;

    public User(String name) {
        this.name = name;
    }
}
