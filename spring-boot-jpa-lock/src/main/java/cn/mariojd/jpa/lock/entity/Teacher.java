package cn.mariojd.jpa.lock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 悲观锁示例
 *
 * @author Jared
 * @date 2019/1/14 15:41
 */
@Data
@Entity
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Teacher(String name) {
        this.name = name;
    }
}
