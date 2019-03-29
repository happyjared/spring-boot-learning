package cn.mariojd.webflux.repository;

import cn.mariojd.webflux.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author Jared
 * @date 2019/3/29 8:49
 */
public interface UserRepository extends ReactiveCrudRepository<User, Integer> {
}
