package com.jidu.pojo.sys;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;


@Data
@Table(name = "sys_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = -4990810027542971546L;
    private Integer id;
    private String name;
    private Integer adminId;
    private String description;//根据权限描述控制
    private Integer enVisible;
    private Integer type;

}