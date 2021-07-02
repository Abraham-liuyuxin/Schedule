package com.hust.soft.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;


@RestController
@RequestMapping("/test/sse")
public class SseEmitterController {
    private static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();

    @RequestMapping("/notice")
    public SseEmitter push(@RequestParam String id) {
        // 超时时间设置为1小时
        SseEmitter sseEmitter = new SseEmitter(3600_000L);
        sseCache.put(id, sseEmitter);
        return sseEmitter;
    }

    public static void usesSsePush(String id, String content) {
        SseEmitter emitter = SseEmitterController.sseCache.get(id);
        if (Objects.nonNull(emitter)) {
            try {
                emitter.send(content);
            } catch (IOException | IllegalStateException e) {
                e.printStackTrace();
                SseEmitterController.sseCache.remove(id);
            }
        }
    }
}
