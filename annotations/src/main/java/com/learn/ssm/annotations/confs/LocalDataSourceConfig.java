package com.learn.ssm.annotations.confs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//配置类
@Configuration
@MapperScan(basePackages = "com.learn.ssm.annotations.mapper",sqlSessionTemplateRef = "localSessionTemplate")
public class LocalDataSourceConfig extends  BaseDataSourceConfig{

    static final String MAPPER_LOCATION = "classpath:local/**/*.xml";

    @Value("${datasource.local.url}")
    private  String url;
    @Value("${datasource.local.user-name}")
    private String userName;
    @Value("${datasource.local.password}")
    private String password;
    @Value("${datasource.local.driver-class-name}")
    private String driverClassName;
    @Value("${datasource.local.poolSize}")
    private Integer poolSize;

    @Primary
    @Bean("localDataSource")
    public DataSource localDataSource() {
        return super.dataSource(driverClassName, url, userName, password, poolSize);
    }

    @Primary
    @Bean("localTransactionManager")
    public DataSourceTransactionManager localTransactionManager(@Qualifier("localDataSource") DataSource dataSource) {
        return super.transactionManager(dataSource);
    }

    @Primary
    @Bean("localSessionFactory")
    public SqlSessionFactory localSessionFactory(@Qualifier("localDataSource") DataSource dataSource) throws Exception {
        return super.sessionFactory(dataSource);
    }

    @Primary
    @Bean("localSessionTemplate")
    public SqlSessionTemplate localSessionTemplate(@Qualifier("localSessionFactory") SqlSessionFactory sessionFactory) {
        return super.sessionTemplate(sessionFactory);
    }

    @Override
    protected String getMapperLocation() {
        return LocalDataSourceConfig.MAPPER_LOCATION;
    }

}
