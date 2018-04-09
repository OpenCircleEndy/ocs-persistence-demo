package com.ocs.demo.persistence.orm;

import com.ocs.demo.model.Product;
import com.ocs.demo.model.Subscription;
import com.ocs.demo.persistence.ProductDataService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class JooqProductDataService implements ProductDataService{


    @Override
    public List<Product> getProducts() {
        // add application code here
        try {
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:~/test", "sa", "");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM product");

            System.out.println("Products: " + rs.next());

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProductDetails(UUID id) {
        return null;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void addSubscription(Subscription subscription) {

    }
}
