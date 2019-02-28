package cn.mariojd.jpa.lock.repository;

import cn.mariojd.jpa.lock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Jared
 * @date 2019/1/14 13:40
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 乐观锁实现方式②：在SQL中添加版本号校验
     *
     * @param id      User id
     * @param name    User姓名
     * @param version 版本号
     * @return 影响行数
     */
    @Modifying
    @Query(value = "update user u set u.name = ?2, u.version = u.version + 1 where u.id = ?1 and u.version = ?3", nativeQuery = true)
    int updateWithVersion(int id, String name, int version);

}
