package com.peter.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import com.peter.domain.User;
import com.peter.utils.Page;

public interface UserService
{
	//查询所有，带条件查询
		public List<User> find(String hql, Class<User> entityClass, Object[] params);
		//获取一条记录
		public User get(Class<User> entityClass, Serializable id);
		//分页查询，将数据封装到一个page分页工具类对象
		public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params);
		
		//新增和修改保存
		public void saveOrUpdate(User enUseriUsery);
		//批量新增和修改保存
		public void saveOrUpdateAll(Collection<User> enUseriUserys);
		
		//单条删除，按id
		public void deleteById(Class<User> entityClass, Serializable id);
		//批量删除
		public void delete(Class<User> entityClass, Serializable[] ids);
}
