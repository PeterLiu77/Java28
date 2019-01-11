package com.peter.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import com.peter.dao.BaseDao;
import com.peter.domain.ContractProduct;
import com.peter.service.ContractProductService;
import com.peter.utils.Page;
import com.peter.utils.UtilFuns;

public class ContractProductServiceImpl implements ContractProductService
{
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public ContractProduct get(Class<ContractProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(ContractProduct entity) {
		if (UtilFuns.isEmpty(entity.getId())) {
			// id没值
			String id = UUID.randomUUID().toString();
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<ContractProduct> entity) {
		baseDao.saveOrUpdateAll(entity);
	}

	public void deleteById(Class<ContractProduct> entityClass, Serializable id) {
//		String hql = "from ContractProduct where contractProductInfo.manager.id = ?";
//		List<ContractProduct> list = baseDao.find(hql, ContractProduct.class, new Object[] { id });
//		System.out.println(list.size());
//		if (list != null && list.size() > 0) {
//			for (ContractProduct contractProduct : list) {
//				// deleteById(Dept.class, dept.getId());
//				contractProduct.getContractProductInfo().setManager(null);
//				baseDao.saveOrUpdate(contractProduct);
//			}
//		}
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<ContractProduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			this.deleteById(ContractProduct.class, id);
		}
	}
}
