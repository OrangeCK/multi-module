package com.ck.multimoduleservice.service.impl;

import com.ck.multimoduledao.entity.Permission;
import com.ck.multimoduledao.mapper.PermissionMapper;
import com.ck.multimoduleservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author ck
 * @date 2018/11/7 15:15
 * Description  :
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> findAllPermissionsById(Long id) {
        return permissionMapper.findAllPermissionsById(id);
    }
}
