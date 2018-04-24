package com.aas.ssw;

import com.aas.ssw.business.example.entity.User;
import com.aas.ssw.common.component.ElasticSearchBean;
import com.aas.ssw.common.component.Result;
import com.aas.ssw.common.util.ElasticUtil;
import io.searchbox.core.search.sort.Sort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
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
        List<Sort> sortFields = Arrays.asList(new Sort[]{new Sort("realName",Sort.Sorting.DESC),new Sort("loginName",Sort.Sorting.ASC)});
        List<String> matchingFields = Arrays.asList(new String[]{"password","email"});
        ElasticSearchBean elasticSearchBean = new ElasticSearchBean();
        elasticSearchBean.setIndex(index);
        elasticSearchBean.setType(type);
        elasticSearchBean.setKeyword(keyword);
        elasticSearchBean.setFrom(from);
        elasticSearchBean.setSize(size);
        elasticSearchBean.setHighLightsFields(highLightsFields);
        elasticSearchBean.setFullTextMatching(true);
        elasticSearchBean.setSortFields(sortFields);
        elasticSearchBean.setMatchingFields(matchingFields);

        Result result = ElasticUtil.get(clazz, elasticSearchBean);
        System.out.println(result.getFlag());
        System.out.println(result.getMsg());
        System.out.println(result.getTotal());
        System.out.println(result.getData());
    }
}
