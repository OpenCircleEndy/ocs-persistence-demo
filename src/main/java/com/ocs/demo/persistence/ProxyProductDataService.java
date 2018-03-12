package com.ocs.demo.persistence;

import com.ocs.demo.model.Product;
import com.ocs.demo.model.Subscription;

import java.util.*;

public class ProxyProductDataService implements ProductDataService {

    private static final int SIZE = 10;

    private Map<UUID, Product> objectCache = new HashMap<>(SIZE);

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(this.objectCache.values()));
    }

    @Override
    public Product getProductDetails(UUID id) {
        return this.objectCache.get(id);
    }

    @Override
    public void addProduct(Product product) {
        this.objectCache.putIfAbsent(product.getId(), product);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        Product product = this.objectCache.get(subscription.getProductId());
        product = product.toBuilder().subscription(subscription).build();
        this.objectCache.replace(product.getId(), product);
    }
}
