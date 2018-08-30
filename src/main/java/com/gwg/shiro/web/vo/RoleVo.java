package com.gwg.shiro.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

public class RoleVo implements Serializable{

    /**
     * 主键
     */
    private Long id;

    private String roleCode;

    private String roleName;

    /**
     * 该角色拥有的资源集合
     */
    private List<Long> resourceIdList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Long> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Long> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
