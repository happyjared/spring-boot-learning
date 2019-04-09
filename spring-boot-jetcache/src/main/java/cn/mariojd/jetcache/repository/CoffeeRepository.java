package cn.mariojd.jetcache.repository;

import cn.mariojd.jetcache.entity.Coffee;
import cn.mariojd.jetcache.service.CoffeeMethodCacheService;
import com.alicp.jetcache.anno.Cached;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jared
 * @date 2019/4/9 13:24
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {

    @Transactional
    @Cached(name = CoffeeMethodCacheService.CACHE_NAME, key = "#result.id")
    Coffee saveCoffee(Coffee coffee);

}
