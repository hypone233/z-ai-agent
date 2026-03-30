package com.zzz.aiagent.rag;


import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetriever;
import com.alibaba.cloud.ai.dashscope.rag.DashScopeDocumentRetrieverOptions;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.DocumentRetriever;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoveAppRagCloudAdvisorConfig {

    @Value("${spring.ai.dashscope.api-key}")

    private String dashscopeApiKey;

    public Advisor loveAppRagCloudAdvisor(){
        DashScopeApi dashScopeApi = DashScopeApi
                .builder()
                .apiKey(dashscopeApiKey)
                .build();
        final String KNOWLEDGE_INDEX = "恋爱大师";

        DocumentRetriever retriever = new DashScopeDocumentRetriever(
                dashScopeApi,
                DashScopeDocumentRetrieverOptions.builder()
                        .indexName("KNOWLEDGE_INDEX")
                        .build()
        );
        return RetrievalAugmentationAdvisor.builder()
                .documentRetriever(retriever)
                .build();

    }

}
