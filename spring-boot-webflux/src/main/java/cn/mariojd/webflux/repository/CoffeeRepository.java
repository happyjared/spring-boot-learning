package cn.mariojd.webflux.repository;

import cn.mariojd.webflux.entity.Coffee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Jared
 * @date 2019/3/29 9:25
 */
public interface CoffeeRepository extends ReactiveMongoRepository<Coffee, String> {
}
