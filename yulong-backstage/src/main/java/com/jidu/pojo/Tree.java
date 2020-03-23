package com.jidu.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 下午 6:30
 * @Version:
 * @Description:
 */
@Data
public class Tree {
    private String title;
    private int id;
    private String field;
    private boolean checked;
    private boolean spread=false;
    List<Tree> children=new ArrayList<>();

    public Tree(String title, int id, boolean checked) {
        this.title = title;
        this.id = id;
        this.checked=checked;
    }
}
