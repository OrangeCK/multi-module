package com.ck.multimoduleservice.service;


import com.ck.multimoduledao.entity.Employee;

/**
 * @author ck
 * @date 2018/11/7 15:13
 * Description  :
 */
public interface EmployeeService extends BaseService<Employee> {
    /**
     * 检查账号
     * @param loginName
     * @param password
     * @return
     */
    Employee loginAccount(String loginName, String password);
    /**
     * 检查账号
     * @param loginName
     * @return
     */
    Employee loginAccountByLoginName(String loginName);

    /**
     * 通过登录账号得到密码
     * @param loginName
     * @return
     */
    String getPasswordByLoginName(String loginName);
}
