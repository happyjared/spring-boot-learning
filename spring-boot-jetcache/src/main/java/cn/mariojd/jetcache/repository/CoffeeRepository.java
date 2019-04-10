package cn.mariojd.jetcache.repository;

import cn.mariojd.jetcache.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jared
 * @date 2019/4/9 13:24
 */
public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
}
