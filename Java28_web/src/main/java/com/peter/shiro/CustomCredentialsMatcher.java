package com.peter.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import com.peter.utils.Encrypt;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

	// 密码比较规则 token是用户在界面输入的用户名密码.info是从数据库中的到的加密信息
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		Object pwd = Encrypt.md5(new String(upToken.getPassword()), upToken.getUsername());
		Object dbpwd = info.getCredentials();
		return this.equals(pwd, dbpwd);
	}

}
