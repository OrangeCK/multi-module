package com.ck.multimoduledao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/10/28 0028
 * Description : 分页返回类
 */
public class PageList<T extends BaseForm> implements Serializable {
    /**
     * 分页总数
     */
    private int total = 0;
    /**
     * 分页数据的集合
     */
    private List<T> rows = new ArrayList<>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public PageList(){

    }

    public PageList(int total, List<T> rows){
        this.total = total;
        this.rows = rows;
    }
}
