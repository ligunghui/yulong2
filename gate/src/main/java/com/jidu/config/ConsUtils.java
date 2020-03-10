package com.jidu.config;

/*常量工具类*/
public class ConsUtils {

    /*系统名称*/
    public static String OS_NAME = System.getProperty("os.name");

    public static String UPLOAD_PATH;
    /*上传文件的访问路径(相对路径)*/
//    public static String HTTP_UPLOAD_PATH = uploadPath.getHttpPath();
    /*是否linux*/
    public static boolean IS_LINUX = true;

    static {
        if (OS_NAME.startsWith("Win")){
            IS_LINUX = false;
            UPLOAD_PATH = "Win";
        }

    }

    static {
        if(IS_LINUX){
            UPLOAD_PATH = "Linux";
        }else {
            UPLOAD_PATH = "Windows";
        }
    }
}
