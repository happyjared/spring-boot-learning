package cn.mariojd.springboot.multiple.mongo.datasource.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Jared
 * @date 2019/1/12 11:00
 */
@Configuration
@EnableMongoRepositories(basePackages = "cn.mariojd.springboot.multiple.mongo.datasource.mongo.repository.primary",
        mongoTemplateRef = PrimaryMongoConfig.PRIMARY_MONGO_TEMPLATE)
public class PrimaryMongoConfig {

    public static final String PRIMARY_MONGO_TEMPLATE = "primaryMongoTemplate";

}
