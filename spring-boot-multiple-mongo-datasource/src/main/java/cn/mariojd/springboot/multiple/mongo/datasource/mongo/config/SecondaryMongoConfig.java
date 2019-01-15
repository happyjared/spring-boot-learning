package cn.mariojd.springboot.multiple.mongo.datasource.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Jared
 * @date 2019/1/12 11:02
 */
@Configuration
@EnableMongoRepositories(basePackages = "cn.mariojd.springboot.multiple.mongo.datasource.mongo.repository.secondary",
        mongoTemplateRef = SecondaryMongoConfig.SECONDARY_MONGO_TEMPLATE)
public class SecondaryMongoConfig {

    public static final String SECONDARY_MONGO_TEMPLATE = "secondaryMongoTemplate";

}

