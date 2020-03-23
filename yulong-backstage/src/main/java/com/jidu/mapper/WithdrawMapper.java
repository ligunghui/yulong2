package com.jidu.mapper;

import com.jidu.pojo.withdrawal.WithdrawalApplication;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 下午 5:08
 * @Version:
 * @Description:
 */
@Service
public interface WithdrawMapper extends Mapper<WithdrawalApplication> {
}
