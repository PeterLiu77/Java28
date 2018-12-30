package com.peter.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import com.peter.domain.User;
import com.peter.utils.SysConstant;
import com.peter.utils.UtilFuns;

/**
 * @Description: 登录和退出类
 * @Author:		传智播客 java学院	传智.宋江
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014年10月31日
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;



	//SSH传统登录方式
	public String login() throws Exception {
		
//		if(true){
//			String msg = "登录错误，请重新填写用户名密码!";
//			this.addActionError(msg);
//			throw new Exception(msg);
//		}
//		User user = new User(username, password);
//		User login = userService.login(user);
//		if (login != null) {
//			ActionContext.getContext().getValueStack().push(user);
//			session.put(SysConstant.CURRENT_USER_INFO, login);	//记录session
//			return SUCCESS;
//		}
//		return "login";
		
		if(UtilFuns.isEmpty(username)) {
			return "login";
		}
		
		try {
			//1.得到subject
			Subject subject = SecurityUtils.getSubject();
			//2.调用登录方法
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token); //当这一行代码执行时，就会自动跳入到authrealm的认证方法
			//3.登陆成功时就去除登录信息
			User user = (User) subject.getPrincipal();
			session.put(SysConstant.CURRENT_USER_INFO, user);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			request.put("errorInfo", "对不起，用户名密码错误!");
			return "login";
		}
		return SUCCESS;
	}
	
	
	//退出
	public String logout(){
		session.remove(SysConstant.CURRENT_USER_INFO);		//删除session
		
		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

