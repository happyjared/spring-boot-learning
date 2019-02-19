package cn.mariojd.jpa.naming.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Jared
 * @date 2019/2/18 16:39
 */
@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name")
    private String name;

}
