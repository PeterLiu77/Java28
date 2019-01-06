package com.peter;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import org.junit.Test;

public class JavaMail01Test {

	@Test
	public void testJavaMail() throws Exception {
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
		Address to = new InternetAddress("823807392@qq.com");
		message.setRecipient(RecipientType.TO, to);
		// 设置邮件主题
		message.setSubject("Test Hello!");
		// 设置邮件内容
		message.setText("我想试试能不能发送hello!");
		// 保存邮件
		message.saveChanges();

		Transport transport = session.getTransport("smtp");
		// TODO
		transport.connect("smtp.163.com", "15022355314@163.com", "wodemima123");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
}
