package com.ocs.demo.persistence;

import com.ocs.demo.persistence.orm.JooqProductDataService;

public class DataServiceFactory {

    private final String[] arguments;

    public DataServiceFactory(String... args) {
        this.arguments = args;
    }

    public ProductDataService getProductDataService() {
        return new JooqProductDataService();
    }
}
