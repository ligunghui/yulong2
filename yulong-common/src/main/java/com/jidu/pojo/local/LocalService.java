package com.jidu.pojo.local;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 7:33
 * @Version:
 * @Description:
 */
@Data
@Table(name = "local_service")
public class LocalService {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "名称")
    private  String name;
    @ApiModelProperty(value = "log")
    private  String log;
}
