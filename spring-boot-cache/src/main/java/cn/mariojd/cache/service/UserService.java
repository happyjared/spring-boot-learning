package cn.mariojd.cache.service;

import cn.mariojd.cache.entity.User;
import cn.mariojd.cache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Jared
 * @date 2019/4/8 14:02
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * Key name: key prefix.classSimpleName.methodName?pn=xxx&ps=xxx&sort=xxx
     */
    @Cacheable(key = "#root.targetClass.simpleName+'.'+#root.methodName+'?pn='+#pageable.pageNumber+'&ps='+#pageable.pageSize+'&sort='+#pageable.sort.toString()")
    public Page<User> page(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Cacheable(key = "'user.'+#userId", unless = "#result == null")
    public Optional<User> get(int userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public User add(String name) {
        User user = User.builder().name(name).build();
        return userRepository.save(user);
    }

    @CachePut(key = "'user.'+#userId", unless = "#result == null")
    @Transactional
    public Optional<User> update(int userId, String name) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {
            user.setName(name);
            userRepository.save(user);
        });
        return userOptional;
    }

    @CacheEvict(key = "'user.'+#userId")
    @Transactional
    public void delete(int userId) {
        userRepository.findById(userId).ifPresent(user -> userRepository.delete(user));
    }

}
