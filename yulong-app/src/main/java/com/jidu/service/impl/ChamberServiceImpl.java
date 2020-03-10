package com.jidu.service.impl;

import com.jidu.client.BusinessClient;
import com.jidu.client.ChamberClient;
import com.jidu.group.ChamberDetails;
import com.jidu.mapper.ShoppingBannerMapper;
import com.jidu.pojo.shop.ChamberUser;
import com.jidu.pojo.shop.ShoppingBanner;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.service.ChamberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-14 13:15
 */
@Service
public class ChamberServiceImpl implements ChamberService {
    @Autowired
    private ChamberClient chamberClient;
    @Autowired
    private BusinessClient businessClient;
    @Autowired
    private ShoppingBannerMapper shoppingBannerMapper;

    @Override
    public ChamberDetails findChamberDetails(Integer chamberId) {
        ChamberDetails chamberDetails = new ChamberDetails();
        //商会
        ShoppingChamber shoppingChamber = chamberClient.findChamberById(chamberId);
        chamberDetails.setShoppingChamber(shoppingChamber);
        //轮播图
        Example example = new Example(ShoppingBanner.class);
        example.createCriteria().andEqualTo("storeId", chamberId + "");
        List<ShoppingBanner> shoppingBannerList = shoppingBannerMapper.selectByExample(example);
        chamberDetails.setShoppingBannerList(shoppingBannerList);
        //商会成员
        List<ChamberUser> chamberUserList = chamberClient.findChamberUserByChamberId(chamberId);
        chamberDetails.setChamberUserList(chamberUserList);
        return chamberDetails;
    }


}
