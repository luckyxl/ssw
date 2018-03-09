package com.aas.ssw.common.util;

import com.aas.ssw.common.component.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * 邮件工具类
 */
@Component
public class EmailUtil {
    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    private static JavaMailSender mailSender;


    private static String from;


    private static TemplateEngine templateEngine;



    /**
     *
     * @param to 目的邮箱
     * @param cc 抄送邮箱
     * @param subject 标题
     * @param attachmentsPath 附件路径
     * @param inlineResources 内嵌图片,key：模板中的id,value:图片路径
     * @param templatePath 模板文件路径
     * @param templateParams 模板中的参数
     * @return
     */
    public static String sendEmail(String[] to, String[] cc, String subject, String[] attachmentsPath, Map<String,String> inlineResources, String templatePath, Map<String,String> templateParams){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setSubject(subject);

            //接收邮箱
            for (String toAddress : to) {
                helper.addTo(toAddress);
            }

            //抄送邮箱
            if(cc != null){
                for (String ccAddress : cc) {
                    helper.addCc(ccAddress);
                }
            }

            //附件
            if(attachmentsPath != null){
                for (String attachmentPath : attachmentsPath) {
                    String attachmentName = attachmentPath.substring(attachmentPath.lastIndexOf("/"));
                    helper.addAttachment(attachmentName, new ClassPathResource(attachmentPath));
                }
            }

            //模板参数
            Context context = new Context();
            if(templateParams != null){
                for (String templateParamKey : templateParams.keySet()) {
                    context.setVariable(templateParamKey,templateParams.get(templateParamKey));
                }
            }
            String emailContent = templateEngine.process(templatePath, context);
            helper.setText(emailContent, true);

            //内嵌图片
            if(inlineResources != null){
                for (String inlineResourceId : inlineResources.keySet()) {
                    helper.addInline(inlineResourceId, new ClassPathResource(inlineResources.get(inlineResourceId)));
                }
            }

            //发送
            mailSender.send(message);
            logger.info("邮件发送成功");
            return Constant.SUCCESS;
        } catch (MessagingException e) {
            logger.error("邮件发送异常！", e);
            return Constant.ERROR;
        }
    }
    @Resource
    public void setMailSender(JavaMailSender mailSender) {
        EmailUtil.mailSender = mailSender;
    }
    @Resource
    public void setTemplateEngine(TemplateEngine templateEngine) {
        EmailUtil.templateEngine = templateEngine;
    }

    @Value("${mail.fromMail.addr}")
    public void setFrom(String from) {
        EmailUtil.from = from;
    }


}
