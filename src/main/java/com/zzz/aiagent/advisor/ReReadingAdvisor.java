package com.zzz.aiagent.advisor;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class ReReadingAdvisor implements CallAdvisor, StreamAdvisor {

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest request, CallAdvisorChain chain) {
        // 替换成 Re2 提示词
        ChatClientRequest newRequest = rewriteRequest(request);
        return chain.nextCall(newRequest);
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest request, StreamAdvisorChain chain) {
        ChatClientRequest newRequest = rewriteRequest(request);
        return chain.nextStream(newRequest);
    }

    // ====================== 核心：重写提示词 ======================
    private ChatClientRequest rewriteRequest(ChatClientRequest request) {

        Prompt newPrompt = request.prompt()
                .augmentUserMessage(userMessage -> {

                    String originalText = userMessage.getText();

                    String re2Prompt =
                            originalText + "\nRead the question again: " + originalText;

                    return userMessage.mutate()
                            .text(re2Prompt)
                            .build();
                });

        return request.mutate()
                .prompt(newPrompt)
                .build();
    }
    @Override
    public String getName() {
        return "ReReadingAdvisor";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}