package com.kilo.controller;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.DefaultChatOptionsBuilder;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatModelController {
    @Autowired
    private ChatModel chatModel;

    @GetMapping("/chatModel01")
    public String chatModel01 (@RequestParam("msg") String msg) {
        String result = chatModel.call(msg);
        return result;
    }
    @GetMapping("/chatModel02")
    public String chatModel02 (@RequestParam("msg") String msg) {
        ChatResponse chatResponse = chatModel.call(
                new Prompt(msg)
        );
        String text = chatResponse.getResult().getOutput().getText();
        return text;
    }
}
