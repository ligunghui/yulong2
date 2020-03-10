package com.jidu.service;

import com.jidu.pojo.address.ShoppingAddress;

import java.util.List;

public interface AddressService {
    void addShoppingAddress(ShoppingAddress shoppingAddress);

    void editShoppingAddress(ShoppingAddress shoppingAddress);

    void deleteShoppingAddress(long id);

    List<ShoppingAddress> findShoppingAddress(String userId);

    void SetDefault(long id, String userId);

    void cancelDefault(long id);

    ShoppingAddress findShoppingAddressById(long id);
}
