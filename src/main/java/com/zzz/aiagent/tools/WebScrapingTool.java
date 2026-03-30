package com.zzz.aiagent.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

public class WebScrapingTool {

    /**
     * 抓取指定 URL 网页的完整 HTML 内容
     * @param url 待抓取网页的 URL 地址
     * @return 网页的完整 HTML 字符串；抓取失败则返回错误信息
     */
    @Tool(description = "Scrape the content of a web page")
    public String scrapeWebPage(
            @ToolParam(description = "URL of the web page to scrape") String url
    ) {
        try {
            // 连接目标 URL 并获取网页 Document 对象
            Document document = Jsoup.connect(url).get();
            // 返回完整的 HTML 内容
            return document.html();
        } catch (Exception e) {
            // 异常捕获，返回错误详情
            return "Error scraping web page: " + e.getMessage();
        }
    }
}