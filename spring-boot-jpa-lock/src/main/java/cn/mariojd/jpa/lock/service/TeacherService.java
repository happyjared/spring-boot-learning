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
    @Transactional
    public Teacher add(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    /**
     * 悲观锁①：更新教师
     *
     * @param teacher
     * @param sleepMillis
     */
    @Transactional
    public void pessimisticLock(Teacher teacher, long sleepMillis) {
        teacherRepository.findById(teacher.getId()).ifPresent(t -> {
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

    /**
     * 悲观锁②：更新教师
     *
     * @param teacher
     * @param sleepMillis
     */
    @Transactional
    public void pessimisticLock2(Teacher teacher, long sleepMillis) {
        teacherRepository.findTeacherForUpdate(teacher.getId()).ifPresent(t -> {
            log.info(t.toString());
            t.setName("Pessimistic Lock2: " + t.getName() + " Sleep millis: " + sleepMillis);
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            teacherRepository.save(t);
        });
    }

}
