package com.jidu.service.impl;

import com.jidu.aop.Log;
import com.jidu.mapper.LogMapper;
import com.jidu.pojo.local.LocalServiceStore;
import com.jidu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/24 0024 上午 11:20
 * @Version:
 * @Description:
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public List<Log> search() {
        Example example = new Example(Log.class);
        example.setOrderByClause("operatedate desc");
        return logMapper.selectAll();
    }
}
