package com.zzz.aiagent.tools;

import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolRegistration {

    // 注入配置文件中的 search-api 密钥
    @Value("${search-api.api-key}")
    private String searchApiKey;

    /**
     * 注册所有工具类为 Spring Bean
     * 并统一包装为 ToolCallback 数组
     */
    @Bean
    public ToolCallback[] allTools() {
        // 初始化各工具实例
        FileOperationTool fileOperationTool = new FileOperationTool();
        WebSearchTool webSearchTool = new WebSearchTool(searchApiKey);
        WebScrapingTool webScrapingTool = new WebScrapingTool();
        ResourceDownloadTool resourceDownloadTool = new ResourceDownloadTool();
        TerminalOperationTool terminalOperationTool = new TerminalOperationTool();


        // 转换为 ToolCallback 数组并返回
        return ToolCallbacks.from(
                fileOperationTool,
                webSearchTool,
                webScrapingTool,
                resourceDownloadTool,
                terminalOperationTool

        );
    }
}