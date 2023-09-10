package com.cq.sandbox.core;

import com.cq.sandbox.model.CodeSandboxCmd;
import lombok.extern.slf4j.Slf4j;

import java.io.File;


/**
 * java本机代码沙箱
 *
 * @author Asce
 * @since 2023/08/21
 */
@Slf4j
public class JavaNativeCodeSandbox extends CodeSandboxTemplate {

    // File.separator 在UNIX系统下 分隔符为/ WINDOWS是\\
    private static final String PREFIX = File.separator + "java";

    private static final String GLOBAL_CODE_DIR_PATH = File.separator + "tempCode";

    private static final String GLOBAL_JAVA_CLASS_NAME = File.separator + "Main.java";

    public JavaNativeCodeSandbox() {
        super.prefix = PREFIX;
        super.globalCodeDirPath = GLOBAL_CODE_DIR_PATH;
        super.globalCodeFileName = GLOBAL_JAVA_CLASS_NAME;
    }

    @Override
    public CodeSandboxCmd getCmd(String userCodeParentPath, String userCodePath) {
        return CodeSandboxCmd
                .builder()
                .compileCmd(String.format("/usr/local/jdk1.8.0_341/bin/javac -encoding utf-8 %s", userCodePath))
                .runCmd(String.format("/usr/local/jdk1.8.0_341/bin/java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main", userCodeParentPath))
                .build();
    }
}
