package com.ocs.demo.persistence;

import com.ocs.demo.model.Product;
import com.ocs.demo.model.Subscription;

import java.time.LocalDateTime;
import java.util.*;

public class ProxyProductDataService implements ProductDataService {

    private static final int SIZE = 10;

    private final DataServiceFactory dataServiceFactory;

    private Map<UUID, Product> objectCache = new HashMap<>(SIZE);

    ProxyProductDataService(DataServiceFactory factory) {
        this.dataServiceFactory = factory;
        this.prefetch();
    }

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(this.objectCache.values()));
    }

    @Override
    public Product getProductDetails(UUID id) {
        return this.objectCache.get(id);
    }

    private void prefetch() {
        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            UUID productId = UUID.randomUUID();
            byte[] code = new byte[4];
            random.nextBytes(code);

            Product product = Product.builder()
                .id(productId)
                .code(new String(code))
                .status((System.currentTimeMillis() % 2) == 0 ? "PUBLISHED" : "DEVELOPMENT")
                .subscription(Subscription.builder()
                    .id(UUID.randomUUID())
                    .productId(productId)
                    .customerId(UUID.randomUUID())
                    .validFrom(LocalDateTime.of(2018, random.nextInt(11)+1, random.nextInt(28)+1, 12, 12 ))
                    .build())
                .build();
            this.objectCache.putIfAbsent(product.getId(), product);
        }
    }
}
