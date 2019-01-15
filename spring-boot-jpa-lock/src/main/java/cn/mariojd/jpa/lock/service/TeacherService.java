package cn.mariojd.jpa.lock.service;

import cn.mariojd.jpa.lock.entity.Teacher;
import cn.mariojd.jpa.lock.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2019/1/14 15:42
 */
@Slf4j
@Service
public class TeacherService {

    @Resource
    private TeacherRepository teacherRepository;

    /**
     * 新增教师
     *
     * @param teacher
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(Teacher teacher) {
        teacherRepository.save(teacher);
    }


    /**
     * 悲观锁：更新教师
     * 使用for update一定要加上这个事务
     * 当事务处理完后，for update才会将行级锁解除
     *
     * @param teacher
     * @param sleepMillis
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void pessimisticLock(Teacher teacher, long sleepMillis) {
        teacherRepository.findByTeacherId(teacher.getId()).ifPresent(t -> {
            log.info(t.toString());
            t.setName("Pessimistic Lock: " + t.getName() + " Sleep millis: " + sleepMillis);
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            teacherRepository.save(t);
        });
    }

}
