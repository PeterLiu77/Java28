package com.peter.utils;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtil {
	/**
	 * 发送一封邮件
	 * @param toAddress 发送到哪个地址
	 * @param subject 邮件的主题
	 * @param text 邮件的内容
	 * @throws Exception
	 */
	public static void sendMessage(String toAddress,String subject,String text) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", true);
		Session session = Session.getInstance(props); // 得到session
		session.setDebug(true);// 启用调试,可以在控制台输出smpt协议应答的过程
		// 创建一个mimeMessage格式的邮件
		MimeMessage message = new MimeMessage(session);
		// 设置发送者
		Address from = new InternetAddress("15022355314@163.com");
		message.setFrom(from);
		// 设置接收者
		Address to = new InternetAddress(toAddress);
		message.setRecipient(RecipientType.TO, to);
		// 设置邮件主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setText(text);
		// 保存邮件
		message.saveChanges();

		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", "15022355314@163.com", "wodemima123");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
}
