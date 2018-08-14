package com.zhu.tools.datasource;

import java.lang.annotation.*;

/**
 * @description:创建拦截设置数据源的注解
 * Created by ZHU on 2018/8/13.
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {
    String dataSource() default "";
}
