package com.ocs.demo.persistence.orm;

import com.ocs.demo.model.Product;
import com.ocs.demo.model.Subscription;
import com.ocs.demo.persistence.ProductDataService;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.ocs.demo.model.public_.tables.records.ProductRecord;
import org.jooq.ocs.demo.model.public_.tables.records.SubscriptionRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.*;

import static org.jooq.ocs.demo.model.public_.Tables.PRODUCT;
import static org.jooq.ocs.demo.model.public_.Tables.SUBSCRIPTION;

public class JooqProductDataService implements ProductDataService {

    private DSLContext dslContext;

    public JooqProductDataService() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            this.dslContext = DSL.using(connection, SQLDialect.H2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getProducts() {
        return this.dslContext.selectFrom(PRODUCT).fetch().into(Product.class);
    }

    @Override
    public Product getProductDetails(UUID id) {
        Product product = this.dslContext
                .selectFrom(PRODUCT)
                .where(PRODUCT.ID.eq(id))
                .fetchOne().into(Product.class);

        List<Subscription> subscriptions = this.dslContext
                .selectFrom(SUBSCRIPTION)
                .where(SUBSCRIPTION.PRODUCTID.eq(product.getId()))
                .fetch().into(Subscription.class);

        if (subscriptions != null ) {
            return product.toBuilder()
                .subscriptions(new HashSet(subscriptions))
                .build();
        } else {
            return product;
        }
    }

    @Override
    public void addProduct(Product product) {
        ProductRecord productRecord = this.dslContext.newRecord(PRODUCT);
        productRecord.setId(product.getId());
        productRecord.setCode(product.getCode());
        productRecord.setStatus(product.getStatus());
        productRecord.insert();
    }

    @Override
    public void addSubscription(Subscription subscription) {
        SubscriptionRecord subscriptionRecord = this.dslContext.newRecord(SUBSCRIPTION);
        subscriptionRecord.setId(subscription.getId());
        subscriptionRecord.setCustomerid(subscription.getCustomerId());
        subscriptionRecord.setProductid(subscription.getProductId());
        if (subscription.getValidFrom() != null) {
            subscriptionRecord.setValidfrom(subscription.getValidFrom().atOffset(ZoneOffset.UTC));
        }
        if (subscription.getValidTo() != null) {
            subscriptionRecord.setValidto(subscription.getValidTo().atOffset(ZoneOffset.UTC));
        }
        subscriptionRecord.insert();
    }
}
