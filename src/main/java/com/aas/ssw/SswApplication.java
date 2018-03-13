package com.aas.ssw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @author xl
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)//解决JMX重复注册bean的问题
//@Import(FdfsClientConfig.class)//获取带有连接池的FastDFS Java客户端，暂时不能用，需要等待jar包升级
public class SswApplication {

    public static void main(String[] args) {
        SpringApplication.run(SswApplication.class, args);
    }
}
