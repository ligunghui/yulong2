package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/26 0026 下午 6:02
 * @Version:
 * @Description:
 */

@Data
@Table(name = "user_points")
@ApiModel(value = "user积分", description = "用户积分")
public class UserPoints  {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer number;
    private String symbol;
    private String action;
    private String uid;
    private Date time;

}
