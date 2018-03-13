package com.aas.ssw.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author xl
 * @date 2017/8/14
 */
@Configuration
@ConditionalOnProperty(name = "spring.jta.enabled")
@MapperScan(basePackages = "com.aas.ssw.business.dao.two", sqlSessionTemplateRef = "sqlSessionTemplateTwo")
public class MybatisConfigTwo {

    @Qualifier("dataSourceTwo")
    @Autowired(required = false)
    DataSource dataSource;

    @ConditionalOnBean(name = "dataSourceTwo")
    @Bean(name = "sqlSessionFactoryTwo")
    @Primary
    public SqlSessionFactory sqlSessionFactoryBean() {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //设置xml扫描路径
            bean.setMapperLocations(resolver.getResources("classpath:mybatis/mappers/two/**/*Dao.xml"));
            bean.setConfigLocation(resolver.getResource("classpath:mybatis/sqlMapConfig.xml"));
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException("sqlSessionFactory init fail", e);
        }
    }

    @Bean(name = "sqlSessionTemplateTwo")
    @Primary
    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /*@ConditionalOnBean(name = "dataSource")
    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }*/


}
