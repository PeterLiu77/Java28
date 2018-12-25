package com.peter.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable
{
	protected static final long serialVersionUID = 1L;
	protected String createBy;
	protected String createDept;
	protected Date createTime;
	protected String updateBy;
	protected Date UpdateTime;

	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return UpdateTime;
	}
	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
