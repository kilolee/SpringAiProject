package com.kilo.other;

import com.alibaba.cloud.ai.dashscope.api.DashScopeImageApi;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageModel;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

@RestController
public class OtherController {

    @Autowired
    private DashScopeImageModel imageModel;

    @GetMapping("/image")
    public void getImage (@RequestParam(value = "msg", defaultValue = "生成一只小猫") String msg,
                          HttpServletResponse response) {
        DashScopeImageOptions imageOptions = DashScopeImageOptions.builder()
                .withModel(DashScopeImageApi.DEFAULT_IMAGE_MODEL)
                .withN(1)
                .withHeight(1024)
                .withWidth(1024)
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(msg, imageOptions);
        ImageResponse imageResponse = imageModel.call(imagePrompt);
        //获取生成图🤔的地址
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        //在浏览器中输出
        try {
            URL url = URI.create(imageUrl).toURL();
            InputStream in = url.openStream();
            response.setHeader("Content-Type", MediaType.IMAGE_PNG_VALUE);
            response.getOutputStream().write(in.readAllBytes());
            response.getOutputStream().flush();
        } catch (Exception e) {
        }
    }
}
