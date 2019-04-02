package cn.mariojd.data.rest.repository;

import cn.mariojd.data.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author Jared
 * @date 2019/4/2 9:04
 */
@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * /api/user/search/findByName
     *
     * @param name
     * @return
     */
    List<User> findByName(@Param("name") String name);

    /**
     * /api/user/1
     *
     * @param id
     */
    @Override
    @Modifying
    @Query("UPDATE User u SET u.deleted = true WHERE u.id = ?1")
    void deleteById(Integer id);

}
