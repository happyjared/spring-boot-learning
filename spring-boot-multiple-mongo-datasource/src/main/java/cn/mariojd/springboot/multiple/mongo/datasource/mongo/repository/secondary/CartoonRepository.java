package cn.mariojd.springboot.multiple.mongo.datasource.mongo.repository.secondary;

import cn.mariojd.springboot.multiple.mongo.datasource.mongo.entity.Cartoon;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Jared
 * @date 2019/1/12 13:57
 */
public interface CartoonRepository extends MongoRepository<Cartoon, String> {
}
