package cn.mariojd.cache.service;

import cn.mariojd.cache.entity.User;
import cn.mariojd.cache.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jared
 * @date 2019/4/9 9:20
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "user")
public class UserSupportService {

    @Resource
    private UserRepository userRepository;

    /**
     * 使用了自定义的KeyGenerator
     * 缓存生效需满足：存在不为空的入参i、且返回值非空
     */
    @Cacheable(keyGenerator = "customKeyGenerator", condition = "#i!=null", unless = "#result.isEmpty()")
    public List<User> list(Integer i) {
        return userRepository.findAll();
    }

}
