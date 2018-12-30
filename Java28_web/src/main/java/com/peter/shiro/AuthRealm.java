package com.peter.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.peter.domain.Module;
import com.peter.domain.Role;
import com.peter.domain.User;
import com.peter.service.UserService;

public class AuthRealm extends AuthorizingRealm {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 授权方法
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		User user = (User) pc.fromRealm(this.getName()).iterator().next();
		Set<Role> roles = user.getRoles();// 对象导航
		List<String> permissions = new ArrayList<>();
		for (Role role : roles) {
			//遍历角色
			Set<Module> modules = role.getModules(); //得到每个角色下的模块列表
			for (Module module : modules) {
				permissions.add(module.getName());
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);// 添加用户权限
		return info;
	}

	/**
	 * 认证方法 token 用户在界面上输入的用户名和密码
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String hql = "from User Where userName=?";
		List<User> list = userService.find(hql, User.class, new String[] { upToken.getUsername() });
		if (list != null && list.size() > 0) {
			User user = list.get(0);
			AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
			return info;
		}
		return null;
	}
}
