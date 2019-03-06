package com.ck.multimoduleservice.service;


import com.ck.multimoduledao.entity.Permission;

import java.util.Set;

/**
 * @author ck
 * @date 2018/11/7 15:13
 * Description  :
 */
public interface PermissionService extends BaseService<Permission>{
    /**
     * 根据id得到所有的权限
     * @param id
     * @return
     */
    Set<String> findAllPermissionsById(Long id);
}
