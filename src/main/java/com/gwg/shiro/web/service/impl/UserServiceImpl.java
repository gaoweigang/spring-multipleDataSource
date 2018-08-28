package com.gwg.shiro.web.service.impl;

import com.gwg.shiro.web.dao.UserDao;
import com.gwg.shiro.web.dto.UserDto;
import com.gwg.shiro.web.model.User;
import com.gwg.shiro.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public boolean addUser(UserDto dto) {
		return userDao.addUser(dto);
	}

	/**
	 * 根据id查询用户信息
	 */
	public User queryUserInfoById(UserDto dto) {
		return userDao.queryUserInfoById(dto);
	}
}
