package com.peter.action.sysadmin;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.peter.action.BaseAction;
import com.peter.domain.Module;
import com.peter.domain.Role;
import com.peter.exception.SysException;
import com.peter.service.ModuleService;
import com.peter.service.RoleService;
import com.peter.utils.Page;

/**
 * 角色管理action
 * 
 * @author zzd
 *
 */
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3373843233258392256L;
	private Role model = new Role();

	public Role getModel() {
		return model;
	}

	// 分页
	private Page<Role> page = new Page<Role>();

	public Page<Role> getPage() {
		return page;
	}

	public void setPage(Page<Role> page) {
		this.page = page;
	}

	// 注入Role service
	private RoleService roleService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	// 注入module service
	private ModuleService moduleService;

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	/**
	 * 分页查询
	 */
	public String list() {
		roleService.findPage("from Role", page, Role.class, null);
		// 设置分页url
		page.setUrl("roleAction_list");
		// 将page对象压入栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 查看功能
	 * @throws SysException 
	 */
	public String toview() throws SysException {
		try {
			// 调用业务方法 根据id得到对象
			Role Role = roleService.get(Role.class, model.getId());
			// 放入栈顶
			super.push(Role);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("对不起，操作失败，请先勾选！");
		}
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
		// 跳页面
		return "alist";
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() {
		Role obj = roleService.get(Role.class, model.getId());
		super.push(obj);
		// 跳页面
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
		// 跳页面
		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		String[] ids = model.getId().split(", ");
		// 调用业务方法
		roleService.delete(Role.class, ids);
		return "alist";
	}

	/**
	 * 查看权限
	 */
	public String tomodule() {
		Role role = roleService.get(Role.class, model.getId());
		super.push(role);
		return "tomodule";
	}

	/**
	 * 为了使用ztree树，就要准备好json数据 数据结构如下
	 * [{"id":"模块id","pid":"父模块id","name":"模块名","checked":"true选中"},{},{}]
	 * 借助response对象输出
	 * 
	 * @throws IOException
	 */
	public void roleModuleJsonStr() throws IOException {
		Role role = roleService.get(Role.class, model.getId());
		Set<Module> moduleSet = role.getModules();
		List<Module> moduleList = moduleService.find("from Module", Module.class, null);
		int size = moduleList.size();
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Module module : moduleList) {
			sb.append("{\"id\":\"").append(module.getId());
			sb.append("\",\"pId\":\"").append(module.getParentId());
			sb.append("\",\"name\":\"").append(module.getName());
			sb.append("\",\"checked\":\"");
			if (moduleSet.contains(module)) {
				sb.append("true");
			} else {
				sb.append("false");
			}
			sb.append("\"}");
			if (size > 0) {
				sb.append(",");
			}
		}
		sb.append("]");
		// 得到response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		// 使用response对象输出json串
		System.out.println(sb.toString());
		response.getWriter().write(sb.toString());
	}

	/**
	 * 保存权限
	 */
	private String moduleIds;

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String module() {
		Role role = roleService.get(Role.class, model.getId());
		String[] ids = moduleIds.split(",");
		Set<Module> modules = new HashSet<>();
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				Module module = moduleService.get(Module.class, id);
				modules.add(module);
			}
		}
		role.setModules(modules);
		roleService.saveOrUpdate(role);
		return "alist";
	}
}
