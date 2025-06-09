package com.kilo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
