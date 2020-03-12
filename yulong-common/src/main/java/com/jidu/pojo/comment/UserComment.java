package com.jidu.pojo.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 2:36
 * @Version:
 * @Description:
 */
@Data
@Table(name = "user_comment")
public class UserComment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @ApiModelProperty(value = "(新闻 商品等id)")
    private String itemId;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户名称")
    private String userName;
    @ApiModelProperty(value = "点赞次数")
    private Integer thumbsNum;
    @ApiModelProperty(value = "用户头像")
    private String userPic;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "评论时间")
    private Date commentTime;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "指向父评论的id,如果不是对评论的回复,那么该值为null")
    private Integer parentId;
    @ApiModelProperty(value = "是不是自己的")
    @Transient
    private Integer oneself;
}
