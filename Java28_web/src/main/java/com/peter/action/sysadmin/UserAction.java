package com.peter.action.sysadmin;

import java.util.Date;
import java.util.List;
import com.opensymphony.xwork2.ModelDriven;
import com.peter.action.BaseAction;
import com.peter.domain.Dept;
import com.peter.domain.User;
import com.peter.service.DeptService;
import com.peter.service.UserService;
import com.peter.utils.Page;

/**
 * 
 * 用户管理action
 * @author zzd
 *
 */
public class UserAction extends BaseAction implements ModelDriven<User>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373843233258392256L;
	private User model = new User();
	@Override
	public User getModel() {
		// TODO 自动生成的方法存根
		return model;
	}

	//分页
	private Page<User> page = new Page<User>();
	public Page<User> getPage() {
		return page;
	}
	public void setPage(Page<User> page) {
		this.page = page;
	}

	//注入User service
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	/**
	 * 分页查询
	 */
	public String list() {
		userService.findPage("from User", page, User.class, null);
		//设置分页url
		page.setUrl("userAction_list");
		// 将page对象压入栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 查看功能
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
		//调用业务方法
		List<User> userList = userService.find("from User where state = 1", User.class, null);
		//将查询结果放入值站中
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
		//跳页面
		return "alist";
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() {
		User obj = userService.get(User.class, model.getId());
		super.push(obj);
		//调用业务方法
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		deptList.remove(obj.getDept());
		//将查询结果放入值站中
		super.put("deptList", deptList);
		//跳页面
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
		//跳页面
		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		String[] ids = model.getId().split(", ");
		//调用业务方法
		userService.delete(User.class, ids);
		return "alist";
	}
}
