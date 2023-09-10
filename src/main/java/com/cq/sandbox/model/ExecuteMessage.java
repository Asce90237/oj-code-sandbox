package com.cq.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 进程执行信息
 *
 * @author Asce
 * @since 2023/08/25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteMessage {

    /**
     * 退出码 0 - 正常退出
     */
    private Integer exitCode;

    /**
     * 正常退出终端输出信息
     */
    private String message;

    /**
     * 异常退出终端输出信息
     */
    private String errorMessage;

    /**
     * 执行时间
     */
    private long time;
}
