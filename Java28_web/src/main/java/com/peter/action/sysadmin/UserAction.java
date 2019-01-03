package com.peter.action.sysadmin;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.opensymphony.xwork2.ModelDriven;
import com.peter.action.BaseAction;
import com.peter.domain.Dept;
import com.peter.domain.Role;
import com.peter.domain.User;
import com.peter.exception.SysException;
import com.peter.service.DeptService;
import com.peter.service.RoleService;
import com.peter.service.UserService;
import com.peter.utils.Page;

/**
 * 
 * 用户管理action
 * 
 * @author zzd
 *
 */
public class UserAction extends BaseAction implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3373843233258392256L;
	private User model = new User();

	public User getModel() {
		return model;
	}

	// 分页
	private Page<User> page = new Page<User>();

	public Page<User> getPage() {
		return page;
	}

	public void setPage(Page<User> page) {
		this.page = page;
	}

	// 注入User service
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private DeptService deptService;

	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 分页查询
	 */
	public String list() {
		userService.findPage("from User", page, User.class, null);
		// 设置分页url
		page.setUrl("userAction_list");
		// 将page对象压入栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 查看功能
	 * 
	 * @throws SysException
	 */
	public String toview() {
		// 调用业务方法 根据id得到对象
		User User = userService.get(User.class, model.getId());
		// 放入栈顶
		super.push(User);
		return "toview";
	}

	/**
	 * 进入新增页面
	 */
	public String tocreate() {
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		super.put("deptList", deptList);
		// 调用业务方法
		List<User> userList = userService.find("from User where state = 1", User.class, null);
		// 将查询结果放入值站中
		super.put("userList", userList);
		return "tocreate";
	}

	/**
	 * 保存
	 */
	public String insert() {
		model.setUpdateTime(new Date());
		model.getUserInfo().setCreateTime(new Date());
		model.getUserInfo().setUpdateTime(new Date());
		userService.saveOrUpdate(model);
		// 跳页面
		return "alist";
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() {
		User obj = userService.get(User.class, model.getId());
		super.push(obj);
		// 调用业务方法
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		deptList.remove(obj.getDept());
		// 将查询结果放入值站中
		super.put("deptList", deptList);
		// 跳页面
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() {
		User obj = userService.get(User.class, model.getId());
		obj.setUserName(model.getUserName());
		obj.setDept(model.getDept());
		obj.setState(model.getState());
		userService.saveOrUpdate(obj);
		// 跳页面
		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		String[] ids = model.getId().split(", ");
		// 调用业务方法
		userService.delete(User.class, ids);
		return "alist";
	}

	/**
	 * 查看角色
	 */
	public String torole() {
		// 根据id得到对象
		User user = userService.get(User.class, model.getId());
		// 将对象保存到值站中
		super.push(user);
		// 得到角色列表
		List<Role> roleList = roleService.find("from Role", Role.class, null);
		// 将roleList放入值站中
		super.put("roleList", roleList);
		// 得到当前用户的角色列表
		Set<Role> roles = user.getRoles();
		StringBuilder sb = new StringBuilder();
		for (Role role : roles) {
			sb.append(role.getName()).append(",");
		}
		super.put("roleString", sb.toString());
		return "torole";
	}

	private String[] roleIds; // 保存角色的列表

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * 保存角色
	 */
	public String role() {
		User user = userService.get(User.class, model.getId());
		Set<Role> roles = new HashSet<>();
		for (String roleId : roleIds) {
			Role role = roleService.get(Role.class, roleId);
			roles.add(role);
		}
		user.setRoles(roles);
		// 保存
		userService.saveOrUpdate(user);
		return "alist";
	}

}
