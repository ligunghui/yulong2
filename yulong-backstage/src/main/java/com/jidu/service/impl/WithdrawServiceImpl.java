package com.jidu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.jidu.config.AlipayConfig;
import com.jidu.entity.Result;
import com.jidu.entity.ResultCode;
import com.jidu.mapper.WithdrawMapper;
import com.jidu.pojo.AlipayVo;
import com.jidu.pojo.PayeeInfo;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.withdrawal.WithdrawalApplication;
import com.jidu.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 下午 3:52
 * @Version:
 * @Description:
 */
@Service
public class WithdrawServiceImpl implements WithdrawService {
    @Autowired
    private WithdrawMapper withdrawMapper;

    @Override
    public List<WithdrawalApplication> searchAuthentication(Map param, Integer status, Integer userType) {
        Example example = new Example(WithdrawalApplication.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userType", userType);
        if (0 != status) {
            criteria.andEqualTo("userType", userType);
        }
        return withdrawMapper.selectByExample(example);
    }

    @Override
    public Result delAuthentication(Integer id, Integer status) {
        WithdrawalApplication withdrawalApplication = withdrawMapper.selectByPrimaryKey(id);
        if (withdrawalApplication.getStatus() == 2) {
            return new Result(201, "重复审核", false);
        }

        withdrawalApplication.setStatus(status);
        withdrawMapper.updateByPrimaryKeySelective(withdrawalApplication);

        return new Result(ResultCode.SUCCESS);
    }

    public Map<String, String> alipay2User(String bizNo, String name, String account, String identity) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        Map<String, String> resultMap = new HashMap<String, String>();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        AlipayVo vo = new AlipayVo();
        vo.setOut_biz_no(bizNo);
        vo.setTrans_amount(account);
        vo.setProduct_code("TRANS_ACCOUNT_NO_PWD");
        vo.setBiz_scene("DIRECT_TRANSFER");
        vo.setOrder_title("提现");
        PayeeInfo payeeInfo = new PayeeInfo();
        payeeInfo.setName(name);
        payeeInfo.setIdentity_type("ALIPAY_USER_ID");
        payeeInfo.setIdentity(identity);
        vo.setPayee_info(payeeInfo);
        String s = JSON.toJSONString(vo);

        // 设置请求参数
        AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
        alipayRequest.setBizContent(s);
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(alipayRequest);
            System.out.println(JSON.toJSONString(response));
            if ("10000".equals(response.getCode())) {
                resultMap.put("success", "true");
                resultMap.put("des", "转账成功");
            } else {
                resultMap.put("success", "false");
                resultMap.put("des", response.getSubMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            resultMap.put("success", "false");
            resultMap.put("des", "转账失败！");
        }
        return resultMap;
    }
}
