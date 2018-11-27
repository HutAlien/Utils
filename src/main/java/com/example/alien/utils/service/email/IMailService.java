package com.example.alien.utils.service.email;

/**
 * @Auther: FengYunJun
 * @Date: 2018/11/27 09:21
 * @Description:
 */
public interface IMailService {
    //简单邮件发送
    void sendSimpleMail(String to, String subject, String content);

    //发送html格式邮件
    void sendHtmlMail(String to, String subject, String content);

    //发送带附件邮件
    void sendAttachmentsMail(String to,String subject,String content,String filePath);
}
