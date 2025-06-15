package com.kilo.other;

import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisModel;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeSpeechSynthesisOptions;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisPrompt;
import com.alibaba.cloud.ai.dashscope.audio.synthesis.SpeechSynthesisResponse;
import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@RestController
public class AudioModelController {
    @Autowired
    private DashScopeSpeechSynthesisModel speechSynthesisModel;

    private static final String TEXT = "床前明月光， 疑是地上霜。 举头望明月， 低头思故乡。";
    private static final String FILE_PATH = "springAi_other/src/main/resources/tts";

    @GetMapping("/tts")
    public void tts(@RequestParam(value = "msg", defaultValue = "请输入文本") String msg) {
        DashScopeSpeechSynthesisOptions options = DashScopeSpeechSynthesisOptions.builder()
                .withSpeed(1.0)
                .withPitch(0.9)
                .withVolume(60)
                .build();
        SpeechSynthesisPrompt prompt = new SpeechSynthesisPrompt(msg, options);
        SpeechSynthesisResponse response = speechSynthesisModel.call(prompt);
        File file = new File(FILE_PATH + "/output.mp3");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteBuffer byteBuffer = response.getResult().getOutput().getAudio();
            fos.write(byteBuffer.array());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
