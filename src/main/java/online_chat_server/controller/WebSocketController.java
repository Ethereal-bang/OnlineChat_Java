package online_chat_server.controller;

import online_chat_server.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    private WebSocketServer webSocketServer;

    @Autowired
    public void setWebSocketServer(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @GetMapping("/{uid}")
    public void webSocket(@PathVariable("uid") int uid) throws IOException {
        webSocketServer.sendMessage("test", uid);
    }
}
