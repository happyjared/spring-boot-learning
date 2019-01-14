package cn.mariojd.jpa.lock.service;

import cn.mariojd.jpa.lock.entity.Teacher;
import cn.mariojd.jpa.lock.repository.TeacherRepository;
import cn.mariojd.jpa.lock.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
     *
     * @param teacher
     * @param sleepMillis
     */
    @Transactional(rollbackFor = Exception.class)
    public void pessimisticLock(Teacher teacher, long sleepMillis) {
        teacherRepository.findByIdWithNativeQuery2(teacher.getId()).ifPresent(t -> {
            log.info(t.toString());
            t.setName("Pessimistic Lock: " + t.getName());
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            teacherRepository.save(t);
        });
    }

}
