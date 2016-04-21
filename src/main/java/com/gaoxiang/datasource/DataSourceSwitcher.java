package com.gaoxiang.datasource;

import org.springframework.util.Assert;
/**
 * Auth: MaSj
 * Desc:
 * Date: 15/12/10 17:35
 */
public class DataSourceSwitcher {
    @SuppressWarnings("rawtypes")
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    @SuppressWarnings("unchecked")
    public static void setDataSource(String dataSource) {
        Assert.notNull(dataSource, "dataSource cannot be null");
        contextHolder.set(dataSource);
    }

    public static void setAppCpa1(){
        setDataSource("appcpa");
    }

//    public static void setAppCpa2() {
//        setDataSource("appcpa2");
//    }

    public static String getDataSource() {
        return (String) contextHolder.get();
    }

    public static void clearDataSource() {
        contextHolder.remove();
    }
}
