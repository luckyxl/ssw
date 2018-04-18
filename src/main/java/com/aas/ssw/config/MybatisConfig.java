package com.aas.ssw.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author xl
 * @date 2017/8/14
 */
@Configuration
@EnableTransactionManagement
@ConditionalOnExpression("!'${spring.jta.enabled}'")
@MapperScan("com.aas.ssw.business.**.dao")
public class MybatisConfig {

    @Qualifier("dataSource")
    @Autowired(required = false)
    DataSource dataSource;

    @ConditionalOnBean(name = "dataSource")
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //设置xml扫描路径
            bean.setMapperLocations(resolver.getResources("classpath:mybatis/mappers/**/*Dao.xml"));
            bean.setConfigLocation(resolver.getResource("classpath:mybatis/sqlMapConfig.xml"));
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("sqlSessionFactory init fail", e);
        }
    }

    @ConditionalOnBean(name = "dataSource")
    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }


}
