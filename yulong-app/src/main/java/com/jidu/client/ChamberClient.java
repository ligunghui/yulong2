package com.jidu.client;

import com.jidu.entity.Result;
import com.jidu.pojo.shop.ChamberUser;
import com.jidu.pojo.shop.ShoppingChamber;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: yulong
 * @description:
 * @author: LiGuangHui
 * @create: 2020-02-13 10:56
 */
@FeignClient(value="chamber")
public interface ChamberClient {
    @PostMapping("/chamber/user")
    public void UserApplyChamber(@RequestBody ChamberUser chamberUser);

    @RequestMapping(value = "/chamber/user/findChamberById/{id}", method = RequestMethod.GET)
    public ShoppingChamber findChamberById(@PathVariable(value = "id") Integer id) ;

    @RequestMapping(value = "/chamber/user/cancelChamberByUserId/{id}", method = RequestMethod.GET)
    public Result cancelChamberByUserId(@PathVariable(value = "id") Integer id) ;
    @RequestMapping(value = "/chamber/user/findChamberUserByChamberId/{chamberId}", method = RequestMethod.GET)
    public List<ChamberUser> findChamberUserByChamberId(@PathVariable(value = "chamberId") Integer chamberId) ;
}
