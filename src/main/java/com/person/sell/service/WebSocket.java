package com.person.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 服务端websocket 响应 客服端websocket 的相关事件操作
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    //该websocket集合是为了存放session
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    //编写对应客户端事件的方法

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】有新的连接，总数{}",webSocketSet.size());
    }

    @OnClose
    public void onClose(){
        //关闭时移除当前session
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开，总数{}",webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到客户端发来的消息:{}",message);
    }

    public void sendMessage(String message){
        for(WebSocket webSocket:webSocketSet) {
            log.info("【websocket消息】广播消息，message={}", message);
            //发送消息
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
