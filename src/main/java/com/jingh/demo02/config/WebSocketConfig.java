package com.jingh.demo02.config;

import com.jingh.demo02.handler.MyWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//通过实现webSocketConfigurer配置类，重写registerWebSocketHandlers方法，注册自定义的WebSocketHandler的实现类MyWsHandler，并
//指定类对应的websocket访问的ServerEndpoint为/myWs通过@EnableWebSocket注解，启动spring-boot-starter-websocket的自动化配置。
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyWsHandler myWsHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWsHandler,"myWebSocket").setAllowedOrigins("*");
    }
}
