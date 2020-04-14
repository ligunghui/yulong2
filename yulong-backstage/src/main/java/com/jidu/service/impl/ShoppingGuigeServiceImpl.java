package com.jidu.service.impl;

import com.jidu.mapper.ShoppingGuigeMapper;
import com.jidu.pojo.shop.ShoppingGuige;
import com.jidu.pojo.sys.Permission;
import com.jidu.service.ShoppingGuigeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/14 0014 上午 9:13
 * @Version:
 * @Description:
 */
@Service
public class ShoppingGuigeServiceImpl implements ShoppingGuigeService {
    @Autowired
    private ShoppingGuigeMapper shoppingGuigeMapper;

    @Override
    public void save(List<ShoppingGuige> shoppingGuiges) {
        for (ShoppingGuige shoppingGuige : shoppingGuiges) {

            shoppingGuigeMapper.insert(shoppingGuige);
        }
    }

    @Override
    public void update(ShoppingGuige shoppingGuige) {
          shoppingGuigeMapper.updateByPrimaryKeySelective(shoppingGuige);
    }

    @Override
    public ShoppingGuige findById(Integer id) {
        return shoppingGuigeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
         shoppingGuigeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ShoppingGuige> search(String goodsId) {
        Example example = new Example(ShoppingGuige.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId", goodsId);
        return shoppingGuigeMapper.selectByExample(example);
    }
}
