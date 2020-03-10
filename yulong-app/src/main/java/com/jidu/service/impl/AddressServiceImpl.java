package com.jidu.service.impl;

import com.jidu.mapper.AddressMapper;
import com.jidu.pojo.address.ShoppingAddress;
import com.jidu.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-16 12:47
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public void addShoppingAddress(ShoppingAddress shoppingAddress) {
        addressMapper.insert(shoppingAddress);
    }

    @Override
    public void editShoppingAddress(ShoppingAddress shoppingAddress) {
        addressMapper.updateByPrimaryKey(shoppingAddress);
    }

    @Override
    public void deleteShoppingAddress(long id) {
        addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ShoppingAddress> findShoppingAddress(String userId) {
        Example example=new Example(ShoppingAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        return addressMapper.selectByExample(example);
    }

    @Override
    public void SetDefault(long id, String userId) {
        Example example = new Example(ShoppingAddress.class);
        example.createCriteria().andEqualTo("userId", userId);
        ShoppingAddress shoppingAddress=new ShoppingAddress();
        shoppingAddress.setType(0);
        addressMapper.updateByExampleSelective(shoppingAddress,example);

        ShoppingAddress shoppingAddress1 = addressMapper.selectByPrimaryKey(id);
        shoppingAddress1.setType(1);
        addressMapper.updateByPrimaryKey(shoppingAddress1);
    }

    @Override
    public void cancelDefault(long id) {
        ShoppingAddress shoppingAddress1 = addressMapper.selectByPrimaryKey(id);
        shoppingAddress1.setType(0);
        addressMapper.updateByPrimaryKey(shoppingAddress1);
    }

    @Override
    public ShoppingAddress findShoppingAddressById(long id) {
        return addressMapper.selectByPrimaryKey(id);
    }
}
