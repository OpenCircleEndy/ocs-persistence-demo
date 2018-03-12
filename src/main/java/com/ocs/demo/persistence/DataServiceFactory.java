package com.ocs.demo.persistence;

public class DataServiceFactory {

    private final String[] arguments;

    public DataServiceFactory(String... args) {
        this.arguments = args;
    }

    public ProductDataService getProductDataService() {
        return new ProxyProductDataService(this);
    }
}
