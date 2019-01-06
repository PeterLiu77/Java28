package com.peter;

import javax.mail.internet.MimeMessage;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class JavaMail03Test {

	@Test
	public void testJavaMail() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml");
		JavaMailSender sender = (JavaMailSender) ac.getBean("mailSender"); //加载简单邮件发送对象
		// 发送一个允许带图片，同时带附件的邮件
		MimeMessage message = sender.createMimeMessage();
		// 为了更好的操作这个对象，使用一个工具类
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("15022355314@163.com");
		helper.setTo("823807392@qq.com");
		helper.setSubject("我的简历和证件照");
		helper.setText("<html><head></head><body><h1>证件照</h1><a href=http://www.baidu.com>baidu</a><img src=cid:image/></body></html>",true);
		FileSystemResource resource = new FileSystemResource("C:\\Users\\zzd\\Desktop\\应用\\beijing subway.jpg");
		helper.addInline("image", resource);
		FileSystemResource resource2 = new FileSystemResource("C:\\Users\\zzd\\Desktop\\应用\\简历.pdf");
		helper.addAttachment("jianli", resource2);
		sender.send(message);
	}
}
