package com.gwg.shiro.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.gwg.shiro.web.dao.RoleDao;
import com.gwg.shiro.web.dto.RoleDto;
import com.gwg.shiro.web.exception.BusinessException;
import com.gwg.shiro.web.model.Role;
import com.gwg.shiro.web.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleDao roleDao;



    public boolean addRole(RoleDto dto) throws BusinessException {

        return roleDao.addRole(dto);
    }

    public boolean updateRoleById(RoleDto dto) throws BusinessException{

        return roleDao.updateRoleById(dto);
    }

    public PageInfo<Role> queryRoleByLimit(RoleDto dto) throws BusinessException{
        return roleDao.queryRoleByLimit(dto);
    }

    public boolean delRoleById(RoleDto dto) throws BusinessException{

        return roleDao.delRoleById(dto);
    }

    /**
     * 根据条件查询用户角色
     */
    public Role queryRoleById(RoleDto dto) throws BusinessException{
        return roleDao.queryRoleById(dto);
    }

}
