package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.peter.dao.BaseDao;
import com.peter.domain.Factory;
import com.peter.service.FactoryService;
import com.peter.utils.Page;
import com.peter.utils.UtilFuns;

public class FactoryServiceImpl implements FactoryService
{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Factory> find(String hql, Class<Factory> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Factory get(Class<Factory> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Factory> findPage(String hql, Page<Factory> page, Class<Factory> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Factory entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// id没值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Factory> entity) {
		baseDao.saveOrUpdateAll(entity);
	}

	public void deleteById(Class<Factory> entityClass, Serializable id) {
//		String hql = "from Factory where factoryInfo.manager.id = ?";
//		List<Factory> list = baseDao.find(hql, Factory.class, new Object[] { id });
//		System.out.println(list.size());
//		if (list != null && list.size() > 0) {
//			for (Factory factory : list) {
//				// deleteById(Dept.class, dept.getId());
//				factory.getFactoryInfo().setManager(null);
//				baseDao.saveOrUpdate(factory);
//			}
//		}
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<Factory> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(Factory.class, id);
		}
	}
}
