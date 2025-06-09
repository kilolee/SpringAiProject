package com.kilo.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeepSeekController {
    @Autowired
    private OpenAiChatModel chatModel;

    @GetMapping("/hello")
    public String generate(@RequestParam(value = "message", defaultValue = "hello")
                           String message) {
        String response = this.chatModel.call(message);
        System.out.println("response : " + response);
        return response;
    }
}
