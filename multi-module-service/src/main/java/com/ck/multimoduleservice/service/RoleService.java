package com.ck.multimoduleservice.service;


import com.ck.multimoduledao.entity.Role;

import java.util.Set;

/**
 * @author ck
 * @date 2018/11/7 15:16
 * Description  :
 */
public interface RoleService extends BaseService<Role>{
    /**
     * 增加用户与角色之前的关系
     * @param role 角色对象
     * @return
     */
    Integer addUserAndRoleRelation(Role role);

    /**
     * 删除用户与角色之间的关系
     * @param userId 用户id
     * @return
     */
    Integer delUserAndRoleRelation(Long userId);

    /**
     * 得到用户所有的角色set集合
     * @param userId
     * @return
     */
    Set<String> findAllRolesById(Long userId);
}
