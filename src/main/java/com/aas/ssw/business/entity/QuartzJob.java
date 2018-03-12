package com.aas.ssw.business.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**job,测试用
 * @author xl
 */
@Component
public class QuartzJob {
    private static final Logger LOGGER =  LoggerFactory.getLogger(QuartzJob.class);
    public void sayHello(){
        LOGGER.info("hello world!");
    }
    public void sayHi(){
        LOGGER.info("hi world!");
    }
}
