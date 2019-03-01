package cn.mariojd.jpa.lock.repository;

import cn.mariojd.jpa.lock.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * @author Jared
 * @date 2019/1/14 15:42
 */
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    /**
     * 悲观锁实现①：使用@Lock注解，并且设置值为LockModeType.PESSIMISTIC_WRITE
     */
    @Override
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<Teacher> findById(Integer id);

    /**
     * 悲观锁实现②：手写SQL，结尾加上for update
     */
    @Query(value = "select t.* from teacher t where t.id = ?1 for update", nativeQuery = true)
    Optional<Teacher> findTeacherForUpdate(int id);

}
