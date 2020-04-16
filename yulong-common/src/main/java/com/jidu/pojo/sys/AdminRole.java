package com.jidu.pojo.sys;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liguanghui
 * Date: 2020/4/15 0015 下午 2:00
 * @Version:
 * @Description:
 */
@Data
@Table(name = "admin_role")
public class AdminRole {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer adminId;
    private Integer roleId;
}
