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

    private static final String CACHE_NAME = "CoffeeMethodCache:";

    @Resource
    private CoffeeRepository coffeeRepository;

    @Transactional
    public Coffee add(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    /**
     * 缓存在 Remote 的 Redis，也可以配置成 both 开启两级缓存
     */
    @Cached(name = CACHE_NAME, key = "#id", cacheType = CacheType.REMOTE, serialPolicy = SerialPolicy.KRYO,
            condition = "#id>0", postCondition = "result!=null")
    public Coffee get(int id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    @CacheUpdate(name = CACHE_NAME, key = "#coffee.id", value = "result", condition = "#coffee.id!=null")
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
