package com.jidu.chat.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class Message implements Serializable {
    private List<OnlineUserList> onlineUserLists;
    private String type;
    private String time;
    private String room;
    private String userId;
    private String userName;
    private String userImg;
    private String label;
    private String content;
    private String giftId;
    private String giftName;
    private String giftNumber;
    private String giftPrice;
    private String CharmValue;
    private String sendName;
    private String sendId;
    private List<String> wheat;

}