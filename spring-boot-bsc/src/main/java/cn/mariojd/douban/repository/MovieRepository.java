package cn.mariojd.douban.repository;

import cn.mariojd.douban.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jared
 * @date 2020/5/25 16:22
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
