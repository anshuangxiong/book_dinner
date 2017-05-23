package com.anxiong.bookdinner.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author xiong
 *发送邮件
 */

public class SendMail {
	
	public static void sendMail(String to) throws Exception{
		Properties prop = new Properties();
		prop.setProperty("mail.host","smtp.163.com");
		prop.setProperty("mail.smtp.auth", "true");
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = 
						new PasswordAuthentication("13821752883", "ax307943252");
				return pa;
			}
		};
		Session session = 
				Session.getDefaultInstance(prop,auth);
		session.setDebug(true);
		MimeMessage mm1 = 
				new MimeMessage(session);
		
		Address from = new InternetAddress("13821752883@163.com");
		mm1.setFrom(from);
		mm1.setRecipient(RecipientType.TO,new InternetAddress(to));
		mm1.setSubject("注册成功");
		mm1.setContent("恭喜您，注册成功,<a href='http://localhost:8080/Book_Dinner/index.jsp'>前往订餐</a>", "text/html;charset=UTF-8");
		
		Transport.send(mm1);
	}
}
