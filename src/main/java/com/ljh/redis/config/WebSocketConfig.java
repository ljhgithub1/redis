package com.ljh.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: Liu.jihong
 * @Date: 2020/7/31 8:35
 */
@Configuration
public class WebSocketConfig {
    /**
     * websocket配置
     */
    @Bean
    public ServerEndpointExporter getServerEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
