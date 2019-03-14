package cn.mariojd.config.apollo.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

/**
 * @author Jared
 * @date 2019/3/12 14:47
 */
@Slf4j
//@Configuration TODO
public class DynamicDataSourceConfig {

    @ApolloConfig
    private Config config;

    @Resource
    private DataSource dataSource;

    private static final String DATASOURCE_KEY = "db";

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
                    terminateHikariDataSource(dataSource);

                    DynamicDataSource source = new DynamicDataSource();
                    source.setTargetDataSources(Collections.singletonMap(DATASOURCE_KEY, dataSource()));
                    source.afterPropertiesSet();
                    try {
                        Connection connection = source.getConnection();
                        log.info("Connection : {}", connection.isClosed());
                        log.info("Connection: {}", connection.isReadOnly());
                        connection.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    log.info("动态切换数据源：{}", config.getProperty("spring.datasource.url", ""));
                });
    }

    private void terminateHikariDataSource(DataSource dataSource) {
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        HikariPoolMXBean poolMXBean = hikariDataSource.getHikariPoolMXBean();
        poolMXBean.softEvictConnections();
        hikariDataSource.close();
    }

    class DynamicDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return DATASOURCE_KEY;
        }
    }

}
