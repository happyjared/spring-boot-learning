package cn.mariojd.springboot.multiple.datasource.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jared
 * @date 2018/11/21 19:53
 * @blog: https://blog.mariojd.cn/
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager",
        // 数据层所在的包位置
        basePackages = "cn.mariojd.springboot.multiple.datasource.jpa.mysql.repository")
public class JpaMysqlDataSourceConfig {

    @Resource
    private Environment environment;

    @Resource
    @Qualifier("mysqlDataSource")
    private DataSource dataSource;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>(4);
        // Spring Boot 1.0+ ，使用MySQLDialect
        // Spring Boot 2.0+ ，指定MySQLDialect会默认使用MyISAM引擎，改成MySQL55Dialect即可
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        return builder.dataSource(dataSource)
                .properties(properties)
                // 实体所在的包位置
                .packages("cn.mariojd.springboot.multiple.datasource.jpa.mysql.entity")
                .persistenceUnit("jpa-mysql")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
