package com.jidu.pojo.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-11 20:57
 */
@Data
@Table(name = "chamber_user")
public class ChamberUser {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户头像")
    private String userImg;
    @ApiModelProperty(value = "商会id")
    private Integer chamberId;
    @ApiModelProperty(value = "商会标签")
    private Integer chamberLabel;
    @ApiModelProperty(value = "真实姓名")
    private String trueName;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "职位")
    private String companyPost;
    @ApiModelProperty(value = "行业")
    private String industry;
    @ApiModelProperty(value = "住址")
    private String address;
    @ApiModelProperty(value = "电话")
    private String mobile;
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;
    @ApiModelProperty(value = "0待审核1审核通过2审核不通过")
    private Integer status;
    @ApiModelProperty(value = "处理人")
    private String handlerPeople;
    @ApiModelProperty(value = "处理时间")
    private Date handlerTime;
    @ApiModelProperty(value = "不通过理由")
    private String reason;
}
