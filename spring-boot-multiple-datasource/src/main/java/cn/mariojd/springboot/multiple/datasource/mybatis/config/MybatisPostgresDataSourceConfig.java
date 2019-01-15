package cn.mariojd.springboot.multiple.datasource.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Jared
 * @date 2018/11/21 15:47
 */
@Configuration
@MapperScan(
        // 数据层所在包位置
        basePackages = "cn.mariojd.springboot.multiple.datasource.mybatis.postgres.mapper",
        sqlSessionTemplateRef = "mybatisPostgresSqlSessionTemplate")
public class MybatisPostgresDataSourceConfig {

    @Resource
    @Qualifier("postgresDataSource")
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory mybatisPostgresSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        // 如果是xml形式，需要在此处指定mapper位置
        // factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/postgres/mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager mybatisPostgresTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate mybatisPostgresSqlSessionTemplate(@Qualifier("mybatisPostgresSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
