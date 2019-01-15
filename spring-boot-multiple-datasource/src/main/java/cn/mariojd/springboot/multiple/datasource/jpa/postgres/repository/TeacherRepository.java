package cn.mariojd.springboot.multiple.datasource.jpa.postgres.repository;

import cn.mariojd.springboot.multiple.datasource.jpa.postgres.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jared
 * @date 2018/11/21 19:51
 * @blog: https://blog.mariojd.cn/
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
