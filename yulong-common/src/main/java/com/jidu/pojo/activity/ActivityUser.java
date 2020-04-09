package com.jidu.pojo.activity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liguanghui
 * Date: 2020/3/13 0013 下午 3:14
 * @Version:
 * @Description:
 */
@Data
@Table(name = "activity_user")
public class ActivityUser {
    @Id
    @KeySql(useGeneratedKeys = true)
    private  Integer id;
    private  Integer activityId;
    private  String userId;
}
