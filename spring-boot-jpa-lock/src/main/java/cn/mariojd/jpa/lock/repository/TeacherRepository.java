package cn.mariojd.jpa.lock.repository;

import cn.mariojd.jpa.lock.entity.Teacher;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Jared
 * @date 2019/1/14 15:42
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query(value = "select id,name from teacher where id =?1 for update", nativeQuery = true)
    Optional<Teacher> findByIdWithNativeQuery(int id);

    @Query(value = "select id,name from teacher where id =?1 LOCK IN SHARE MODE", nativeQuery = true)
    Optional<Teacher> findByIdWithNativeQuery2(int id);

}
