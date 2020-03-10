package com.jidu.chat.utils;

import com.alibaba.fastjson.JSON;
import com.jidu.chat.pojo.Message;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-19 14:49
 */
public class Utils {
    public static Message jsonToJsonBean(String text) {
        Message message = (Message) JSON.parseObject(text, Message.class);
        return message;
    }

    public static String beanTojson(Message message) {
        String s = JSON.toJSONString(message);
        return s;
    }
}
