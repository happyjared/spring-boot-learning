package cn.mariojd.jetcache.service;

import cn.mariojd.jetcache.entity.Coffee;
import cn.mariojd.jetcache.repository.CoffeeRepository;
import com.alicp.jetcache.anno.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Jared
 * @date 2019/4/9 14:25
 */
@Slf4j
@Service
public class CoffeeMethodCacheService {

    public static final String CACHE_NAME = "CoffeeMethodCache:";

    @Resource
    private CoffeeRepository coffeeRepository;

    @Cached(name = CACHE_NAME, key = "#id", expire = 30, localExpire = 10,
            cacheType = CacheType.BOTH, cacheNullValue = true, localLimit = 10,
            condition = "#id>0", postCondition = "#result.isPresent()")
    @CacheRefresh(refresh = 10000, stopRefreshAfterLastAccess = 20000, refreshLockTimeout = 3000,
            timeUnit = TimeUnit.MILLISECONDS)
    @CachePenetrationProtect(timeout = 30)
    public Optional<Coffee> get(int id) {
        return coffeeRepository.findById(id);
    }

    @CacheUpdate(name = CACHE_NAME, key = "#coffee.id", value = "#result")
    @Transactional
    public Coffee update(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @CacheInvalidate(name = CACHE_NAME, key = "#id")
    @Transactional
    public void delete(int id) {
        coffeeRepository.deleteById(id);
    }

}
