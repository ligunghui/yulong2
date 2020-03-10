package com.jidu;

/**
 * @Author: liguanghui
 * Date: 2020/3/5 0005 下午 3:25
 * @Version:
 * @Description:
 */

import com.jidu.mapper.AddressMapper;
import com.jidu.pojo.address.ShoppingAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTest {
    @Autowired
    private AddressMapper addressMapper;
    @Test
    public  void test(){
        ShoppingAddress shoppingAddress = addressMapper.selectByPrimaryKey(1);
        System.out.println(shoppingAddress);
    }
}
