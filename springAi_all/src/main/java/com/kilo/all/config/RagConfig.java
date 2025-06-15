package com.kilo.all.config;

import com.kilo.all.function.RecruitServiceFunction;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.List;
import java.util.function.Function;

@Configuration
public class RagConfig {
    /**
     * 创建向量数据库（rag知识数据库）
     * @param embeddingModel
     * @return
     */
    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        // 1、提取文本内容
        String filepath = "张三简历.txt";
        TextReader textReader = new TextReader(filepath);
        textReader.getCustomMetadata().put("filepath", filepath);
        List<Document> documents = textReader.get();
        // 2、文本切分段落
        TokenTextSplitter splitter = new TokenTextSplitter(1200, 350, 5, 100, true);
        splitter.apply(documents);
        // 3、添加到向量数据库
        simpleVectorStore.add(documents);
        return simpleVectorStore;
    }

    @Bean
    @Description("xx是否有面试资格")
    public Function<RecruitServiceFunction.Request, RecruitServiceFunction.Response> recruitServiceFunction() {
        return new RecruitServiceFunction();
    }

}
