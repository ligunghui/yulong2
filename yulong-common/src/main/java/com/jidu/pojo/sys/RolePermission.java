package com.jidu.pojo.sys;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: liguanghui
 * Date: 2020/4/15 0015 上午 9:59
 * @Version:
 * @Description:
 */
@Data
@Table(name = "role_permission")
public class RolePermission {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
}
