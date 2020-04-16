package com.jidu.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/25 0025 上午 10:00
 * @Version:
 * @Description:
 */
@Data
public class RequestManualAddData {
    private String type;
    private String title;
    private List<AuthorsModel> authors;

}
