package com.peter;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JavaMail02Test {

	@Test
	public void testJavaMail() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml");
		SimpleMailMessage message = (SimpleMailMessage) ac.getBean("mailMessage"); //加载简单邮件对象
		JavaMailSender sender = (JavaMailSender) ac.getBean("mailSender"); //加载简单邮件发送对象
		message.setSubject("Spring与JavaMail的测试");
		message.setText("hello!~");
		message.setTo("823807392@qq.com");
		sender.send(message);
	}
}
