package cn.mariojd.jpa.naming.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Jared
 * @date 2019/2/18 16:39
 */
@Data
@Entity
@Table(name = "teacher_class")
public class TeacherClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer teacherId;

    @Column(name = "ClassName")
    private String className;

}
