package cn.mariojd.springboot.multiple.mongo.datasource.mongo;

import cn.mariojd.springboot.multiple.mongo.datasource.mongo.config.PrimaryMongoConfig;
import cn.mariojd.springboot.multiple.mongo.datasource.mongo.config.SecondaryMongoConfig;
import cn.mariojd.springboot.multiple.mongo.datasource.mongo.entity.Cartoon;
import cn.mariojd.springboot.multiple.mongo.datasource.mongo.entity.Movie;
import cn.mariojd.springboot.multiple.mongo.datasource.mongo.repository.primary.MovieRepository;
import cn.mariojd.springboot.multiple.mongo.datasource.mongo.repository.secondary.CartoonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2019/1/12 13:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("mongo")
public class SpringBootMongoMultipleDataSourceTest {

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private CartoonRepository cartoonRepository;

    @Resource
    @Qualifier(value = PrimaryMongoConfig.PRIMARY_MONGO_TEMPLATE)
    private MongoTemplate primaryMongoTemplate;

    @Resource
    @Qualifier(value = SecondaryMongoConfig.SECONDARY_MONGO_TEMPLATE)
    private MongoTemplate secondaryMongoTemplate;

    @Test
    public void test() {
        movieRepository.save(Movie.builder().name("毒液").build());
        movieRepository.save(Movie.builder().name("战狼").build());
        movieRepository.save(Movie.builder().name("我不是药神").build());

        Assert.assertEquals(3, movieRepository.findAll().size());

        cartoonRepository.save(Cartoon.builder().name("蜡笔小新").build());
        cartoonRepository.save(Cartoon.builder().name("小猪佩奇").build());
        cartoonRepository.save(Cartoon.builder().name("小猪佩奇").build());
        Assert.assertEquals(2, cartoonRepository.findAll().size());
    }

}
