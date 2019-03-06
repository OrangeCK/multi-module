package com.ck.multimoduledao.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ck
 * @date 2018/12/12 13:40
 * Description  : JWT的token对象
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
