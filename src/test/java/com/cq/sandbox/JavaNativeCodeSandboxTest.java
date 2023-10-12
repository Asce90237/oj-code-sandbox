package com.cq.sandbox;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.cq.sandbox.core.CodeSandboxTemplate;
import com.cq.sandbox.core.CppNativeCodeSandbox;
import com.cq.sandbox.core.JavaNativeCodeSandbox;
import com.cq.sandbox.model.ExecuteCodeRequest;
import com.cq.sandbox.model.ExecuteCodeResponse;
import com.cq.sandbox.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class JavaNativeCodeSandboxTest {

    @Test
    void executeCode() {
        CodeSandboxTemplate codeSandbox = new JavaNativeCodeSandbox();
//        CodeSandboxTemplate codeSandbox = new CppNativeCodeSandbox();
        String code = ResourceUtil.readStr("testcode/Main.java", StandardCharsets.UTF_8);
        QuestionSubmitLanguageEnum languageType = QuestionSubmitLanguageEnum.getEnumByValue("java");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest
                .builder()
                .inputList(Arrays.asList("1 2", "3 5"))
                .code(code)
                .language(languageType)
                .build();
        String s = JSONUtil.toJsonStr(executeCodeRequest);
        System.out.println(s);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        System.out.println(JSONUtil.toJsonStr(executeCodeResponse));
    }
}
