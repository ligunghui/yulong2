package com.jidu.chat;

import com.jidu.chat.pojo.Message;
import com.jidu.chat.pojo.OnlineUserList;
import com.jidu.chat.utils.Utils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final AttributeKey<String> USER_ID = AttributeKey.valueOf("id");
    private final AttributeKey<String> ROOM_ID = AttributeKey.valueOf("roomId");
    private final AttributeKey<String> IMG = AttributeKey.valueOf("IMG");
    private final AttributeKey<String> USER_NAME = AttributeKey.valueOf("USER_NAME");
    private final AttributeKey<String> USER_LABEL = AttributeKey.valueOf("USER_LABEL");
    @Autowired
    protected void channelRead0(ChannelHandlerContext cx, TextWebSocketFrame msg) throws Exception {
        Channel client = cx.channel();

        String text = msg.text();
        System.out.println("接收到消息数据为：" + text);

        Message message = Utils.jsonToJsonBean(text);
        if ((StringUtils.isEmpty(message.getUserId())) || (StringUtils.isEmpty(message.getRoom()))) {
            message.setContent("请登录");
            sendMessage(message, client);
        }
        //1加入房间2 聊天3送礼物
        if ("1".equals(message.getType())) {
            client.attr(this.USER_ID).getAndSet(message.getUserId());
            client.attr(this.ROOM_ID).getAndSet(message.getRoom());
            client.attr(this.IMG).getAndSet(message.getUserImg());
            client.attr(this.USER_NAME).getAndSet(message.getUserName());
            client.attr(this.USER_LABEL).getAndSet(message.getLabel());
            onlineUsers.add(client);
            message.setContent("系统提示:欢迎" + message.getUserName() + "加入房间");
        } else if ("2".equals(message.getType())) {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            String time = formatter.format(currentTime);
            message.setTime(time);
        } else if ("3".equals(message.getType())) {
            //  DisposeService disposeService = (DisposeService) SpringUtil.getBean(DisposeService.class);

            // String delGift = disposeService.delGift(message);
            message.setContent("1");
        }
        sendMessage(message, client);
    }

    private void sendMessage(Message message, Channel client) {
        List onlineUserLists = new ArrayList();
        for (Channel channel : onlineUsers) {
            if (( channel.attr(this.ROOM_ID).get()).equals(client.attr(this.ROOM_ID).get())) {
                OnlineUserList onlineUserList = new OnlineUserList();
                onlineUserList.setName( channel.attr(this.USER_NAME).get());
                onlineUserList.setImg( channel.attr(this.IMG).get());
                onlineUserList.setLabel(channel.attr(this.USER_LABEL).get());
                onlineUserLists.add(onlineUserList);
                message.setOnlineUserLists(onlineUserLists);
            }
        }
        String content = Utils.beanTojson(message);
        send(client, content);
    }

    private void send(Channel client, String content) {
        for (Channel channel : onlineUsers)
            if (( channel.attr(this.ROOM_ID).get()).equals(client.attr(this.ROOM_ID).get())) {
                channel.writeAndFlush(new TextWebSocketFrame(content));
            }
    }

    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

        Channel client = ctx.channel();
        System.out.println("异常");
        onlineUsers.remove(client);
        client.close();
    }

    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel client = ctx.channel();
        System.out.println("关闭通道");
        onlineUsers.remove(client);
        client.close();
    }

    public static void main(String[] args) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(currentTime);
        Message message = new Message();
        List lists = new ArrayList();
        OnlineUserList onlineUserList = new OnlineUserList();
        lists.add(onlineUserList);
        message.setRoom("1");
        message.setType("1");
        message.setTime(time);
        message.setUserId("1");
        message.setUserName("测试");
        message.setContent("加入房间");
        message.setGiftId("1");
        message.setOnlineUserLists(lists);
        System.out.println(Utils.beanTojson(message));
    }
}