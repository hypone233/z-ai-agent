package com.zzz.aiagent.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalOperationTool {

    /**
     * 执行终端命令并返回执行结果
     * @param command 待执行的终端命令字符串
     * @return 命令执行的输出内容（含成功输出或错误信息）
     */
    @Tool(description = "Execute a command in the terminal")
    public String executeTerminalCommand(
            @ToolParam(description = "Command to execute in the terminal") String command) {
        StringBuilder output = new StringBuilder();
        try {
            // 执行命令并获取进程对象
            ProcessBuilder builder = new ProcessBuilder("com.exe","/c",command);
            Process process = builder.start();
            
            // 读取命令执行的标准输出流
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }
            
            // 等待命令执行完成并获取退出码
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                output.append("Command execution failed with exit code: ").append(exitCode);
            }
        } catch (IOException | InterruptedException e) {
            // 捕获IO异常或线程中断异常
            output.append("Error executing command: ").append(e.getMessage());
        }
        return output.toString();
    }
}