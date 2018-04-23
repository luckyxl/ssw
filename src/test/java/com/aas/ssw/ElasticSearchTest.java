package com.aas.ssw;

import com.aas.ssw.business.example.entity.User;
import com.aas.ssw.common.component.Result;
import com.aas.ssw.common.util.ElasticUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ElasticSearchTest {



    @Test
    public void saveTest() {
        User user = new User();
        user.setRealName("王五");
        user.setLoginName("wangwu");
        user.setAge(19);
        user.setGender(true);
        user.setStatus(0);
        Result result = ElasticUtil.save(user, "user", "user");
        System.out.println(result.getFlag());
        System.out.println(result.getMsg());
    }
    @Test
    public void updateTest() {
        User user = new User();
        user.setRealName("王五2号");
        user.setLoginName("wangwu");
        user.setAge(19);
        user.setGender(true);
        user.setStatus(0);
        Result result = ElasticUtil.updateById(user, "user", "user","AWLxcPgxbdesqcfPx6TL");
        System.out.println(result.getFlag());
        System.out.println(result.getMsg());
    }
    @Test
    public void deleteTest() {
        Result result = ElasticUtil.deleteById("user", "user","AWLxcPgxbdesqcfPx6TL");
        System.out.println(result.getFlag());
        System.out.println(result.getMsg());
    }
    @Test
    public void searchTest() {
        Class clazz = User.class;
        String index = "user";
        String type = "user";
        String keyword = "王";
        int from = 0;
        int size = 10;
        List<String> highLightsFields = Arrays.asList(new String[]{"realName","loginName"});
        Result result = ElasticUtil.get(clazz, index, type, keyword, from, size, highLightsFields);
        System.out.println(result.getFlag());
        System.out.println(result.getMsg());
        System.out.println(result.getTotal());
        System.out.println(result.getData());
    }
}
