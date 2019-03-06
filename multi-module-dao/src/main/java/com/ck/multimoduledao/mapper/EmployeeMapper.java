package com.ck.multimoduledao.mapper;

import com.ck.multimoduledao.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author ck
 * @date 2018/11/7 15:20
 * Description  :
 */
@Mapper
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 检查账号
     * @param loginName
     * @param password
     * @return
     */
    Employee loginAccount(@Param("loginName") String loginName, @Param("password") String password);
    /**
     * 检查账号
     * @param loginName
     * @return
     */
    Employee loginAccountByLoginName(@Param("loginName") String loginName);
    /**
     * 通过登录账号得到密码
     * @param loginName
     * @return
     */
    String getPasswordByLoginName(String loginName);

    /**
     * 校验登陆名是否存在
     * @param employee
     * @return
     */
    Integer countCheckLoginName(Employee employee);

}
