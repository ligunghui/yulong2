package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Table(name = "sys_permission")
public class Permission {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "管理员id")
    private Integer adminId;
    private String description;//根据权限描述控制
    private Integer enVisible;
    private Integer type;
    private Integer parentId;


}