package cn.mariojd.jpa.lock;

import cn.mariojd.jpa.lock.entity.Teacher;
import cn.mariojd.jpa.lock.entity.User;
import cn.mariojd.jpa.lock.repository.UserRepository;
import cn.mariojd.jpa.lock.service.TeacherService;
import cn.mariojd.jpa.lock.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private TeacherService teacherService;

    @Test
    public void testUser() {
        // 插入1条数据
        User user = new User("Jared");
        userService.add(user);

        // 乐观锁：更新User
        new Thread(() -> userService.optimisticLock(user, 100L)).start();
        // @throws org.springframework.orm.ObjectOptimisticLockingFailureException
        userService.optimisticLock(user, 3000L);
    }

    @Test
    public void testTeacher() {
        // 插入1条数据
        Teacher teacher = new Teacher("Nancy");
        teacherService.add(teacher);

        // 悲观锁：更新Teacher
        new Thread(() -> teacherService.pessimisticLock(teacher, 1000L)).start();
        teacherService.pessimisticLock(teacher, 10000L);
    }

}

