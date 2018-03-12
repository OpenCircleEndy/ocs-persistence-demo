package com.ocs.demo;

import com.ocs.demo.persistence.DataServiceFactory;
import com.ocs.demo.persistence.ProductDataService;

public class DemoApplication {

	public static void main(String... args) {
		DataServiceFactory factory = new DataServiceFactory(args);
		ProductDataService productDataService = factory.getProductDataService();

		System.out.println("*** Demo Search and Select ***");

		System.out.println("*** Get Products:");
		productDataService.getProducts().forEach(System.out::println);

		System.out.println("*** Select Product:");
		System.out.print(productDataService.getProductDetails(productDataService.getProducts().get(0).getId()));
	}
}
