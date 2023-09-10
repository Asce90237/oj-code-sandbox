package com.cq.sandbox.utils;

import cn.hutool.core.util.StrUtil;
import com.cq.sandbox.model.ExecuteMessage;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * 进程处理工具类
 *
 * @author Asce
 * @since 2023/08/25
 */
@Slf4j
public class ProcessUtil {

    /**
     * 执行进程获取终端信息
     *
     * @param runProcess    运行进程
     * @param operationName 操作名称
     * @return {@link ExecuteMessage}
     */
    public static ExecuteMessage handleProcessMessage(Process runProcess, String operationName) {
        int exitCode;
        StringBuilder output = new StringBuilder();
        StringBuilder errorOutput = new StringBuilder();
        try {
            // 等待执行，获取退出码 0 - 正常退出
            exitCode = runProcess.waitFor();
            if (exitCode == 0) {
                log.info(operationName + "成功");
            } else {
                log.error(operationName + "失败，错误码为: {}", exitCode);
                // 成块分批逐行读取终端的输出
                BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                String errorRunOutputLine;
                while ((errorRunOutputLine = errorBufferedReader.readLine()) != null) {
                    errorOutput.append(errorRunOutputLine);
                }
                log.error("错误输出为：{}", errorOutput);
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            String runOutputLine;
            while ((runOutputLine = bufferedReader.readLine()) != null) {
                output.append(runOutputLine);
            }
            if (StrUtil.isNotBlank(output)) {
                log.info("正常输出：{}", output);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ExecuteMessage.builder()
                .exitCode(exitCode)
                .message(output.toString())
                .errorMessage(errorOutput.toString())
                .build();
    }


    /**
     * 给进程喂输入用例
     *
     * @param runProcess    运行进程
     * @param input         输入数据
     * @param operationName 操作名称
     * @return
     */
    public static ExecuteMessage handleProcessInteraction(Process runProcess, String input, String operationName) {
        OutputStream outputStream = runProcess.getOutputStream();
        try {
            // 给终端喂输入用例
            outputStream.write((input + "\n").getBytes());
            // flush相当于按回车
            outputStream.flush();
            outputStream.close();
            // 返回终端输出结果
            return handleProcessMessage(runProcess, operationName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error("关闭输入流失败");
            }
        }
    }

}
