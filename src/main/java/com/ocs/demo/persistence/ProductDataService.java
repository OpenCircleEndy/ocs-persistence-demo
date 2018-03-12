package com.ocs.demo.persistence;

import com.ocs.demo.model.Product;
import com.ocs.demo.model.Subscription;

import java.util.List;
import java.util.UUID;

public interface ProductDataService {
    List<Product> getProducts();
    Product getProductDetails(UUID id);
    void addProduct(Product product);
    void addSubscription(Subscription subscription);
}
