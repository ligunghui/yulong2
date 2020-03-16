package com.jidu.pojo.local;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liguanghui
 * Date: 2020/3/15 0015 下午 11:27
 * @Version:
 * @Description:
 */
@Data
@Table(name = "local_service_store")
public class LocalServiceStore {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "名称")
    private  String name;
    @ApiModelProperty(value = "img")
    private  String img;
    @ApiModelProperty(value = "服务id")
    private  String serviceId;
    @ApiModelProperty(value = "introduce")
    private  String introduce;
    @ApiModelProperty(value = "地址")
    private  String address;
}
