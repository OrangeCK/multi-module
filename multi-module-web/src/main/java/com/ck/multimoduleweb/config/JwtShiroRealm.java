package com.ck.multimoduleweb.config;

import com.alibaba.fastjson.JSON;
import com.ck.multimodulecommon.constant.LmEnum;
import com.ck.multimodulecommon.util.JwtUtil;
import com.ck.multimodulecommon.util.RedisUtil;
import com.ck.multimoduledao.entity.Employee;
import com.ck.multimoduledao.entity.JwtToken;
import com.ck.multimoduleservice.service.EmployeeService;
import com.ck.multimoduleservice.service.PermissionService;
import com.ck.multimoduleservice.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * @author ck
 * @date 2018/12/12 14:03
 * Description  :
 */
public class JwtShiroRealm extends AuthorizingRealm {
    private final static Logger logger = LoggerFactory.getLogger(JwtShiroRealm.class);
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * jwt整合shiro必须重写此方法，不然Shiro会报错，限定这个Realm只支持我们自定义的JWT Token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 权限验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("权限验证");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String loginName = JwtUtil.getLoginName(principalCollection.toString(), LmEnum.LOGIN_NAME.getName());
        if(loginName != null){
            boolean employeeExist = false;
            employeeExist = redisUtil.hHasKey(principalCollection.toString(), LmEnum.USER_INFO.getName());
            Employee employee = null;
            if(!employeeExist){
                // 与数据库数据进行匹配校验
                employee = employeeService.loginAccountByLoginName(loginName);
            }else{
                employee = JSON.parseObject(redisUtil.hget(principalCollection.toString(), LmEnum.USER_INFO.getName()).toString(), Employee.class);
            }
            try {
                // 查看redis中是否存在，否则从新数据库查询再保存到redis中
                boolean permSetExist = false;
                permSetExist = redisUtil.hHasKey(principalCollection.toString(), LmEnum.PERMISSIONS.getName());
                Set<String> permissionSet = null;
                if(!permSetExist){
                    permissionSet = permissionService.findAllPermissionsById(employee.getId());
                    Iterator<String> it = permissionSet.iterator();
                    StringBuilder sb = new StringBuilder();
                    while (it.hasNext()){
                        sb.append(it.next() + ",");
                    }
                    String permArray = sb.toString().substring(0, sb.toString().length()-1);
                    redisUtil.hset(principalCollection.toString(), LmEnum.PERMISSIONS.getName(), permArray, LmEnum.LOGIN_INFO_EXPIRE.getNum());
                }else{
                    String[] permArray = redisUtil.hget(principalCollection.toString(), LmEnum.PERMISSIONS.getName()).toString().split(",");
                    permissionSet = new HashSet<>(Arrays.asList(permArray));
                }
                // 注入权限
                authorizationInfo.setStringPermissions(permissionSet);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                logger.error("权限配置错误" + sw.toString());
            }
            logger.info("用户" + employee.getUserName() + "的权限:{}", authorizationInfo.getStringPermissions());
            return authorizationInfo;
        }
        return null;
    }

    /**
     * 登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("用户验证");
        String token = (String) authenticationToken.getCredentials();
        // 获取用户名和密码
        String loginName = JwtUtil.getLoginName(token, LmEnum.LOGIN_NAME.getName());
        boolean loginNameExist = false;
        loginNameExist = redisUtil.hHasKey(token, LmEnum.USER_INFO.getName());
        // redis校验是否存在这个key
        if(!loginNameExist){
            // 与数据库数据进行匹配校验
            Employee employee = employeeService.loginAccountByLoginName(loginName);
            if(null == employee){
                throw new UnknownAccountException("该用户不存在");
            }else{
                redisUtil.hset(token, LmEnum.USER_INFO.getName(), JSON.toJSONString(employee), LmEnum.LOGIN_INFO_EXPIRE.getNum());
            }
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
