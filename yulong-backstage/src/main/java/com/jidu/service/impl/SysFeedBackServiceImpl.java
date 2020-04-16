package com.jidu.service.impl;

import com.jidu.mapper.SysFeedbackMapper;
import com.jidu.pojo.sys.SysFeedback;
import com.jidu.service.SysFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/4/2 0002 上午 11:19
 * @Version:
 * @Description:
 */
@Service
public class SysFeedBackServiceImpl implements SysFeedBackService {
    @Autowired
    private SysFeedbackMapper sysFeedbackMapper;
    @Override
    public List<SysFeedback> search() {
        return sysFeedbackMapper.selectAll();
    }
}
