package com.jidu.chat.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-19 14:46
 */
@Data
public class OnlineUserList implements Serializable {
    private String name;
    private String img;
    private String label;
}
