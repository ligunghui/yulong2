package com.jidu.service.impl;

import com.jidu.mapper.ShoppingGoodsMapper;
import com.jidu.pojo.goods.ShoppingGoods;
import com.jidu.pojo.order.OrderItem;
import com.jidu.pojo.shop.ShoppingCart;
import com.jidu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-13 13:59
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ShoppingGoodsMapper shoppingGoodsMapper;

    @Override
    public List<ShoppingCart> addGoodsToCartList(List<ShoppingCart> cartList, Long goodsId, Integer num) {
        ShoppingGoods shoppingGoods = shoppingGoodsMapper.selectByPrimaryKey(goodsId);
        if (Objects.isNull(shoppingGoods)) {
            throw new RuntimeException("商品不存在");
        }
        if (1 == shoppingGoods.getOnsale()) {
            throw new RuntimeException("商品已下架");
        }
        String storeId = shoppingGoods.getStoreId();
        ShoppingCart shoppingCart = searchCartBySellerId(cartList, storeId);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setStoreId(storeId);//商家ID
            shoppingCart.setStoreName(shoppingGoods.getStoreName());
            List<OrderItem> orderItemList = new ArrayList();//创建购物车明细列表
            OrderItem orderItem = createOrderItem(shoppingGoods, num);
            orderItemList.add(orderItem);
            shoppingCart.setOrderItemList(orderItemList);

            //4.2将新的购物车对象添加到购物车列表中
            cartList.add(shoppingCart);

        } else {//5.如果购物车列表中存在该商家的购物车
            // 判断该商品是否在该购物车的明细列表中存在
            OrderItem orderItem = searchOrderItemByGoodsId(shoppingCart.getOrderItemList(), goodsId);
            if (orderItem == null) {
                //5.1  如果不存在  ，创建新的购物车明细对象，并添加到该购物车的明细列表中
                orderItem = createOrderItem(shoppingGoods, num);
                shoppingCart.getOrderItemList().add(orderItem);

            } else {
                //5.2 如果存在，在原有的数量上添加数量 ,并且更新金额
                orderItem.setNum(orderItem.getNum() + num);//更改数量
                //金额
                orderItem.setTotalFee(new BigDecimal(orderItem.getPrice().doubleValue() * orderItem.getNum()));
                //当明细的数量小于等于0，移除此明细
                if (orderItem.getNum() <= 0) {
                    shoppingCart.getOrderItemList().remove(orderItem);
                }
                //当购物车的明细数量为0，在购物车列表中移除此购物车
                if (shoppingCart.getOrderItemList().size() == 0) {
                    cartList.remove(shoppingCart);
                }
            }

        }

        return cartList;
    }


    private OrderItem searchOrderItemByGoodsId(List<OrderItem> orderItemList, Long goodsId) {
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getItemId().longValue() == goodsId) {
                return orderItem;
            }
        }
        return null;
    }

    private OrderItem createOrderItem(ShoppingGoods shoppingGoods, Integer num) {
        OrderItem orderItem = new OrderItem();
        orderItem.setGoodsId(shoppingGoods.getId());
        orderItem.setItemId(shoppingGoods.getId());
        orderItem.setNum(num);
        orderItem.setPicPath(shoppingGoods.getImg());
        orderItem.setPrice(shoppingGoods.getStorePrice());
        orderItem.setSellerId(shoppingGoods.getStoreId());
        orderItem.setTitle(shoppingGoods.getName());
        orderItem.setTotalFee(new BigDecimal(shoppingGoods.getStorePrice().doubleValue() * num));
        return orderItem;
    }

    private ShoppingCart searchCartBySellerId(List<ShoppingCart> cartList, String storeId) {
        for (ShoppingCart cart : cartList) {
            if (cart.getStoreId().equals(storeId)) {
                return cart;
            }
        }
        return null;
    }


    @Override
    public List<ShoppingCart> findCartListFromRedis(String userId) {
        System.out.println("从redis中提取购物车" + userId);
        List<ShoppingCart> cartList = (List<ShoppingCart>) redisTemplate.boundHashOps("cartList").get(userId);
        if (cartList == null) {
            cartList = new ArrayList();
        }
        return cartList;
    }

    @Override
    public List<ShoppingCart> mergeCartList(List<ShoppingCart> cartList_cookie, List<ShoppingCart> cartList_redis) {
        System.out.println("合并购物车");
        for (ShoppingCart cart : cartList_redis) {
            for (OrderItem orderItem : cart.getOrderItemList()) {
                cartList_cookie = addGoodsToCartList(cartList_cookie, orderItem.getItemId(), orderItem.getNum());
            }
        }
        return cartList_cookie;
    }

    @Override
    public void saveCartListToRedis(String userId, List<ShoppingCart> cartList) {
        System.out.println("向redis中存入购物车" + userId);
        redisTemplate.boundHashOps("cartList").put(userId, cartList);
    }


}
