package com.jidu.pojo;

import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.local.LocalServiceGoods;
import com.jidu.pojo.shop.ShoppingStore;
import lombok.Data;

/**
 * @Author: liguanghui
 * Date: 2020/4/4 0004 上午 10:17
 * @Version:
 * @Description:
 */
@Data
public class StoreGongGroup {
    private  Integer id;
    private ShoppingStore shoppingStore;
    private ShoppingGoods shoppingGoods;
    private LocalServiceGoods localServiceGoods;
}
