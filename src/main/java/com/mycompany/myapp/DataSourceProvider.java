package com.mycompany.myapp;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceProvider {

    private static BasicDataSource singleDataSource;

    public static BasicDataSource getSingleDataSource(){

        if(singleDataSource == null){

            singleDataSource = new BasicDataSource();

            String url = "jdbc:postgresql://localhost:5432/tennis";
            singleDataSource.setUrl(url);
            singleDataSource.setUsername("postgres");
            singleDataSource.setPassword("p@ssw0rd");
        }
        return singleDataSource;
    }
}
