package com.kilo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class PreRoleChatController {
    @Autowired
    private ChatClient chatClient;
    @GetMapping("/chatai")
    public String chatAi (@RequestParam(value = "msg") String message) {
        String response = chatClient.prompt().user(message).call().content();
        System.out.println("response : " + response);
        return response;
    }

    /**
     * 流式响应
     * @param message
     * @return
     */
    @GetMapping(value = "/chatai/stream", produces = "text/html;charset=UTF-8")
    public Flux<String> chatAiStream (@RequestParam(value = "msg") String message) {
        return chatClient.prompt().user(message).stream().content();
    }
}
