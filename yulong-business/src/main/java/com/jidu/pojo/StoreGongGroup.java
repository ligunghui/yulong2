package com.jidu.pojo;

import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.local.LocalServiceGoods;
import lombok.Data;


/**
 * @Author: liguanghui
 * Date: 2020/4/4 0004 上午 11:25
 * @Version:
 * @Description:
 */
@Data
public class StoreGongGroup {
    private ShoppingGoods shoppingGoods;
    private LocalServiceGoods localServiceGoods;
    private Integer id;
}
