package com.course.springboot2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class MailTest {
    @Autowired
    JavaMailSender javaMailSender;

    @Test
    public void test1(){
        // 发件人
        String from = "603280349@qq.com";
        // 收件人
        String to = "603280349@qq.com";
        // 邮件主题
        String subject = "mail测试";
        // 邮件内容
        String text = "通常在实际项目中，也有其他很多地方会用到邮件发送，比如通过邮件注册账户/找回密码，通过邮件发送订阅消息等等";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        for (int i = 0; i < 10; i++) {
            javaMailSender.send(simpleMailMessage);
        }

    }

}
