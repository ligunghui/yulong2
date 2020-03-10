package com.jidu.pojo.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-18 15:43
 */
@Data
@Table(name = "shopping_activity")
public class ShoppingActivity {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "副标题")
    private String subheading;
    @ApiModelProperty(value = "封面图")
    private String headPic;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    @ApiModelProperty(value = "排序")
    private Integer sordOrder;
    @ApiModelProperty(value = "是否可见1是0否")
    private Integer display;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "添加时间")
    private Date addtime;
    @ApiModelProperty(value = "添加人")
    private String addpeople;
    @ApiModelProperty(value = "1商会动态2政府资3文化旅游4本地新闻")
    private String type;
    @ApiModelProperty(value = "观看次数")
    private Integer seenum;
    @ApiModelProperty(value = "内容")
    private String context;
    @ApiModelProperty(value = "商户id")
    private String storeId;
    @ApiModelProperty(value = "商户名称")
    private String storeName;
    @ApiModelProperty(value = "评论次数")
    private Integer commentNum;
    @ApiModelProperty(value = "点赞次数")
    private Integer thumbsNum;
    @ApiModelProperty(value = "是否推到首页1是0否")
    private  Integer homePage;
}
