package com.ljh.redis.serivce.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljh.redis.mapper.SysMenuMapper;
import com.ljh.redis.pojo.SysMenu;
import com.ljh.redis.serivce.SysMenuService;
import com.ljh.redis.util.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Liu.jihong
 * @Date: 2020/7/29 17:03
 */
@Service
public class SysMenuSerivceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public void getById1(Integer id) {
       SysMenu byId = this.getById(id);
        webSocketServer.sendMessage(JSONUtil.toJsonStr(byId));
    }
}
