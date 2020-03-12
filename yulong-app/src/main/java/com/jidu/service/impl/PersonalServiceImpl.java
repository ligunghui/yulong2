package com.jidu.service.impl;

import com.jidu.client.ChamberClient;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.*;
import com.jidu.pojo.goods.GiftCollect;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.order.ShoppingOrder;
import com.jidu.pojo.shop.ChamberUser;
import com.jidu.pojo.shop.ShoppingChamber;
import com.jidu.pojo.shop.ShoppingStore;
import com.jidu.pojo.sys.UserInfo;
import com.jidu.service.PersonalService;
import com.jidu.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-11 15:32
 */
@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ChamberClient chamberClient;
    @Autowired
    private GiftCollectMapper giftCollectMapper;
    @Autowired
    private ShoppingOrderMapper shoppingOrderMapper;
    @Autowired
    private ShoppingGoodsMapper shoppingGoodsMapper;
    @Autowired
    private ShoppingStoreMapper storeMapper;
    @Autowired
    private IdWorker idWorker;

    @Override
    public List<ShoppingOrder> findOrderByUserId(String userId, int status) {
        Example example = new Example(ShoppingOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("orderStatus", status);
        return shoppingOrderMapper.selectByExample(example);
    }

    @Override
    public Result UserApplyChamber(ChamberUser chamberUser) {
        String userId = chamberUser.getUserId();
        if (StringUtils.isEmpty(userId)) {
            return new Result(201, "用户id不能为空", false);
        }
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(userInfo)) {
            return new Result(201, "用户为空", false);
        }
        if (0 != userInfo.getChamberId()) {
            return new Result(201, "用户已经加入商会", false);
        }
        chamberUser.setApplyTime(new Date());
        // ResponseEntity<Result> resultResponseEntity = restTemplate.postForEntity("http://chamber/chamber/user", chamberUser, Result.class);
        chamberClient.UserApplyChamber(chamberUser);
        userInfo.setChamberId(chamberUser.getChamberId());
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result findChamberByUserId(String userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(userInfo)) {
            return new Result(201, "用户为空", false);
        }

        if (userInfo.getChamberId()==null ||0 == userInfo.getChamberId()) {
            return new Result(201, "您还未加入商会", false);
        }
        //ShoppingChamber shoppingChamber = restTemplate.getForObject("http://chamber/chamber/user/findChamberById/"+userInfo.getChamberId(),ShoppingChamber.class);
        ShoppingChamber shoppingChamber = chamberClient.findChamberById(userInfo.getChamberId());
        return new Result(ResultCode.SUCCESS, shoppingChamber);
    }

    @Override
    public Result cancelChamberByUserId(String userId) {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(userInfo)) {
            return new Result(201, "用户为空", false);
        }
        if (0 == userInfo.getChamberId()) {
            return new Result(201, "您还未加入商会", false);
        }
        chamberClient.cancelChamberByUserId(userInfo.getChamberId());
        userInfo.setChamberId(0);
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public List<ShoppingGoods> findCollectByUserId(String userId) {
        Example example = new Example(GiftCollect.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<GiftCollect> giftCollects = giftCollectMapper.selectByExample(example);
        List<ShoppingGoods> list = new ArrayList<>();
        for (GiftCollect giftCollect : giftCollects) {
            ShoppingGoods shoppingGoods = shoppingGoodsMapper.selectByPrimaryKey(giftCollect.getGoodsId());
            list.add(shoppingGoods);
        }

        return list;
    }

    @Override
    public Result UserApplyBusiness(ShoppingStore shoppingStore) {
        String id = idWorker.nextId() + "";
        shoppingStore.setId(id);
        shoppingStore.setStoreRecommend(false);
        shoppingStore.setValidity(0);
        shoppingStore.setStoreStatus(1);
        storeMapper.insert(shoppingStore);
        //分配商户初始管理员

        return new Result(ResultCode.SUCCESS);
    }
}
