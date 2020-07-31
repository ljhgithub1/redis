package com.ljh.redis.serivce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljh.redis.pojo.SysMenu;

/**
 * @Author: Liu.jihong
 * @Date: 2020/7/29 17:00
 */
public interface SysMenuService extends IService< SysMenu> {
    void getById1(Integer id);
}
