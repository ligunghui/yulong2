package com.jidu.aop;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "sys_log")
public class Log {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String operateor;

    private String operatetype;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatedate;

    private String operateresult;
    private String ip;
}