package cn.mariojd.cache.repository;

import cn.mariojd.cache.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jared
 * @date 2019/4/8 14:00
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
