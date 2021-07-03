package com.hust.soft.controller;


import com.hust.soft.service.SseEmitterServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;


@RestController
@RequestMapping("/test/sse")
public class SseEmitterController {

    /**
     * 用于创建连接
     */
    @GetMapping("/connect/{userId}")
    public SseEmitter connect(@PathVariable String userId) {
        return SseEmitterServer.connect(userId);
    }

    @GetMapping("/close/{userId}")
    public void close(@PathVariable String userId) {
        SseEmitterServer.removeUser(userId);
    }

    @GetMapping("/list")
    public ResponseEntity<List<String>> list() {
        return ResponseEntity.ok(SseEmitterServer.getIds());
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getUserCount() {
        return ResponseEntity.ok(SseEmitterServer.getUserCount());
    }

    @GetMapping("/push/{message}")
    public ResponseEntity<String> push(@PathVariable(name = "message") String message) {
        SseEmitterServer.batchSendMessage(message);
        return ResponseEntity.ok("WebSocket 推送消息给所有人");
    }

    @GetMapping("/pushTag/{userId}/{message}")
    public ResponseEntity<String> pushTag(@PathVariable(name = "userId") String userId,
                                          @PathVariable(name = "message") String message) {
        SseEmitterServer.sendMessage(userId, message);
        return ResponseEntity.ok("WebSocket 推送消息给：" + userId);
    }


}