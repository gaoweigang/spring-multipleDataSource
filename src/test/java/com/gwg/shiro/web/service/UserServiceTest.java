package com.gwg.shiro.web.service;

import com.gwg.shiro.web.config.mvc.RootConfig;
import com.gwg.shiro.web.dto.UserDto;
import com.gwg.shiro.web.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    private UserService userService;

    /**
     * 每个测试方法执行之前执行一次
     */
    @Before
    public void before(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        userService = applicationContext.getBean(UserService.class);
    }


    @Test
    public void testAddUser() throws BusinessException {

        UserDto dto = new UserDto();
        dto.setUserId("13817191471");
        dto.setUsername("高伟刚");
        dto.setCardNo("420881199101095174");
        dto.setEmail("13817191469@163.com");
        dto.setEntryTime(new Date());
        dto.setMobile("13817191469");
        userService.addUser(dto);

    }



    @Test
    public void testQueryUserInfoById() throws BusinessException{

        UserDto dto = new UserDto();
        dto.setId(23L);

        userService.queryUserInfoById(dto);
    }
}
