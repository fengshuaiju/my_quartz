package com.feng.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.feng.entity.Permission;

public interface PermissionService {

	/**
	 * 添加菜单基础操作
	 * @param pid 菜单id
	 * @param pName 菜单权限标识名
	 */
	@Transactional(readOnly = false)
	public void addBaseOpe(Integer pid,String pClassName);
	
	/**
	 * 获取角色拥有的权限集合
	 * @param userId
	 * @return 结果集合
	 */
	public List<Permission> getPermissions(Integer userId);
	
	/**
	 * 获取角色拥有的菜单
	 * @param userId
	 * @return 菜单集合
	 */
	public List<Permission> getMenus(Integer userId);
	
	/**
	 * 获取所有菜单
	 * @return 菜单集合
	 */
	public List<Permission> getMenus();
	
	/**
	 * 获取菜单下的操作
	 * @param pid
	 * @return 操作集合
	 */
	public List<Permission> getMenuOperation(Integer pid);
}
