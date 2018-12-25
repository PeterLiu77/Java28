package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import com.peter.dao.BaseDao;
import com.peter.domain.Dept;
import com.peter.service.DeptService;
import com.peter.utils.Page;
import com.peter.utils.UtilFuns;

public class DeptServiceImpl implements DeptService
{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Dept get(Class<Dept> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Dept enDeptiDepty) {
		if (UtilFuns.isEmpty(enDeptiDepty.getId())) {
			// id没值
			enDeptiDepty.setState(1);//启用
		}
		baseDao.saveOrUpdate(enDeptiDepty);
	}

	public void saveOrUpdateAll(Collection<Dept> enDeptiDeptys) {
		baseDao.saveOrUpdateAll(enDeptiDeptys);
	}

	public void deleteById(Class<Dept> entityClass, Serializable id) {
		// 查找这个部门的子部门
		String hql = "from Dept where parent.id = ?";
		List<Dept> list = baseDao.find(hql, Dept.class, new Object[] { id });
		if (list != null && list.size() > 0) {
			for (Dept dept : list) {
				// deleteById(Dept.class, dept.getId());
				dept.setParent(null);
				baseDao.saveOrUpdate(dept);
			}
		}
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<Dept> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(Dept.class, id);
		}
	}
}
