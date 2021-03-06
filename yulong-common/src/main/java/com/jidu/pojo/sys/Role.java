package com.jidu.pojo.sys;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Table(name = "sys_role")
public class Role  {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;

    private String description;





}