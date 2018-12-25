package com.peter.action.sysadmin;

import java.util.Date;
import com.opensymphony.xwork2.ModelDriven;
import com.peter.action.BaseAction;
import com.peter.domain.Module;
import com.peter.service.ModuleService;
import com.peter.utils.Page;

/**
 * 模型管理action
 * @author zzd
 *
 */
public class ModuleAction extends BaseAction implements ModelDriven<Module>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3373843233258392256L;
	private Module model = new Module();
	@Override
	public Module getModel() {
		// TODO 自动生成的方法存根
		return model;
	}

	//分页
	private Page<Module> page = new Page<Module>();
	public Page<Module> getPage() {
		return page;
	}
	public void setPage(Page<Module> page) {
		this.page = page;
	}

	//注入Module service
	private ModuleService moduleService;
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	/**
	 * 分页查询
	 */
	public String list() {
		moduleService.findPage("from Module", page, Module.class, null);
		//设置分页url
		page.setUrl("moduleAction_list");
		// 将page对象压入栈顶
		super.push(page);
		return "list";
	}

	/**
	 * 查看功能
	 */
	public String toview() {
		// 调用业务方法 根据id得到对象
		Module Module = moduleService.get(Module.class, model.getId());
		// 放入栈顶
		super.push(Module);

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
		moduleService.saveOrUpdate(model);
		//跳页面
		return "alist";
	}

	/**
	 * 进入修改页面
	 */
	public String toupdate() {
		Module obj = moduleService.get(Module.class, model.getId());
		super.push(obj);
		//跳页面
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() {
		Module obj = moduleService.get(Module.class, model.getId());
		obj.setName(model.getName());
		obj.setLayerNum(model.getLayerNum());
		obj.setCpermission(model.getCpermission());
		obj.setCurl(model.getCurl());
		obj.setCtype(model.getCtype());
		obj.setState(model.getState());
		obj.setBelong(model.getBelong());
		obj.setCwhich(model.getCwhich());
		obj.setRemark(model.getRemark());
		obj.setOrderNo(model.getOrderNo());
		moduleService.saveOrUpdate(obj);
		//跳页面
		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		String[] ids = model.getId().split(", ");
		//调用业务方法
		moduleService.delete(Module.class, ids);
		return "alist";
	}
}
