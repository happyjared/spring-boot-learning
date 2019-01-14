package cn.mariojd.jpa.lock.repository;

import cn.mariojd.jpa.lock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jared
 * @date 2019/1/14 13:40
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
