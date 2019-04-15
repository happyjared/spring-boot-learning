package cn.mariojd.webflux.repository;

import cn.mariojd.webflux.entity.Apple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Jared
 * @date 2019/3/29 8:49
 */
public interface AppleRepository extends ReactiveMongoRepository<Apple, String> {

    Page<Apple> findAll(Pageable pageable);

}
