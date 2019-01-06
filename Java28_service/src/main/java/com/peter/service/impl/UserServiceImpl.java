package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.peter.dao.BaseDao;
import com.peter.domain.User;
import com.peter.service.UserService;
import com.peter.utils.Encrypt;
import com.peter.utils.Page;
import com.peter.utils.SysConstant;
import com.peter.utils.UtilFuns;

public class UserServiceImpl implements UserService {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	private SimpleMailMessage mailMessage;

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public List<User> find(String hql, Class<User> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(final User entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// id没值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			entity.getUserInfo().setId(id);
			// 添加密码
			entity.setPassword(Encrypt.md5(SysConstant.DEFAULT_PASS, entity.getUserName()));
			baseDao.saveOrUpdate(entity);

			// 开启一个线程 去完成邮件发送 用mailutil工具类发邮件
			/*
			 * Thread thread = new Thread(new Runnable() { public void run() { String text =
			 * "欢迎您，您的用户名："+entity.getUserName()+"，初始密码："+SysConstant.DEFAULT_PASS; try {
			 * MailUtil.sendMessage(entity.getUserInfo().getEmail(), "新员工入职系统账户初始密码", text);
			 * } catch (Exception e) { e.printStackTrace(); } } }); thread.start();
			 */

			// spring集成后的代码发邮件
			Thread thread = new Thread(new Runnable() {
				public void run() {
					mailMessage.setTo(entity.getUserInfo().getEmail());
					mailMessage.setSubject("新员工入职系统账户初始密码");
					mailMessage.setText("欢迎您，您的用户名："+entity.getUserName()+"，初始密码："+SysConstant.DEFAULT_PASS);
					mailSender.send(mailMessage);
				}
			});
			thread.start();
		} else {
			// 修改
			baseDao.saveOrUpdate(entity);
		}
	}

	public void saveOrUpdateAll(Collection<User> entity) {
		baseDao.saveOrUpdateAll(entity);
	}

	public void deleteById(Class<User> entityClass, Serializable id) {
		// String hql = "from User where userInfo.manager.id = ?";
		// List<User> list = baseDao.find(hql, User.class, new Object[] { id });
		// System.out.println(list.size());
		// if (list != null && list.size() > 0) {
		// for (User user : list) {
		// // deleteById(Dept.class, dept.getId());
		// user.getUserInfo().setManager(null);
		// baseDao.saveOrUpdate(user);
		// }
		// }
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<User> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(User.class, id);
		}
	}
}
