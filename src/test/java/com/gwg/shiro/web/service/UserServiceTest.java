package com.gwg.shiro.web.service;

import com.gwg.shiro.web.config.mvc.RootConfig;
import com.gwg.shiro.web.config.mvc.WebConfig;
import com.gwg.shiro.web.dto.UserDto;
import com.gwg.shiro.web.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void testAddUser() throws BusinessException {

        UserDto dto = new UserDto();
        dto.setUserId("13817191469");
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
        dto.setUserId("13817191469");

        userService.queryUserInfoById(dto);
    }
}
