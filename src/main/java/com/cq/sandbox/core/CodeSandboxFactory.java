package com.cq.sandbox.core;

import com.cq.sandbox.model.enums.QuestionSubmitLanguageEnum;

/**
 * 根据语言类型获得对应的代码沙箱
 */
public class CodeSandboxFactory {
    public static CodeSandboxTemplate getInstance(QuestionSubmitLanguageEnum language) {
        switch (language.getValue()) {
            case "java":
                return new JavaNativeCodeSandbox();
            case "cpp":
                return new CppNativeCodeSandbox();
        }
        return null;
    }
}
