package com.jidu.pojo.opportunities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Table(name = "business_opportunities")
public class BusinessOpportunities {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "副标题")
    private String subheading;
    @ApiModelProperty(value = "封面图")
    private String headPic;
    @ApiModelProperty(value = "是否推荐到首页1是0否")
    private Integer homePage;
    @ApiModelProperty(value = "排序")
    private Integer sordOrder;
    @ApiModelProperty(value = "是否可见1是0否")
    private Integer display;
    @ApiModelProperty(value = "添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "1商会动态2政府资3文化旅游4本地新闻")
    private String type;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "内容")
    private String context;


}