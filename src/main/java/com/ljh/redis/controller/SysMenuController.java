package com.ljh.redis.controller;

import com.ljh.redis.serivce.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Liu.jihong
 * @Date: 2020/7/29 17:00
 */
@RestController
@RequestMapping("/sys")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;
    @GetMapping("/{id}")
    public void getById(@PathVariable("id") Integer id){
        sysMenuService.getById1(id);
    }
}
