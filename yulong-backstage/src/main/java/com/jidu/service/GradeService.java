package com.jidu.service;

import com.jidu.pojo.grade.SysGrade;

import java.util.List;

/**
 * @Author: liguanghui
 * Date: 2020/3/4 0004 上午 11:33
 * @Version:
 * @Description:
 */
public interface GradeService {
    void save(SysGrade sysGrade);

    void update(SysGrade sysGrade);

    SysGrade findById(Integer id);

    void delete(Integer id);

    List<SysGrade> search();
}
