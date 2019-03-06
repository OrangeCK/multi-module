package com.ck.multimoduleservice.service.impl;

import com.ck.multimodulecommon.util.RedisUtil;
import com.ck.multimoduledao.entity.BaseForm;
import com.ck.multimoduledao.entity.PageList;
import com.ck.multimoduledao.mapper.BaseMapper;
import com.ck.multimoduleservice.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/10/28 0028
 * Description :
 */
@Transactional(rollbackFor=Exception.class)
public class BaseServiceImpl<T extends BaseForm> implements BaseService<T> {
    @Autowired
    private BaseMapper<T> baseMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public PageList<T> getPageList(T query) {
        int total = baseMapper.findPageTotal(query);
        List<T> list = baseMapper.findPageData(query);
        return new PageList(total, list);
    }

    @Override
    public List<T> findData(T query) {
        return baseMapper.findData(query);
    }

    @Override
    public List<T> findDataById(Long id) {
        return baseMapper.findDataById(id);
    }

    @Override
    public T findDetailById(Long id) {
        return baseMapper.findDetailById(id);
    }

    @Override
    public T saveForm(T form) {
        this.basicForm(form);
        int count = baseMapper.saveForm(form);
        if(count < 1){
            form.setEnableFlag(false);
        }
        return form;
    }

    @Override
    public T updateForm(T form) {
        this.basicForm(form);
        int count = baseMapper.updateForm(form);
        if(count < 1){
            form.setEnableFlag(false);
        }
        return form;
    }

    @Override
    public Integer deleteForm(Long id) {
        int count = baseMapper.deleteForm(id);
        return count;
    }

    @Override
    public Integer updateToDisable(T form) {
        this.basicForm(form);
        int count = baseMapper.updateToDisable(form);
        return count;
    }

    @Override
    public Integer updateToEnable(T form) {
        return null;
    }

    public void basicForm(T form){
        Date date=new Date();
        form.setCreationDate(date);
        form.setUpdatedDate(date);
    }
}
