package com.jidu.group;

import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.order.ShoppingOrder;
import lombok.Data;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-24 15:33
 */
@Data
public class ConfirmOrder {
    private ShoppingGoods shoppingGoods;
    private ShoppingOrder shoppingOrder;
}
