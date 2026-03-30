// package com.zzz.aiagent.demo.invoke;

// import com.alibaba.cloud.ai.dashscope.spec.DashScopeModel;
// import jakarta.annotation.Resource;
// import org.springframework.ai.chat.messages.AssistantMessage;
// import org.springframework.ai.chat.model.ChatModel;
// import org.springframework.ai.chat.prompt.Prompt;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.ResponseStatus;

// @Component
// public class springAiInvoke {

//     @Resource
//     private ChatModel chatModel;

//     public void testChat() {
//         AssistantMessage assistantMessage = chatModel.call(new Prompt("你好，我是水分子"))
//                 .getResult()
//                 .getOutput();
//         System.out.println(assistantMessage.getText());
//     }
// }
