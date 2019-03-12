package cn.mariojd.config.apollo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Jared
 * @date 2019/3/12 15:24
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

}
