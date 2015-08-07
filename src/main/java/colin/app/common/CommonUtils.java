package colin.app.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by joker on 14-9-13.
 */
public class CommonUtils {
    /**
     * 判断字符串是否是空的
     */
    public static boolean handleEmptyStr(Object obj) {
        return obj != null && !obj.toString().trim().equals("");
    }

    /**
     * 初始化参数类
     */
    public static Map<String, Object> initParamsMap() {
        return new HashMap<String, Object>();
    }

    /**
     * 计算当前的文件大小，单位是kb
     */
    public static float fetchTheFileSize(float fileLength) {
        float result = 0;
        result = fileLength / 1024l;
        if (result <= 1024) {
            result = 1l;
        } else {
            result = fileLength / (1024l * 1024l);
        }

        return Math.round(result);
    }

    /**
     * 检测文件的类型，确定是有效的图片类型
     */
    public static boolean testFileType(String imageType) {
        //TODO 欠缺正则的表达式，来匹配文件是否是有效的图片格式，jpg/jpeg/gif/
        boolean result = true;
        String math = "jpg|";
        return result;
    }


    /**
     * 返回一个UUID主键
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 测试内部工具方法
     * @param args
     */
    public static void main(String[] args) {
       // System.out.println(CommonUtils.fetchTheFileSize(12455503));
        for(int i=0;i<20;i++){
            System.out.println(CommonUtils.generateUUID());
        }

    }
}
