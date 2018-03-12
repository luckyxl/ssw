package com.aas.ssw.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 *
 * @author xl
 * @date 2017/8/14
 */
@Configuration
@ConditionalOnProperty(name = "spring.jta.enabled")
public class AtomikosDataSourceConfig {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AtomikosDataSourceConfig.class);

    @Value("${spring.datasource.initial-size}")
    private int initialSize;

    @Value("${spring.datasource.min-idle}")
    private int minIdle;

    @Value("${spring.datasource.max-active}")
    private int maxActive;

    @Value("${spring.datasource.max-wait}")
    private int maxWait;

    @Value("${spring.datasource.time-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validation-query}")
    private String validationQuery;

    @Value("${spring.datasource.test-while-idle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.test-on-return}")
    private boolean testOnReturn;

    @Value("${spring.datasource.pool-prepared-statements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.max-open-prepared-statements}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.one.url}")
    private String urlOne;

    @Value("${spring.datasource.one.username}")
    private String usernameOne;

    @Value("${spring.datasource.one.password}")
    private String passwordOne;

    @Value("${spring.datasource.one.driverClassName}")
    private String driverClassNameOne;

    @Value("${spring.datasource.one.connectionProperties}")
    private String connectionPropertiesOne;

    @Value("${spring.datasource.two.url}")
    private String urlTwo;

    @Value("${spring.datasource.two.username}")
    private String usernameTwo;

    @Value("${spring.datasource.two.password}")
    private String passwordTwo;

    @Value("${spring.datasource.two.driverClassName}")
    private String driverClassNameTwo;

    @Value("${spring.datasource.two.connectionProperties}")
    private String connectionPropertiesTwo;


    @Bean(name = "dataSourceOne")     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource oneDataSource() {
        DruidXADataSource datasource = new DruidXADataSource();
        datasource.setUrl(this.urlOne);
        datasource.setUsername(usernameOne);
        datasource.setPassword(passwordOne);
        datasource.setDriverClassName(driverClassNameOne);
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (Exception e) {
            LOGGER.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionPropertiesOne);
        AtomikosDataSourceBean atomikosDataSource=new AtomikosDataSourceBean();
        atomikosDataSource.setUniqueResourceName("dataSourceOne");
        atomikosDataSource.setXaDataSource(datasource);
        atomikosDataSource.setMinPoolSize(5);
        atomikosDataSource.setMaxPoolSize(20);
//        atomikosDataSource.setTestQuery("SELECT 1");
        return atomikosDataSource;
    }
    @Bean(name = "dataSourceTwo")     //声明其为Bean实例
    public DataSource twoDataSource() {
        DruidXADataSource datasource = new DruidXADataSource();
        datasource.setUrl(this.urlTwo);
        datasource.setUsername(usernameTwo);
        datasource.setPassword(passwordTwo);
        datasource.setDriverClassName(driverClassNameTwo);
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (Exception e) {
            LOGGER.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionPropertiesTwo);
        AtomikosDataSourceBean atomikosDataSource=new AtomikosDataSourceBean();
        atomikosDataSource.setUniqueResourceName("dataSourceTwo");
        atomikosDataSource.setXaDataSource(datasource);
        atomikosDataSource.setMinPoolSize(5);
        atomikosDataSource.setMaxPoolSize(20);
//        atomikosDataSource.setTestQuery("SELECT 1");
        return atomikosDataSource;
    }



    /*@ConditionalOnExpression("#{T(org.apache.commons.lang3.StringUtils).isNotBlank('${spring.datasource.password}')}")
    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //配置最大连接
        dataSource.setMaxActive(20);
        //配置初始连接
        dataSource.setInitialSize(1);
        //配置最小连接
        dataSource.setMinIdle(1);
        //连接等待超时时间
        dataSource.setMaxWait(60000);
        //间隔多久进行检测,关闭空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        //一个连接最小生存时间
        dataSource.setMinEvictableIdleTimeMillis(300000);
        //用来检测是否有效的sql
        dataSource.setValidationQuery("select 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        //打开PSCache,并指定每个连接的PSCache大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(20);
        //配置密码加密
        dataSource.setFilters(filters);
        dataSource.setConnectionProperties(connectionProperties);
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException("druid datasource init fail");
        }
        return dataSource;
    }*/


}
