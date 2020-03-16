package com.jidu.pojo.room;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/16 0016 上午 10:17
 * @Version:
 * @Description:
 */
@Data
@Table(name = "room_chat")
public class RoomChat {
    @Id
    @KeySql(useGeneratedKeys = true)
    private  Integer id;
    @ApiModelProperty(value = "群主")
    private  Integer uid;
    @ApiModelProperty(value = "名称")
    private  String name;
    @ApiModelProperty(value = "聊天室类型 1=>商务聊天室 2=>休闲聊天室")
    private Integer type;
    @ApiModelProperty(value = "1申请中2申请通过3申请不通过")
    private Integer status;
    @ApiModelProperty(value = "商会id")
    private  Integer chamberId;
}
