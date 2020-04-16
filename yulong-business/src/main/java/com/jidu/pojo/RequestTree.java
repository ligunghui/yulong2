package com.jidu.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/24 0024 下午 2:49
 * @Version:
 * @Description:
 */
@Data
public class RequestTree {
    private Integer id;
    private List<Integer> children;
}
