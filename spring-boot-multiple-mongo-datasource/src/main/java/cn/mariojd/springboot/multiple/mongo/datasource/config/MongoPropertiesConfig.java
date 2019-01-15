package cn.mariojd.springboot.multiple.mongo.datasource.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Jared
 * @date 2019/1/12 10:52
 */
@Data
@Configuration
public class MongoPropertiesConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.data.mongodb.primary")
    public MongoProperties primaryMongo() {
        return new MongoProperties();
    }

    @Bean
    @ConfigurationProperties("spring.data.mongodb.secondary")
    public MongoProperties secondaryMongo() {
        return new MongoProperties();
    }


}
