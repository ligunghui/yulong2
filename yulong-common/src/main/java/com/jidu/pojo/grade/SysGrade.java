package com.jidu.pojo.grade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: SysGrade
 * @Author: Administrator
 * Date: 2020/3/2 0002 下午 4:11
 * @Version:
 * @Description:
 */

@Data
@Table(name = "sys_grade")
public class SysGrade {

    @Id
    @KeySql(useGeneratedKeys = true)
    private  Integer id;
    @ApiModelProperty(value = "名称")
    private  String name;
    @ApiModelProperty(value = "所需积分")
    private  String integral;
    @ApiModelProperty(value = "图标")
    private  String img;
    @ApiModelProperty(value = "说明")
    private  String explain;
}
