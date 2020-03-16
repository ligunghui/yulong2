package com.jidu.service.impl;

import com.jidu.group.IndexGroup;
import com.jidu.pojo.sys.UserAccount;
import com.jidu.service.IncomeService;
import com.jidu.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/9 0009 下午 7:03
 * @Version:
 * @Description:
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private IncomeService incomeService;
    @Override
    public IndexGroup findAll(String storeId) {
        IndexGroup indexGroup=new IndexGroup();
        BigDecimal monthProfit =new BigDecimal("0");
        BigDecimal historyProfit =new BigDecimal("0");
        List<UserAccount> monthProfitAccount = incomeService.findIncome(1, storeId);//本月
        for (UserAccount userAccount : monthProfitAccount) {
            if (userAccount.getSymbol().equals("+")){
                BigDecimal money = userAccount.getMoney();
                monthProfit= monthProfit.add(money);
            }
        }
        indexGroup.setMonthProfit(monthProfit);
        List<UserAccount> historyProfitAccount = incomeService.findIncome(2, storeId);//历史
        for (UserAccount userAccount : historyProfitAccount) {
            if (userAccount.getSymbol().equals("+")){
                BigDecimal money = userAccount.getMoney();
                historyProfit= historyProfit.add(money);
            }
        }
        indexGroup.setHistoryProfit(historyProfit);

        return indexGroup;
    }
}
