package com.zhu.tools.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by ZHU on 2018/8/13.
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey(){
        return HandlerDataSource.getDataSource();
    }
}
