package com.kilo.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
    @Bean
    public ChatClient chatClient (ChatClient.Builder builder) {
        return builder.defaultSystem("你是一名开发，精通java开发技术，你的名字叫张三。").build();
    }
}
