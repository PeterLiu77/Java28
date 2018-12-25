package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.peter.dao.BaseDao;
import com.peter.domain.Role;
import com.peter.service.RoleService;
import com.peter.utils.Page;
import com.peter.utils.UtilFuns;

public class RoleServiceImpl implements RoleService
{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Role> find(String hql, Class<Role> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Role get(Class<Role> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Role> findPage(String hql, Page<Role> page, Class<Role> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Role entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// id没值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Role> entity) {
		baseDao.saveOrUpdateAll(entity);
	}

	public void deleteById(Class<Role> entityClass, Serializable id) {
//		String hql = "from Role where roleInfo.manager.id = ?";
//		List<Role> list = baseDao.find(hql, Role.class, new Object[] { id });
//		System.out.println(list.size());
//		if (list != null && list.size() > 0) {
//			for (Role role : list) {
//				// deleteById(Dept.class, dept.getId());
//				role.getRoleInfo().setManager(null);
//				baseDao.saveOrUpdate(role);
//			}
//		}
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<Role> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(Role.class, id);
		}
	}
}
