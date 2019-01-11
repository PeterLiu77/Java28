package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.peter.dao.BaseDao;
import com.peter.domain.ExtCproduct;
import com.peter.service.ExtCproductService;
import com.peter.utils.Page;
import com.peter.utils.UtilFuns;

public class ExtCproductServiceImpl implements ExtCproductService
{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(ExtCproduct entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// id没值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ExtCproduct> entity) {
		baseDao.saveOrUpdateAll(entity);
	}

	public void deleteById(Class<ExtCproduct> entityClass, Serializable id) {
//		String hql = "from ExtCproduct where extCproductInfo.manager.id = ?";
//		List<ExtCproduct> list = baseDao.find(hql, ExtCproduct.class, new Object[] { id });
//		System.out.println(list.size());
//		if (list != null && list.size() > 0) {
//			for (ExtCproduct extCproduct : list) {
//				// deleteById(Dept.class, dept.getId());
//				extCproduct.getExtCproductInfo().setManager(null);
//				baseDao.saveOrUpdate(extCproduct);
//			}
//		}
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<ExtCproduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(ExtCproduct.class, id);
		}
	}
}
