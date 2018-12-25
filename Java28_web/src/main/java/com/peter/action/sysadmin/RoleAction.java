package com.peter.action.sysadmin;

import java.util.Date;
import com.opensymphony.xwork2.ModelDriven;
import com.peter.action.BaseAction;
import com.peter.domain.Role;
import com.peter.service.RoleService;
import com.peter.utils.Page;

/**
 * 角色管理action
 * @author zzd
 *
 */
public class RoleAction extends BaseAction implements ModelDriven<Role>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373843233258392256L;
	private Role model = new Role();
	@Override
	public Role getModel() {
		// TODO 自动生成的方法存根
		return model;
	}

	//分页
	private Page<Role> page = new Page<Role>();
	public Page<Role> getPage() {
		return page;
	}
	public void setPage(Page<Role> page) {
		this.page = page;
	}

	//注入Role service
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 分页查询
	 */
	public String list() {
		roleService.findPage("from Role", page, Role.class, null);
		//设置分页url
		page.setUrl("roleAction_list");
		// 将page对象压入栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 查看功能
	 */
	public String toview() {
		// 调用业务方法 根据id得到对象
		Role Role = roleService.get(Role.class, model.getId());
		// 放入栈顶
		super.push(Role);

		return "toview";
	}

	/**
	 * 进入新增页面
	 */
	public String tocreate() {
		return "tocreate";
	}

	/**
	 * 保存
	 */
	public String insert() {
		model.setCreateTime(new Date());
		model.setUpdateTime(new Date());
		roleService.saveOrUpdate(model);
		//跳页面
		return "alist";
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() {
		Role obj = roleService.get(Role.class, model.getId());
		super.push(obj);
		//跳页面
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() {
		Role obj = roleService.get(Role.class, model.getId());
		obj.setName(model.getName());
		obj.setRemark(model.getRemark()); 
		roleService.saveOrUpdate(obj);
		//跳页面
		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		String[] ids = model.getId().split(", ");
		//调用业务方法
		roleService.delete(Role.class, ids);
		return "alist";
	}
}
