package com.ck.multimoduledao.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @date 2018/10/28 0028
 * Description : form的基础父类
 */
public class BaseForm implements Serializable {
    /**
     * 当前页，默认显示1
     */
    private int page = 1;
    /**
     * 每页显示条数，默认10条
     */
    private int rows = 10;
    /**
     * 创建时间
     */
    private Date creationDate;
    /**
     * 创建人id
     */
    private Long creationBy;
    /**
     * 更新时间
     */
    private Date updatedDate;
    /**
     * 更新人id
     */
    private Long updatedBy;
    /**
     * 是否有效表示
     */
    private boolean enableFlag = true;
    /**
     * 返回信息
     */
    private String returnMsg;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreationBy() {
        return creationBy;
    }

    public void setCreationBy(Long creationBy) {
        this.creationBy = creationBy;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @Override
    public String toString() {
        return "BaseForm{" +
                "page=" + page +
                ", rows=" + rows +
                ", creationDate=" + creationDate +
                ", creationBy=" + creationBy +
                ", updatedDate=" + updatedDate +
                ", updatedBy=" + updatedBy +
                ", enableFlag=" + enableFlag +
                ", returnMsg='" + returnMsg + '\'' +
                '}';
    }

    /**
     * 分页查询其实位置
     * @return 查询数据起始位置 start
     */
    public int getStart(){
        return (this.page - 1) * this.rows;
    }

    /**
     * 分页查询数目
     * @return 每页查询的条数 end
     */
    public int getEnd(){
        return this.rows;
    }

}
