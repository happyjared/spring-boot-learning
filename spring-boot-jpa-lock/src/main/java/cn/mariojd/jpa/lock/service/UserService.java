package cn.mariojd.jpa.lock.service;

import cn.mariojd.jpa.lock.entity.User;
import cn.mariojd.jpa.lock.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2019/1/14 15:09
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * 新增用户
     *
     * @param user
     */
    @Transactional
    public User add(User user) {
        return userRepository.save(user);
    }

    /**
     * 乐观锁①：更新用户
     *
     * @param user
     * @param sleepMillis
     */
    @Transactional
    public void optimisticLock(User user, long sleepMillis) {
        userRepository.findById(user.getId()).ifPresent(u -> {
            log.info(u.toString());
            u.setName("OptimisticLock Lock: " + u.getName() + " Sleep millis: " + sleepMillis);
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userRepository.save(u);
        });
    }

    /**
     * 乐观锁①：更新用户
     *
     * @param user
     */
    @Transactional
    public void optimisticLock2(User user, long sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userRepository.updateWithVersion(user.getId(), user.getName(), user.getVersion());
    }

}
