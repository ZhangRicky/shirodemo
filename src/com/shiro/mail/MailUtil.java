package com.shiro.mail;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.chainsaw.Main;

/**
 * 邮件发送工具类
 * @author Mark
 *
 */
public class MailUtil {
	
	/**
	 * 验证用户
	 */
	public static Authenticator getAuthenticatorInfo(final Properties props){
		Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        return authenticator;
	}
	
	
	/**
	 * 邮件发送
	 * @param subject	邮件主题
	 * @param content   邮件内容
	 * @param list		批量收件人
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void senMail(String subject,String content,List to) throws MessagingException, UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.ngarihealth.com");
        props.put("mail.user", "zhanggx@ngarihealth.com");
        props.put("mail.password", "rickyxiao791213");

        Session mailSession = Session.getInstance(props, getAuthenticatorInfo(props));
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));message.setFrom(form);
        // 设置收件人
        InternetAddress[] _mailTo =new InternetAddress[to.size()];
        for(int i=0;i<to.size();i++){
        	_mailTo[i] =new InternetAddress(to.get(i).toString());
        }
	    message.setRecipients(RecipientType.TO, _mailTo);
        // 设置抄送人
        //InternetAddress cc = new InternetAddress("luo_aaaaa@yeah.net");
        //message.setRecipient(RecipientType.CC, cc);
        // 设置密�?，其他的收件人不能看到密送的邮件地址
        //InternetAddress bcc = new InternetAddress("aaaaa@163.com");
        //message.setRecipient(RecipientType.CC, bcc);
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=UTF-8");
        Transport.send(message);
	}  
	
	public static void main(String[] args) {
		
	}
}
