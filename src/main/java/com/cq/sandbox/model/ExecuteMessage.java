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

    private Integer exitCode;

    private String message;

    private String errorMessage;

    private long time;
}
