package cn.mariojd.config.apollo.repository;

import cn.mariojd.config.apollo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jared
 * @date 2019/3/13 9:15
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
