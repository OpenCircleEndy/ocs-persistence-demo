package com.ocs.demo;

import com.ocs.demo.model.Product;
import com.ocs.demo.model.Subscription;
import com.ocs.demo.persistence.DataServiceFactory;
import com.ocs.demo.persistence.ProductDataService;

import java.time.LocalDateTime;
import java.util.UUID;

public class DemoApplication {

	public static void main(String... args) {
		DataServiceFactory factory = new DataServiceFactory(args);
		ProductDataService productDataService = factory.getProductDataService();

		System.out.println("*** Demo Search and Select ***");

		System.out.println("\n*** Add Product:");
		Product product = createProduct("R23JDSK", "PUBLISHED");
		productDataService.addProduct(product);
		System.out.println(product);

		System.out.println("\n*** Add Subscription:");
		Subscription subscription = createSubscription(product.getId(), LocalDateTime.now());
		productDataService.addSubscription(subscription);
		System.out.println(subscription);

		System.out.println("\n*** Get Products:");
		productDataService.getProducts().forEach(System.out::println);

		System.out.println("\n*** Select Product:");
		System.out.print(productDataService.getProductDetails(productDataService.getProducts().get(0).getId()));
	}

	private static Product createProduct(String code, String status) {
		return Product.builder().id(UUID.randomUUID()).code(code).status(status).build();
	}

	private static Subscription createSubscription(UUID productId, LocalDateTime validFrom) {
		return Subscription.builder().id(UUID.randomUUID()).productId(productId).customerId(UUID.randomUUID())
			.validFrom(validFrom).build();
	}
}
