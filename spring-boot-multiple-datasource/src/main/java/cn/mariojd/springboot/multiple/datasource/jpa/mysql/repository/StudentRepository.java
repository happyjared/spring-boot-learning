package cn.mariojd.springboot.multiple.datasource.jpa.mysql.repository;

import cn.mariojd.springboot.multiple.datasource.jpa.mysql.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jared
 * @date 2018/11/21 19:46
 * @blog: https://blog.mariojd.cn/
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
