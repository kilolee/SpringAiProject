package com.kilo.function.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private ChatModel chatModel;

    @GetMapping(value = "/function", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String function01 (@RequestParam("userMessage") String userMessage) {
        return ChatClient.builder(chatModel).build().prompt().system("必须从用户处获取如下信息：两个数字，运算类型。调用自定义函数执行加法运算、乘法运算").
                user(userMessage).toolNames("addOperation", "mulOperation").call().content();
    }
}
