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
     * 作用的for update作用一样，将此行数据进行加锁，当整个方法将事务提交后，才会解锁
     *
     * @param id
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query(value = "select t from Teacher t where t.id =?1")
    Optional<Teacher> findByTeacherId(int id);

    @Override
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    <S extends Teacher> S save(S s);
}
