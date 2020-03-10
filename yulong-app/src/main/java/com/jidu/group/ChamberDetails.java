package com.jidu.group;

import com.jidu.pojo.shop.ChamberUser;
import com.jidu.pojo.shop.ShoppingBanner;
import com.jidu.pojo.shop.ShoppingChamber;
import lombok.Data;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-14 13:21
 */
@Data
public class ChamberDetails {
    //商会
   private ShoppingChamber shoppingChamber ;
    //轮播图
    private List<ShoppingBanner> shoppingBannerList;
    //商会成员
    private List<ChamberUser> chamberUserList;
}
