package cn.mariojd.springboot.multiple.datasource.jpa;

import cn.mariojd.springboot.multiple.datasource.jpa.mysql.entity.Student;
import cn.mariojd.springboot.multiple.datasource.jpa.mysql.repository.StudentRepository;
import cn.mariojd.springboot.multiple.datasource.jpa.postgres.entity.Teacher;
import cn.mariojd.springboot.multiple.datasource.jpa.postgres.repository.TeacherRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * JPA多数据源测试
 *
 * @author Jared
 * @date 2018/11/21 23:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaMultipleDataSourceTest {

    @Resource
    private StudentRepository studentRepository;

    @Resource
    private TeacherRepository teacherRepository;

    @Test
    public void test() {
        studentRepository.save(new Student("张三"));
        studentRepository.save(new Student("李四"));
        studentRepository.save(new Student("王五"));

        Assert.assertEquals(3, studentRepository.findAll().size());

        teacherRepository.save(new Teacher("张老师"));
        teacherRepository.save(new Teacher("李老师"));
        teacherRepository.save(new Teacher("王老师"));
        Assert.assertEquals(3, teacherRepository.findAll().size());
    }

}