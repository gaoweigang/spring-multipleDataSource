package com.gwg.shiro.web.service;

import com.gwg.shiro.web.dto.RoleDto;
import com.gwg.shiro.web.exception.BusinessException;
import com.gwg.shiro.web.model.Role;

public interface RoleService {

    /**
     * 新增角色
     */
    public boolean addRole(RoleDto dto) throws BusinessException;

    /**
     * 根据ID查询角色
     * @param dto
     * @return
     * @throws BusinessException
     */
    public Role queryRoleById(RoleDto dto) throws BusinessException;

}
