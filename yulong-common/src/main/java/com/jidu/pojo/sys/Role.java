package com.jidu.pojo.sys;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 594829320797158219L;

    private String id;
    /**
     * 角色名
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 企业id
     */
    private String companyId;




}