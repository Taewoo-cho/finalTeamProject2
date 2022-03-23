package com.bitc.wub.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.bitc.wub.handler.SocketHandler;

// 웹 소켓을 사용하기 위한 기본 설정
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    //	미리 만들어 놓은 WebSocketHandler를 사용 등록
    @Autowired
    SocketHandler socketHandler;

    //	chating/채팅방 번호에서 웹 소켓을 사용
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/chating/{roomNumber}");
    }
}
