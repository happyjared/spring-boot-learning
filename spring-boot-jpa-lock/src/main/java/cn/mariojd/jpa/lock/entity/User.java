package cn.mariojd.jpa.lock.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
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

    @Version
    private Integer version;

    public User(String name) {
        this.name = name;
    }
}
