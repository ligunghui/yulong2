package com.jidu.aop;

import com.jidu.mapper.LogMapper;
import com.jidu.utils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Author: liguanghui
 * Date: 2020/3/24 0024 上午 10:36
 * @Version:
 * @Description:
 */
@Order(3)
@Component
@Aspect
public class LogAopAspect {
    @Autowired
    private LogMapper logMapper;

    /**
     * 环绕通知记录日志通过注解匹配到需要增加日志功能的方法
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.jidu.aop.LogAnno)")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        LogAnno logAnno = method.getAnnotation(LogAnno.class);
        String operateType = logAnno.operateType();
        Log log = new Log();
        log.setOperatetype(operateType);
        log.setOperateor("系统管理员");
        String ip = HttpContextUtil.getIpAddress();
        log.setIp(ip);
        Object result = null;
        try {

            result = pjp.proceed();

            Object[] args = pjp.getArgs();

            log.setOperateresult("正常");
        } catch (SQLException e) {
            // 3.相当于异常通知部分
            log.setOperateresult("失败");
        } finally {
            // 4.相当于最终通知
            log.setOperatedate(new Date());
            logMapper.insert(log);
        }
        return result;
    }

}
