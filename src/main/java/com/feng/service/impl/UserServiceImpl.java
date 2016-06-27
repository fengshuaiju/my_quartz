package com.feng.service.impl;

import org.springframework.stereotype.Service;

import com.feng.entity.User;
import com.feng.security.util.Digests;
import com.feng.service.UserService;
import com.feng.system.util.DateUtils;
import com.feng.system.util.Encodes;

/**
 * 用户service
 * @author ty
 * @date 2015年1月13日
 */
@Service
public class UserServiceImpl implements UserService{

	/**加密方法*/
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;	//盐长度

	//@Autowired
	//private UserDao userDao;

//	@Override
//	public HibernateDao<User, Integer> getEntityDao() {
//		return userDao;
//	}

	/**
	 * 保存用户
	 * @param user
	 */
	@Override
	public void save(User user) {
		entryptPassword(user);
		user.setCreateDate(DateUtils.getSysTimestamp());
		//userDao.save(user);
	}

	/**
	 * 修改密码
	 * @param user
	 */
	public void updatePwd(User user) {
		entryptPassword(user);
		//userDao.save(user);
	}
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void delete(Integer id){
		//if(!isSupervisor(id))
			//userDao.delete(id);
	}
	
	/**
	 * 按登录名查询用户
	 * @param loginName
	 * @return 用户对象
	 */
	public User getUser(String loginName) {
		//return userDao.findUniqueBy("loginName", loginName);
		//return null;
		
		return new User("admin","admin","159ae5f48f14e89f3f9f54dc995f1f276d472b54");
	}
	
	/**
	 * 判断是否超级管理员
	 * @param id
	 * @return boolean
	 */
	public boolean isSupervisor(Integer id) {
		return id == 1;
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(),salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 验证原密码是否正确
	 * @param user
	 * @param oldPwd
	 * @return
	 */
	public boolean checkPassword(User user,String oldPassword){
		byte[] salt =Encodes.decodeHex(user.getSalt()) ;
		byte[] hashPassword = Digests.sha1(oldPassword.getBytes(),salt, HASH_INTERATIONS);
		if(user.getPassword().equals(Encodes.encodeHex(hashPassword))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 修改用户登录
	 * @param user
	 */
	public void updateUserLogin(User user){
		user.setLoginCount((user.getLoginCount()==null?0:user.getLoginCount())+1);
		user.setPreviousVisit(user.getLastVisit());
		user.setLastVisit(DateUtils.getSysTimestamp());
		//update(user);
	}
	
}
