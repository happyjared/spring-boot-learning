package cn.mariojd.config.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author Jared
 * @date 2019/3/12 14:47
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DynamicDataSourceConfig {

    @ApolloConfig
    private Config config;

    @Resource
    private ApplicationContext context;

    @Resource
    @Qualifier("mysqlDataSource")
    private DataSource dataSource;

    private static final String DATASOURCE_KEY = "db";

    @Bean("dataSource")
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource source = new DynamicDataSource();
        source.setTargetDataSources(Collections.singletonMap(DATASOURCE_KEY, dataSource()));
        return source;
    }

    private DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(config.getProperty("spring.datasource.url", ""));
        dataSource.setUsername(config.getProperty("spring.datasource.username", ""));
        dataSource.setPassword(config.getProperty("spring.datasource.password", ""));
        return dataSource;
    }

    @ApolloConfigChangeListener
    public void configChangeListener(ConfigChangeEvent e) {
        e.changedKeys().stream().filter("spring.datasource.url"::contains).findFirst()
                .ifPresent(key -> {
                    boolean result = terminateHikariDataSource(dataSource);
                    log.info("Result {}", result);
                    DynamicDataSource source = context.getBean(DynamicDataSource.class);
                    source.setTargetDataSources(Collections.singletonMap(DATASOURCE_KEY, dataSource()));
                    source.afterPropertiesSet();
                    log.info("动态切换数据源：{}", config.getProperty("spring.datasource.url", ""));
                });
    }

    private boolean terminateHikariDataSource(DataSource dataSource) {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
            HikariPoolMXBean poolMXBean = hikariDataSource.getHikariPoolMXBean();
            //evict idle connections
            poolMXBean.softEvictConnections();
            hikariDataSource.close();
            return true;
        }
        return false;
    }

    class DynamicDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return DATASOURCE_KEY;
        }
    }

}
