package com.aas.ssw;

import com.aas.ssw.common.util.EmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SswApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmailTest {

    @Test
    public void sendEmailTest() {
        String[] to = new String[]{"jerry.xu@analyticservice.net", "279474386@qq.com"};
        String[] cc = new String[]{"279474386@qq.com", "jerry.xu@analyticservice.net"};
        String subject = "test";
        String[] attachmentsPath = new String[]{"templates/error.html", "templates/error.html"};
        Map<String, String> inlineResources = new HashMap<>();
        inlineResources.put("testImg", "static/images/test.jpg");
        inlineResources.put("testImg2", "static/images/test.jpg");
        String templatePath = "email/emailTemplate";
        Map<String, String> templateParams = new HashMap<>();
        templateParams.put("id", "123");
        templateParams.put("name", "jason");
        EmailUtil.sendEmail(to, cc, subject, attachmentsPath, inlineResources, templatePath, templateParams);
        System.out.println("发送成功！");
    }
}
