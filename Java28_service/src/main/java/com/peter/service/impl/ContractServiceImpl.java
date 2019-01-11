package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.peter.dao.BaseDao;
import com.peter.domain.Contract;
import com.peter.service.ContractService;
import com.peter.utils.Page;
import com.peter.utils.UtilFuns;

public class ContractServiceImpl implements ContractService
{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Contract> find(String hql, Class<Contract> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Contract get(Class<Contract> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Contract> findPage(String hql, Page<Contract> page, Class<Contract> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Contract entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// id没值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Contract> entity) {
		baseDao.saveOrUpdateAll(entity);
	}

	public void deleteById(Class<Contract> entityClass, Serializable id) {
//		String hql = "from Contract where contractInfo.manager.id = ?";
//		List<Contract> list = baseDao.find(hql, Contract.class, new Object[] { id });
//		System.out.println(list.size());
//		if (list != null && list.size() > 0) {
//			for (Contract contract : list) {
//				// deleteById(Dept.class, dept.getId());
//				contract.getContractInfo().setManager(null);
//				baseDao.saveOrUpdate(contract);
//			}
//		}
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<Contract> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(Contract.class, id);
		}
	}
}
