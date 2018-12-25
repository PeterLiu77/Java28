package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.peter.dao.BaseDao;
import com.peter.domain.Module;
import com.peter.service.ModuleService;
import com.peter.utils.Page;
import com.peter.utils.UtilFuns;

public class ModuleServiceImpl implements ModuleService
{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Module> find(String hql, Class<Module> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Module get(Class<Module> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Module> findPage(String hql, Page<Module> page, Class<Module> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Module entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// id没值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Module> entity) {
		baseDao.saveOrUpdateAll(entity);
	}

	public void deleteById(Class<Module> entityClass, Serializable id) {
//		String hql = "from Module where moduleInfo.manager.id = ?";
//		List<Module> list = baseDao.find(hql, Module.class, new Object[] { id });
//		System.out.println(list.size());
//		if (list != null && list.size() > 0) {
//			for (Module module : list) {
//				// deleteById(Dept.class, dept.getId());
//				module.getModuleInfo().setManager(null);
//				baseDao.saveOrUpdate(module);
//			}
//		}
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<Module> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(Module.class, id);
		}
	}
}
