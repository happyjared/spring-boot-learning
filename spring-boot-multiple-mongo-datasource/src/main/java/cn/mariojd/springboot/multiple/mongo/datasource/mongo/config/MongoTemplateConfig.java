package cn.mariojd.springboot.multiple.mongo.datasource.mongo.config;

import cn.mariojd.springboot.multiple.mongo.datasource.config.MongoPropertiesConfig;
import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

/**
 * @author Jared
 * @date 2019/1/12 11:04
 */
@Configuration
public class MongoTemplateConfig {

    @Resource
    private MongoPropertiesConfig mongoPropertiesConfig;

    @Primary
    @Bean(name = PrimaryMongoConfig.PRIMARY_MONGO_TEMPLATE)
    public MongoTemplate primaryMongoTemplate() {
        MongoProperties mongo = mongoPropertiesConfig.primaryMongo();
        MongoClient mongoClient = new MongoClient(mongo.getHost(), mongo.getPort());
        return new MongoTemplate(mongoClient, mongo.getDatabase());
    }

    @Bean(SecondaryMongoConfig.SECONDARY_MONGO_TEMPLATE)
    public MongoTemplate secondaryMongoTemplate() {
        MongoProperties mongo = mongoPropertiesConfig.secondaryMongo();
        MongoClient mongoClient = new MongoClient(mongo.getHost(), mongo.getPort());
        return new MongoTemplate(mongoClient, mongo.getDatabase());
    }

}
