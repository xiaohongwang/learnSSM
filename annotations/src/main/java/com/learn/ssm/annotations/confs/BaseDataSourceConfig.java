package com.learn.ssm.annotations.confs;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

public abstract class BaseDataSourceConfig {

    protected DataSource dataSource(String driverClass,String url,String user,String password,Integer poolSize) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(user);
        dataSource.setMaximumPoolSize(poolSize);
        dataSource.setPassword(password);
        return dataSource;
    }
    protected DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    protected SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(getMapperLocation()));
        return sessionFactory.getObject();
    }
    protected SqlSessionTemplate sessionTemplate(SqlSessionFactory sessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sessionFactory);
        return sqlSessionTemplate;
    }
    abstract protected String getMapperLocation();
}

