package com.cq.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeSandboxCmd {

    /**
     * 编译
     */
    private String compileCmd;

    /**
     * 运行
     */
    private String runCmd;
}
