package com.ck.multimoduledao.entity;

/**
 * @author ck
 * @date 2018/11/7 15:08
 * Description  : 权限类
 */
public class Permission extends BaseForm{
    /**
     *  权限id
     */
    private Long id;
    /**
     * 权限编码
     */
    private String permCode;
    /**
     * 权限描述
     */
    private String permDesc;
    /**
     * 权限路径
     */
    private String resources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermCode() {
        return permCode;
    }

    public void setPermCode(String permCode) {
        this.permCode = permCode;
    }

    public String getPermDesc() {
        return permDesc;
    }

    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }
}
