package cn.mariojd.webflux.repository;

import cn.mariojd.webflux.entity.People;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author Jared
 * @date 2019/3/29 9:25
 */
public interface PeopleRepository extends ReactiveCrudRepository<People, Integer> {
}
