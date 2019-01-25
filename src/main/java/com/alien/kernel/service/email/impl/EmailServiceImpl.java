package com.alien.kernel.service.email.impl;

import com.alien.kernel.service.email.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/27 09:20
 * @Description: 邮件发送服务实现
 */
@Slf4j
@Component
public class EmailServiceImpl implements IMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    //简单邮件发送
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送异常",e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);//true表示需要创建一个multipart message
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            mailSender.send(message);
            log.info("邮件发送成功");
        }catch (Exception e){
            log.error("邮件发送异常",e);
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content,String filePath) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setText(content);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            //获取附件
            FileSystemResource file=new FileSystemResource(new File(filePath));
            String fileName=filePath.substring(filePath.lastIndexOf(File.separator));

            helper.addAttachment(fileName,file);
            mailSender.send(message);
            log.info("邮件发送成功");
        } catch (MessagingException e) {
            log.error("邮件发送异常",e);
        }
    }
}
