package com.cq.sandbox.core;

import com.cq.sandbox.model.enums.QuestionSubmitLanguageEnum;

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
