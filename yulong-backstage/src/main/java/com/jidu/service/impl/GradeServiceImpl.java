package com.jidu.service.impl;

import com.jidu.mapper.GradeMapper;
import com.jidu.pojo.activity.ShoppingActivity;
import com.jidu.pojo.grade.SysGrade;
import com.jidu.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/4 0004 上午 11:33
 * @Version:
 * @Description:
 */
@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public void save(SysGrade sysGrade) {
        gradeMapper.insert(sysGrade);
    }

    @Override
    public void update(SysGrade sysGrade) {
        gradeMapper.updateByPrimaryKeySelective(sysGrade);
    }

    @Override
    public SysGrade findById(Integer id) {
        return gradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Integer id) {
        gradeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysGrade> search() {
        Example example = new Example(SysGrade.class);
        Example.Criteria criteria = example.createCriteria();
        return gradeMapper.selectByExample(example);
    }
}
