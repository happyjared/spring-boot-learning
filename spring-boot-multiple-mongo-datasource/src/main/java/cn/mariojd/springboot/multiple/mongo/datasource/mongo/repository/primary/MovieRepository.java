package cn.mariojd.springboot.multiple.mongo.datasource.mongo.repository.primary;

import cn.mariojd.springboot.multiple.mongo.datasource.mongo.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Jared
 * @date 2019/1/12 13:57
 */
public interface MovieRepository extends MongoRepository<Movie, String> {
}
