package com.jidu.pojo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Table(name = "sys_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = -4990810027542971546L;
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

}