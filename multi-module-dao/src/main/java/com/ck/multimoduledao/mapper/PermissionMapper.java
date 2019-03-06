package com.ck.multimoduledao.mapper;

import com.ck.multimoduledao.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author ck
 * @date 2018/11/7 15:21
 * Description  :
 */
@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据id得到所有的权限
     * @param id 用户id
     * @return
     */
    Set<String> findAllPermissionsById(Long id);
}
