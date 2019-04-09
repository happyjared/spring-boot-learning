package cn.mariojd.jetcache.service;

import cn.mariojd.jetcache.entity.Coffee;
import cn.mariojd.jetcache.repository.CoffeeRepository;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Jared
 * @date 2019/4/9 14:25
 */
@Slf4j
@Service
public class CoffeeCreateCacheService {

    public static final String CACHE_NAME = "CoffeeCreateCache:";

    @Resource
    private CoffeeRepository coffeeRepository;

    /**
     * 使用 @CreateCache 注解创建Cache实例
     * 以下未定义默认值的参数，将使用yml中指定的全局配置
     */
    @CreateCache(name = CACHE_NAME, expire = 1, localLimit = 10, serialPolicy = SerialPolicy.KRYO,
            timeUnit = TimeUnit.MINUTES, cacheType = CacheType.BOTH)
    private Cache<Integer, Coffee> coffeeCache;

    @Transactional
    public void add(Coffee coffee) {
        coffee = coffeeRepository.save(coffee);
        coffeeCache.put(coffee.getId(), coffee, 30, TimeUnit.SECONDS);
    }

    public Optional<Coffee> get(int id) {
        Coffee coffee = coffeeCache.get(id);
        log.info("CoffeeCreateCache get {} res {}", id, coffee);
        if (Objects.isNull(coffee)) {
            Optional<Coffee> coffeeOptional = coffeeRepository.findById(id);
            if (coffeeOptional.isPresent()) {
                coffee = coffeeOptional.get();
                boolean res = coffeeCache.putIfAbsent(id, coffee);
                log.info("CoffeeCreateCache putIfAbsent {} res {}", id, res);
            }
        }
        return Optional.ofNullable(coffee);
    }

    @Transactional
    public Coffee update(Coffee coffee) {
        Coffee c = coffeeRepository.save(coffee);
        coffeeCache.put(c.getId(), c, 60, TimeUnit.SECONDS);
        return c;
    }

    @Transactional
    public void delete(int id) {
        coffeeRepository.deleteById(id);
        boolean res = coffeeCache.remove(id);
        log.info("CoffeeCreateCache delete {} res {}", id, res);
    }

}
