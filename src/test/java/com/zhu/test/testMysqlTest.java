package com.zhu.test;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ZHU on 2018/8/13.
 */
public class testMysqlTest {

    @Test
    public void testMysql() throws Exception{
        assertEquals(3,new testMysql().testMysql());
    }
}