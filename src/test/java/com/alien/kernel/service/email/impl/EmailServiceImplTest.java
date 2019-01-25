package com.alien.kernel.service.email.impl;

import com.alien.kernel.service.email.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/27 09:31
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceImplTest {
    @Autowired
    private IMailService mailService;
    private static String receive="notice_meTnT@163.com";

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail(receive,"Test simple email","Hello this is content");
    }

    @Test
    public void sendHtmlMail(){
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(receive,"This is title",content);
    }
    @Test
    public void sendAttachmentsMail(){
        String filePath="C:\\Users\\FengYunJun\\Desktop\\学习使我快乐.txt";
        mailService.sendAttachmentsMail(receive,"带附件的邮件","请查收附件",filePath);
    }
}