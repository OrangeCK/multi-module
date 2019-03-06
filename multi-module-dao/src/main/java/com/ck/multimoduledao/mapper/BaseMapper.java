package com.ck.multimoduledao.mapper;

import com.ck.multimoduledao.entity.BaseForm;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/10/28 0028
 * Description :
 */
public interface BaseMapper<T extends BaseForm> {
    /**
     * 分页查询数据
     * @param query
     * @return
     */
    List<T> findPageData(T query);

    /**
     * 分页总数
     * @param query
     * @return
     */
    int findPageTotal(T query);

    /**
     * 根据条件查询数据
     * @param query
     * @return
     */
    List<T> findData(T query);

    /**
     * 根据id查询数据集合
     * @param id
     * @return
     */
    List<T> findDataById(Long id);

    /**
     * 通过id得到详细信息
     * @param id
     * @return
     */
    T findDetailById(Long id);

    /**
     * 新增
     * @param form
     * @return
     */
    Integer saveForm(T form);

    /**
     * 更新
     * @param form
     * @return
     */
    Integer updateForm(T form);

    /**
     * 删除
     * @param id
     * @return
     */
    Integer deleteForm(Long id);

    /**
     * 失效
     * @param form
     * @return
     */
    Integer updateToDisable(T form);

    /**
     * 重新置为有效
     * @param form
     * @return
     */
    Integer updateToEnable(T form);
}
