package com.example.teamproject.Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // WebSocket 핸들러를 주입받아서 사용하는 생성자
    private final WebSocketHandler webSocketHandler;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // enableSimpleBroker: 내장 메시지 브로커를 사용하기 위한 메소드
        // 파라미터로 지정한 prefix가 붙은 메시지를 발행할 경우, 메시지 브로커가 이를 처리하게 된다.
        // 메시지를 구독하는 요청 url => 즉 메시지 받을 때
        registry.enableSimpleBroker("/topic"); // 해당 채팅방의 클라이언트에게 메시지 전달

        // 메세지 핸들러로 라우팅 되는 prefix를 파라미터로 지정할 수 있다.
        // 메시지 가공 처리가 필요한 경우, 가공 핸들러로 메시지를 라우팅 되도록하는 설정
        // 메시지를 발행하는 요청 url => 즉 메시지 보낼 때
        registry.setApplicationDestinationPrefixes("/app"); // 클라이언트에서 보낸 메시지를 받을 prefix

    }

    // WebSocket 핸들러를 등록하는 메소드
    /*
     * HTTP method      URL                 EndPoint
     *  POST            /chat               채팅방 생성
     *  GET             /chat               채팅방 조회
     *  GET             /chat/1             1번 채팅방 조회
     *  PUT             /chat/1             1번 채팅방 수정
     * DELETE           /chat/1             1번 채팅방 삭제
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        try{
            registry.addEndpoint("/connection")
                    .setAllowedOriginPatterns("*");
                    //.withSockJS(); // SockJS를 사용하는 경우에만 주석 해제
        } catch (Exception e){
            log.error("Error during WebSocket handshake:", e);
        }
    }
}
