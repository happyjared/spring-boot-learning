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
        // 新增数据
        User user = new User("Jared");
        User dbUser = userService.add(user);

        // @throws org.springframework.orm.ObjectOptimisticLockingFailureException

        // 乐观锁①：更新User
        new Thread(() -> userService.optimisticLock(dbUser, 500L)).start();
        userService.optimisticLock(dbUser, 1000L);

        // 乐观锁②：更新User
        new Thread(() -> userService.optimisticLock(dbUser, 1000L)).start();
        userService.optimisticLock2(dbUser, 500L);
    }

    @Test
    public void testTeacher() {
        // 新增数据
        Teacher teacher = new Teacher("Nancy");
        Teacher dbTeacher = teacherService.add(teacher);

        // 悲观锁①：更新Teacher
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            teacherService.pessimisticLock(dbTeacher, 200L);
        }).start();
        // Success
        teacherService.pessimisticLock(dbTeacher, 2000L);

        // 悲观锁②：更新Teacher
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            teacherService.pessimisticLock2(dbTeacher, 300L);
        }).start();
        // Success
        teacherService.pessimisticLock2(dbTeacher, 3000L);
    }

}

