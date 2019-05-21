package com.bnpp.demo.spring.interceptor;

import org.hibernate.EmptyInterceptor;

public class AutoTableNameInterceptor extends EmptyInterceptor {

    private String srcName = "";
    private String destName = "";

    public AutoTableNameInterceptor() {}

    public AutoTableNameInterceptor(String srcName,String destName){
        this.srcName = srcName;
        this.destName = destName;
    }


    @Override
    public String onPrepareStatement(String sql) {
        if(srcName.equals("TBL_PRODUCTS_HISTORY_0")) {
            sql = sql.replace(srcName, "TBL_PRODUCTS_HISTORY_"+ destName);
            System.out.println("sql_______" + sql);
        }
        return sql;
    }
}


