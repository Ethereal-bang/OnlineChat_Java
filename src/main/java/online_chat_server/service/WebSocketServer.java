package online_chat_server.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import online_chat_server.common.WsNews;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{uid}")
@Component
public class WebSocketServer {

    // 线程安全?Set 存放每个客户端对应的ws对象
    private static final Map<Integer, Session> electricSocketMap = new ConcurrentHashMap<>();

    // 与某客户端的连接对话
    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") int uid) {
        this.session = session;
        electricSocketMap.put(uid, session);
        System.out.println("有新连接加入。当前连接人数" + electricSocketMap.size());
    }

    @OnClose
    public void onClose(@PathParam("uid") int id) {
        electricSocketMap.remove(id);
        System.out.println("有连接断开。当前连接人数" + electricSocketMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, @PathParam("uid") int id) throws IOException {
        System.out.println("来自客户端的消息:" + message);
        // 心跳机制
        if (message.equals("ping")) {
            sendMessage(id, new WsNews("connect", "pong"));
        }
    }

    public void sendMessage(int id, WsNews news) throws IOException {
        if (electricSocketMap.containsKey(id)) {    // 如果对方在线，发送
            // 转换成JSON格式后发送
            ObjectMapper mapper = new ObjectMapper();
            String script = mapper.writeValueAsString(news);
            electricSocketMap.get(id).getBasicRemote().sendText(script);
        }
    }
}
