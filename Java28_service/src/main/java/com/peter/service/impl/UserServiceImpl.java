package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.peter.dao.BaseDao;
import com.peter.domain.User;
import com.peter.service.UserService;
import com.peter.utils.Page;
import com.peter.utils.UtilFuns;

public class UserServiceImpl implements UserService
{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
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

	public void saveOrUpdate(User entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// id没值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			entity.getUserInfo().setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<User> entity) {
		baseDao.saveOrUpdateAll(entity);
	}

	public void deleteById(Class<User> entityClass, Serializable id) {
//		String hql = "from User where userInfo.manager.id = ?";
//		List<User> list = baseDao.find(hql, User.class, new Object[] { id });
//		System.out.println(list.size());
//		if (list != null && list.size() > 0) {
//			for (User user : list) {
//				// deleteById(Dept.class, dept.getId());
//				user.getUserInfo().setManager(null);
//				baseDao.saveOrUpdate(user);
//			}
//		}
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<User> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(User.class, id);
		}
	}
}
