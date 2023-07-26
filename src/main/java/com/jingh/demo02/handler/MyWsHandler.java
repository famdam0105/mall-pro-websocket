package com.jingh.demo02.handler;

import com.jingh.demo02.work.WsSessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class MyWsHandler extends AbstractWebSocketHandler {
    /**
     * ws消息处理类
     */
//    private static Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("建立ws连接");
        WsSessionManager.add(session.getId(),session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("发送文本消息");
        // 获得客户端传来的消息
        String payload = message.getPayload();

        //如果是json数组，




        log.info("server 接收到消息 " + payload);
        //相当于服务端发送小心给客户端
        //session.sendMessage(new TextMessage("server 发送给的消息 " + payload + "，发送时间:" + LocalDateTime.now().toString()));
        //me add
        //某一台客服端发送，其他客服端可以接受，尝试----可以实现
        for(WebSocketSession session01 : WsSessionManager.SESSION_POOL.values()){
            if(session01!=session){
                session01.sendMessage(new TextMessage(payload));
            }
        }

    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {

        log.info("发送二进制消息");
        byte[] array = message.getPayload().array();
        System.out.println(array.toString());

        for(WebSocketSession session2:WsSessionManager.SESSION_POOL.values()){
            if(session!=session2){
                session2.sendMessage(new BinaryMessage(array));
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("异常处理");
        WsSessionManager.removeAndClose(session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("关闭ws连接");
        WsSessionManager.remove(session.getId());
    }


}
