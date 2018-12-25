package com.peter.action.sysadmin;

import java.util.List;
import com.opensymphony.xwork2.ModelDriven;
import com.peter.action.BaseAction;
import com.peter.domain.Dept;
import com.peter.service.DeptService;
import com.peter.utils.Page;

/**
 * 部门管理action
 * @author zzd
 *
 */
public class DeptAction extends BaseAction implements ModelDriven<Dept>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373843233258392256L;
	private Dept model = new Dept();
	@Override
	public Dept getModel() {
		// TODO 自动生成的方法存根
		return model;
	}

	//分页
	private Page<Dept> page = new Page<Dept>();
	public Page<Dept> getPage() {
		return page;
	}
	public void setPage(Page<Dept> page) {
		this.page = page;
	}

	//注入dept service
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	/**
	 * 分页查询
	 */
	public String list() {
		deptService.findPage("from Dept", page, Dept.class, null);
		//设置分页url
		page.setUrl("deptAction_list");
		// 将page对象压入栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 查看功能
	 */
	public String toview() {
		// 调用业务方法 根据id得到对象
		Dept dept = deptService.get(Dept.class, model.getId());
		// 放入栈顶
		super.push(dept);

		return "toview";
	}

	/**
	 * 进入新增页面
	 */
	public String tocreate() {
		//调用业务方法
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		//将查询结果放入值站中
		super.put("deptList", deptList);
		return "tocreate";
	}

	/**
	 * 保存
	 */
	public String insert() {
		deptService.saveOrUpdate(model);
		//跳页面
		return "alist";
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() {
		Dept obj = deptService.get(Dept.class, model.getId());
		super.push(obj);
		//调用业务方法
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		deptList.remove(obj);
		//将查询结果放入值站中
		super.put("deptList", deptList);
		//跳页面
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() {
		Dept obj = deptService.get(Dept.class, model.getId());
		obj.setDeptName(model.getDeptName());
		obj.setParent(model.getParent());
		deptService.saveOrUpdate(obj);
		//跳页面
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete() {
		String[] ids = model.getId().split(", ");
		//调用业务方法
		deptService.delete(Dept.class, ids);
		return "alist";
	}
}
