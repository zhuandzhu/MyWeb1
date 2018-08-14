package com.zhu.tools.datasource;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;

/**
 * Created by ZHU on 2018/8/13.
 */
public class HandlerDataSourceAop {
    protected Logger logger = LoggerFactory.getLogger(HandlerDataSourceAop.class);

    //@within在类上设置
    //@annotation在方法上进行设置
    @Pointcut("@within(com.zhu.tools.datasource.DynamicSwitchDataSource)||@annotation(com.zhu.tools.datasource.DynamicSwitchDataSource)")
    public void pointcut(){}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        DynamicSwitchDataSource annotationClass = method.getAnnotation(DynamicSwitchDataSource.class);//获取方法上的注解
        if(annotationClass == null){
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DynamicSwitchDataSource.class);//获取类上面的注解
            if(annotationClass == null) return;
        }
        //获取注解上的数据源的值的信息
        String dataSourceKey = annotationClass.dataSource();
        if(dataSourceKey !=null){
            //给当前的执行SQL的操作设置特殊的数据源的信息
            HandlerDataSource.putDataSource(dataSourceKey);
        }
        logger.info("AOP动态切换数据源，className"+joinPoint.getTarget().getClass().getName()+"methodName"+method.getName()+";dataSourceKey:"+dataSourceKey==""?"默认数据源":dataSourceKey);
    }

    @After("pointcut()")
    public void after(JoinPoint point){
        //清理掉当前设置的数据源，让默认的数据源不受影响
        HandlerDataSource.clear();
    }
}
