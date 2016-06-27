package com.feng.service;

import com.feng.entity.User;

public interface UserService {

	/**
	 * 保存用户
	 * @param user
	 */
	public void save(User user);

	/**
	 * 修改密码
	 * @param user
	 */
	public void updatePwd(User user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 按登录名查询用户
	 * @param loginName
	 * @return 用户对象
	 */
	public User getUser(String loginName);
	
	/**
	 * 判断是否超级管理员
	 * @param id
	 * @return boolean
	 */
	public boolean isSupervisor(Integer id);
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public void entryptPassword(User user);
	
	/**
	 * 验证原密码是否正确
	 * @param user
	 * @param oldPwd
	 * @return
	 */
	public boolean checkPassword(User user,String oldPassword);
	
	/**
	 * 修改用户登录
	 * @param user
	 */
	public void updateUserLogin(User user);

}
