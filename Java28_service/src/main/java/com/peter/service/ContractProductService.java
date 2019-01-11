package com.peter.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import com.peter.domain.ContractProduct;
import com.peter.utils.Page;

public interface ContractProductService
{
	//查询所有，带条件查询
		public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params);
		//获取一条记录
		public ContractProduct get(Class<ContractProduct> entityClass, Serializable id);
		//分页查询，将数据封装到一个page分页工具类对象
		public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params);
		
		//新增和修改保存
		public void saveOrUpdate(ContractProduct entityClass);
		//批量新增和修改保存
		public void saveOrUpdateAll(Collection<ContractProduct> entityClass);
		
		//单条删除，按id
		public void deleteById(Class<ContractProduct> entityClass, Serializable id);
		//批量删除
		public void delete(Class<ContractProduct> entityClass, Serializable[] ids);
}
