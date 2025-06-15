package com.kilo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RagController {
    @Autowired
    private ChatClient chatClient;

    @Autowired
    private VectorStore vectorStore;

    @GetMapping("/rag")
    public String rag(@RequestParam(value = "input", defaultValue = "java是什么") String input) {
        String content = chatClient.prompt()
                .user(input)
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .call()
                .content();
        return content;
    }
}
